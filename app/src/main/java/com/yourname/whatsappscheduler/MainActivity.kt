package com.yourname.whatsappscheduler

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.yourname.whatsappscheduler.data.AppDatabase
import com.yourname.whatsappscheduler.data.Message
import com.yourname.whatsappscheduler.utils.PhoneNumberFormatter
import com.yourname.whatsappscheduler.utils.SchedulingHelper
import kotlinx.coroutines.launch
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var etPhoneNumber: EditText
    private lateinit var etMessage: EditText
    private lateinit var btnSelectContact: Button
    private lateinit var btnImportContacts: Button
    private lateinit var tvSelectedContact: TextView
    private lateinit var btnPickDateTime: Button
    private lateinit var tvSelectedDateTime: TextView
    private lateinit var btnSchedule: Button
    private lateinit var btnViewScheduled: Button

    private var selectedCalendar: Calendar? = null
    private var selectedRecipients: List<Recipient> = emptyList()
    private lateinit var database: AppDatabase
    private val notificationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (!isGranted) {
            Toast.makeText(
                this,
                "Notifications are needed to remind you when messages are due.",
                Toast.LENGTH_LONG
            ).show()
        }
    }
    private val contactPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            openContactSelector()
        } else {
            Toast.makeText(this, "Contacts permission is needed to select saved contacts.", Toast.LENGTH_LONG).show()
        }
    }
    private val contactSelectionLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val names = result.data?.getStringArrayListExtra(ContactSelectionActivity.EXTRA_CONTACT_NAMES).orEmpty()
            val numbers = result.data?.getStringArrayListExtra(ContactSelectionActivity.EXTRA_CONTACT_NUMBERS).orEmpty()
            selectedRecipients = numbers.mapIndexed { index, phoneNumber ->
                Recipient(names.getOrElse(index) { phoneNumber }, phoneNumber)
            }
            updateSelectedRecipientsUi()
        }
    }
    private val contactImportLauncher = registerForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri ->
        if (uri != null) {
            importContacts(uri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = AppDatabase.getDatabase(this)

        etPhoneNumber = findViewById(R.id.etPhoneNumber)
        etMessage = findViewById(R.id.etMessage)
        btnSelectContact = findViewById(R.id.btnSelectContact)
        btnImportContacts = findViewById(R.id.btnImportContacts)
        tvSelectedContact = findViewById(R.id.tvSelectedContact)
        btnPickDateTime = findViewById(R.id.btnPickDateTime)
        tvSelectedDateTime = findViewById(R.id.tvSelectedDateTime)
        btnSchedule = findViewById(R.id.btnSchedule)
        btnViewScheduled = findViewById(R.id.btnViewScheduled)

        btnSelectContact.setOnClickListener {
            requestContactsPermissionThenOpen()
        }

        btnImportContacts.setOnClickListener {
            openContactsFilePicker()
        }

        btnPickDateTime.setOnClickListener {
            showDateTimePicker()
        }

        btnSchedule.setOnClickListener {
            scheduleMessage()
        }

        btnViewScheduled.setOnClickListener {
            val intent = Intent(this, ScheduledMessagesActivity::class.java)
            startActivity(intent)
        }

        requestNotificationPermissionIfNeeded()
    }

    private fun showDateTimePicker() {
        val now = Calendar.getInstance()

        val datePicker = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val timePicker = TimePickerDialog(
                    this,
                    { _, hourOfDay, minute ->
                        val calendar = Calendar.getInstance()
                        calendar.set(year, month, dayOfMonth, hourOfDay, minute, 0)
                        calendar.set(Calendar.MILLISECOND, 0)
                        selectedCalendar = calendar

                        val formattedTime = android.text.format.DateFormat.format(
                            "MMM dd, yyyy - HH:mm",
                            calendar
                        )
                        tvSelectedDateTime.text = formattedTime
                        btnSchedule.isEnabled = true
                    },
                    now.get(Calendar.HOUR_OF_DAY),
                    now.get(Calendar.MINUTE),
                    true
                )
                timePicker.show()
            },
            now.get(Calendar.YEAR),
            now.get(Calendar.MONTH),
            now.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    private fun scheduleMessage() {
        val phoneNumber = etPhoneNumber.text.toString().trim()
        val recipients = getRecipientsForScheduling(phoneNumber)
        val messageText = etMessage.text.toString().trim()
        val scheduleTime = selectedCalendar?.time

        if (recipients.isEmpty()) {
            etPhoneNumber.error = "At least one phone number required"
            return
        }

        if (messageText.isEmpty()) {
            etMessage.error = "Message required"
            return
        }

        if (scheduleTime == null) {
            Toast.makeText(this, "Please select date and time", Toast.LENGTH_SHORT).show()
            return
        }

        if (scheduleTime.time <= System.currentTimeMillis()) {
            Toast.makeText(this, "Please choose a future date and time.", Toast.LENGTH_LONG).show()
            return
        }

        lifecycleScope.launch {
            var scheduledCount = 0

            recipients.forEach { recipient ->
                val message = Message(
                    phoneNumber = recipient.phoneNumber,
                    messageText = messageText,
                    scheduledTime = scheduleTime.time,
                    status = "pending"
                )
                val messageId = database.messageDao().insert(message)
                val scheduled = SchedulingHelper.scheduleMessage(
                    this@MainActivity,
                    messageId,
                    PhoneNumberFormatter.toWhatsAppNumber(recipient.phoneNumber),
                    messageText,
                    scheduleTime.time
                )

                if (scheduled) {
                    scheduledCount++
                } else {
                    database.messageDao().delete(messageId)
                }
            }

            if (scheduledCount == 0) {
                showSchedulingFailure()
                return@launch
            }

            Toast.makeText(
                this@MainActivity,
                "Scheduled for $scheduledCount recipient(s) at ${scheduleTime.toString()}",
                Toast.LENGTH_LONG
            ).show()

            etMessage.text?.clear()
            etPhoneNumber.text?.clear()
            selectedRecipients = emptyList()
            tvSelectedContact.text = "No contact selected"
            btnSchedule.isEnabled = false
            selectedCalendar = null
            tvSelectedDateTime.text = "No date/time selected"
        }
    }

    private fun requestContactsPermissionThenOpen() {
        val hasPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_CONTACTS
        ) == PackageManager.PERMISSION_GRANTED

        if (hasPermission) {
            openContactSelector()
        } else {
            contactPermissionLauncher.launch(Manifest.permission.READ_CONTACTS)
        }
    }

    private fun openContactSelector() {
        contactSelectionLauncher.launch(Intent(this, ContactSelectionActivity::class.java))
    }

    private fun openContactsFilePicker() {
        contactImportLauncher.launch(
            arrayOf(
                "text/*",
                "text/vcard",
                "text/x-vcard",
                "text/comma-separated-values",
                "application/csv",
                "application/vnd.ms-excel"
            )
        )
    }

    private fun importContacts(uri: Uri) {
        val importedRecipients = runCatching {
            contentResolver.openInputStream(uri)?.bufferedReader()?.use { reader ->
                parseImportedContacts(reader.readText())
            }.orEmpty()
        }.getOrElse {
            Toast.makeText(this, "Could not read contacts file.", Toast.LENGTH_LONG).show()
            return
        }

        if (importedRecipients.isEmpty()) {
            Toast.makeText(this, "No valid phone numbers found in that file.", Toast.LENGTH_LONG).show()
            return
        }

        selectedRecipients = (selectedRecipients + importedRecipients)
            .distinctBy { PhoneNumberFormatter.toWhatsAppNumber(it.phoneNumber) }
        updateSelectedRecipientsUi()

        Toast.makeText(
            this,
            "Imported ${importedRecipients.size} contact(s).",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun updateSelectedRecipientsUi() {
        etPhoneNumber.setText(selectedRecipients.joinToString(", ") { it.phoneNumber })
        tvSelectedContact.text = when (selectedRecipients.size) {
            0 -> "No contact selected"
            1 -> selectedRecipients.first().name
            else -> "${selectedRecipients.size} contacts selected"
        }
    }

    private fun getRecipientsForScheduling(phoneNumberText: String): List<Recipient> {
        if (selectedRecipients.isNotEmpty()) return selectedRecipients

        return phoneNumberText
            .split(",", ";", "\n")
            .map { it.trim() }
            .filter { PhoneNumberFormatter.toWhatsAppNumber(it).isNotEmpty() }
            .distinct()
            .map { Recipient(it, it) }
    }

    private fun parseImportedContacts(content: String): List<Recipient> {
        val trimmedContent = content.trim()
        if (trimmedContent.isBlank()) return emptyList()

        return if (trimmedContent.contains("BEGIN:VCARD", ignoreCase = true)) {
            parseVCardContacts(trimmedContent)
        } else {
            parseDelimitedContacts(trimmedContent)
        }.filter { PhoneNumberFormatter.toWhatsAppNumber(it.phoneNumber).isNotEmpty() }
            .distinctBy { PhoneNumberFormatter.toWhatsAppNumber(it.phoneNumber) }
    }

    private fun parseVCardContacts(content: String): List<Recipient> {
        return content.splitToSequence(Regex("(?i)BEGIN:VCARD"))
            .mapNotNull { card ->
                val lines = unfoldVCardLines(card.lines())
                val name = lines.firstValueFor("FN")
                    ?: lines.firstValueFor("N")?.replace(';', ' ')?.trim()
                val phoneNumber = lines.firstValueFor("TEL")

                phoneNumber?.takeIf { it.isNotBlank() }?.let {
                    Recipient(name.orEmpty().ifBlank { it }, it)
                }
            }
            .toList()
    }

    private fun unfoldVCardLines(lines: List<String>): List<String> {
        val unfoldedLines = mutableListOf<String>()
        lines.forEach { line ->
            if ((line.startsWith(" ") || line.startsWith("\t")) && unfoldedLines.isNotEmpty()) {
                unfoldedLines[unfoldedLines.lastIndex] += line.trimStart()
            } else {
                unfoldedLines.add(line)
            }
        }
        return unfoldedLines
    }

    private fun List<String>.firstValueFor(propertyName: String): String? {
        return firstNotNullOfOrNull { line ->
            val key = line.substringBefore(":", missingDelimiterValue = "")
                .substringBefore(";")
                .uppercase()
            if (key == propertyName) line.substringAfter(":", "").trim().ifBlank { null } else null
        }
    }

    private fun parseDelimitedContacts(content: String): List<Recipient> {
        val rows = content.lines()
            .map { parseDelimitedLine(it) }
            .filter { fields -> fields.any { it.isNotBlank() } }

        if (rows.isEmpty()) return emptyList()

        val header = rows.first()
        val nameIndex = header.indexOfFirst {
            it.equals("name", ignoreCase = true) ||
                it.equals("contact", ignoreCase = true) ||
                it.equals("full name", ignoreCase = true)
        }
        val phoneIndex = header.indexOfFirst {
            it.equals("phone", ignoreCase = true) ||
                it.equals("phone number", ignoreCase = true) ||
                it.equals("mobile", ignoreCase = true) ||
                it.equals("number", ignoreCase = true) ||
                it.equals("tel", ignoreCase = true)
        }
        val hasHeader = phoneIndex >= 0
        val dataRows = if (hasHeader) rows.drop(1) else rows

        return dataRows.mapNotNull { fields ->
            if (hasHeader) {
                val phoneNumber = fields.getOrNull(phoneIndex).orEmpty().trim()
                val name = fields.getOrNull(nameIndex).orEmpty().trim().ifBlank { phoneNumber }
                phoneNumber.takeIf { it.isNotBlank() }?.let { Recipient(name, it) }
            } else {
                fields.toRecipientFromUnknownColumns()
            }
        }
    }

    private fun parseDelimitedLine(line: String): List<String> {
        val separator = when {
            line.contains(",") -> ','
            line.contains(";") -> ';'
            line.contains("\t") -> '\t'
            else -> null
        }
        if (separator == null) return listOf(line.trim())

        val fields = mutableListOf<String>()
        val currentField = StringBuilder()
        var insideQuotes = false
        line.forEach { char ->
            when {
                char == '"' -> insideQuotes = !insideQuotes
                char == separator && !insideQuotes -> {
                    fields.add(currentField.toString().trim())
                    currentField.clear()
                }
                else -> currentField.append(char)
            }
        }
        fields.add(currentField.toString().trim())
        return fields
    }

    private fun List<String>.toRecipientFromUnknownColumns(): Recipient? {
        val phoneIndex = indexOfFirst { looksLikePhoneNumber(it) }
        if (phoneIndex < 0) return null

        val phoneNumber = get(phoneIndex).trim()
        val name = filterIndexed { index, value ->
            index != phoneIndex && value.isNotBlank()
        }.joinToString(" ").ifBlank { phoneNumber }

        return Recipient(name, phoneNumber)
    }

    private fun looksLikePhoneNumber(value: String): Boolean {
        val digitCount = value.count { it.isDigit() }
        return digitCount >= 7 && PhoneNumberFormatter.toWhatsAppNumber(value).isNotEmpty()
    }

    private fun requestNotificationPermissionIfNeeded() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) return

        val hasPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED

        if (!hasPermission) {
            notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    private fun showSchedulingFailure() {
        Toast.makeText(
            this,
            "Unable to schedule this message.",
            Toast.LENGTH_LONG
        ).show()
    }

    private data class Recipient(
        val name: String,
        val phoneNumber: String
    )
}
