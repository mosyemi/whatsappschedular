package com.yourname.whatsappscheduler

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText

class ContactSelectionActivity : AppCompatActivity() {

    private lateinit var tvSelectedCount: TextView
    private lateinit var rvContacts: RecyclerView
    private lateinit var btnSelectAll: Button
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_selection)

        tvSelectedCount = findViewById(R.id.tvSelectedCount)
        rvContacts = findViewById(R.id.rvContacts)
        btnSelectAll = findViewById(R.id.btnSelectAll)
        val etSearchContacts: TextInputEditText = findViewById(R.id.etSearchContacts)
        val btnDone: Button = findViewById(R.id.btnDone)

        adapter = ContactAdapter(loadContacts()) {
            updateSelectedCount()
        }
        rvContacts.layoutManager = LinearLayoutManager(this)
        rvContacts.adapter = adapter

        btnDone.setOnClickListener {
            finishWithSelectedContacts()
        }
        btnSelectAll.setOnClickListener {
            adapter.toggleSelectAllVisible()
            updateSelectedCount()
        }
        etSearchContacts.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter(s?.toString().orEmpty())
                updateSelectedCount()
            }

            override fun afterTextChanged(s: Editable?) = Unit
        })
        updateSelectedCount()
    }

    private fun loadContacts(): List<ContactItem> {
        val contacts = mutableListOf<ContactItem>()
        val projection = arrayOf(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )

        contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            projection,
            null,
            null,
            "${ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME} ASC"
        )?.use { cursor ->
            val nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            val numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)

            while (cursor.moveToNext()) {
                val name = cursor.getString(nameIndex).orEmpty().ifBlank { "Unnamed contact" }
                val phoneNumber = cursor.getString(numberIndex).orEmpty()
                if (phoneNumber.isNotBlank()) {
                    contacts.add(ContactItem(name, phoneNumber))
                }
            }
        }

        return contacts.distinctBy { it.name.lowercase() to it.phoneNumber }
    }

    private fun finishWithSelectedContacts() {
        val selectedContacts = adapter.getSelectedContacts()
        if (selectedContacts.isEmpty()) {
            Toast.makeText(this, "Select at least one contact.", Toast.LENGTH_SHORT).show()
            return
        }

        val resultIntent = Intent().apply {
            putStringArrayListExtra(EXTRA_CONTACT_NAMES, ArrayList(selectedContacts.map { it.name }))
            putStringArrayListExtra(EXTRA_CONTACT_NUMBERS, ArrayList(selectedContacts.map { it.phoneNumber }))
        }
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }

    private fun updateSelectedCount() {
        val count = adapter.getSelectedContacts().size
        val visibleCount = adapter.getVisibleContactCount()
        tvSelectedCount.text = "$count selected • $visibleCount visible"
        btnSelectAll.text = if (adapter.areAllVisibleContactsSelected()) {
            "Clear visible selection"
        } else {
            "Select all visible contacts"
        }
    }

    data class ContactItem(
        val name: String,
        val phoneNumber: String,
        var isSelected: Boolean = false
    )

    class ContactAdapter(
        private val contacts: List<ContactItem>,
        private val onSelectionChanged: () -> Unit
    ) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
        private var visibleContacts: List<ContactItem> = contacts

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
            return ContactViewHolder(view)
        }

        override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
            holder.bind(visibleContacts[position])
        }

        override fun getItemCount(): Int = visibleContacts.size

        fun getSelectedContacts(): List<ContactItem> = contacts.filter { it.isSelected }

        fun getVisibleContactCount(): Int = visibleContacts.size

        fun areAllVisibleContactsSelected(): Boolean {
            return visibleContacts.isNotEmpty() && visibleContacts.all { it.isSelected }
        }

        fun toggleSelectAllVisible() {
            val shouldSelect = !areAllVisibleContactsSelected()
            visibleContacts.forEach { contact ->
                contact.isSelected = shouldSelect
            }
            notifyDataSetChanged()
            onSelectionChanged()
        }

        fun filter(query: String) {
            val normalizedQuery = query.trim().lowercase()
            visibleContacts = if (normalizedQuery.isBlank()) {
                contacts
            } else {
                contacts.filter { contact ->
                    contact.name.lowercase().contains(normalizedQuery) ||
                        contact.phoneNumber.lowercase().contains(normalizedQuery)
                }
            }
            notifyDataSetChanged()
        }

        inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val cbSelected: CheckBox = itemView.findViewById(R.id.cbSelected)
            private val tvContactName: TextView = itemView.findViewById(R.id.tvContactName)
            private val tvContactPhone: TextView = itemView.findViewById(R.id.tvContactPhone)

            fun bind(contact: ContactItem) {
                tvContactName.text = contact.name
                tvContactPhone.text = contact.phoneNumber

                cbSelected.setOnCheckedChangeListener(null)
                cbSelected.isChecked = contact.isSelected
                cbSelected.setOnCheckedChangeListener { _, isChecked ->
                    contact.isSelected = isChecked
                    onSelectionChanged()
                }

                itemView.setOnClickListener {
                    contact.isSelected = !contact.isSelected
                    cbSelected.isChecked = contact.isSelected
                }
            }
        }
    }

    companion object {
        const val EXTRA_CONTACT_NAMES = "contact_names"
        const val EXTRA_CONTACT_NUMBERS = "contact_numbers"
    }
}
