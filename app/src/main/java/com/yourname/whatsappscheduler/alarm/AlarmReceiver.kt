package com.yourname.whatsappscheduler.alarm

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.yourname.whatsappscheduler.data.AppDatabase
import com.yourname.whatsappscheduler.utils.PhoneNumberFormatter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val messageId = intent.getLongExtra("message_id", -1L)
        val phoneNumber = intent.getStringExtra("phone_number") ?: return
        val messageText = intent.getStringExtra("message_text") ?: return

        createNotificationChannel(context)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
        ) {
            updateMessageStatus(context, messageId, "failed")
            return
        }

        val cleanNumber = PhoneNumberFormatter.toWhatsAppNumber(phoneNumber)
        val uri = Uri.parse("https://wa.me/$cleanNumber?text=${Uri.encode(messageText)}")
        val waIntent = Intent(Intent.ACTION_VIEW, uri).apply {
            setPackage("com.whatsapp")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        val pendingIntent = android.app.PendingIntent.getActivity(
            context,
            0,
            waIntent,
            android.app.PendingIntent.FLAG_UPDATE_CURRENT or android.app.PendingIntent.FLAG_IMMUTABLE
        )

        val channelId = "whatsapp_scheduler_channel"
        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Send WhatsApp Message")
            .setContentText("Tap to send to $phoneNumber")
            .setStyle(NotificationCompat.BigTextStyle().bigText(messageText))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        try {
            with(NotificationManagerCompat.from(context)) {
                notify(System.currentTimeMillis().toInt(), builder.build())
            }
        } catch (e: SecurityException) {
            updateMessageStatus(context, messageId, "failed")
            return
        }

        updateMessageStatus(context, messageId, "notified")
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return

        val channel = NotificationChannel(
            "whatsapp_scheduler_channel",
            "WhatsApp Scheduler",
            NotificationManager.IMPORTANCE_HIGH
        )
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun updateMessageStatus(context: Context, messageId: Long, status: String) {
        if (messageId == -1L) return

        val pendingResult = goAsync()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                AppDatabase.getDatabase(context).messageDao().updateStatus(messageId, status)
            } finally {
                pendingResult.finish()
            }
        }
    }
}
