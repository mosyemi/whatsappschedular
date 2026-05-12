package com.yourname.whatsappscheduler.worker

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.yourname.whatsappscheduler.data.AppDatabase

class SendMessageWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return try {
            val messageId = inputData.getLong("message_id", -1)
            val phoneNumber = inputData.getString("phone_number") ?: ""
            val messageText = inputData.getString("message") ?: ""

            if (messageId == -1L || phoneNumber.isEmpty() || messageText.isEmpty()) {
                return Result.failure()
            }

            // Show notification to user
            showNotification(phoneNumber, messageText)

            // Do not mark as sent until user acts
            // Optionally, you can update status to "notified" if you add that state

            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.retry()
        }
    }
    private fun showNotification(phoneNumber: String, message: String) {
        val cleanNumber = phoneNumber.replace(Regex("[^\\d+]"), "")
        val uri = Uri.parse("https://wa.me/$cleanNumber?text=${Uri.encode(message)}")
        val intent = Intent(Intent.ACTION_VIEW, uri).apply {
            setPackage("com.whatsapp")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        val pendingIntent = android.app.PendingIntent.getActivity(
            applicationContext,
            0,
            intent,
            android.app.PendingIntent.FLAG_UPDATE_CURRENT or android.app.PendingIntent.FLAG_IMMUTABLE
        )

        val channelId = "whatsapp_scheduler_channel"
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager

        // Create notification channel for Android O+
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = android.app.NotificationChannel(
                channelId,
                "WhatsApp Scheduler",
                android.app.NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        val builder = androidx.core.app.NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Send WhatsApp Message")
            .setContentText("Tap to send to $phoneNumber")
            .setStyle(androidx.core.app.NotificationCompat.BigTextStyle().bigText(message))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(androidx.core.app.NotificationCompat.PRIORITY_HIGH)

        notificationManager.notify(System.currentTimeMillis().toInt(), builder.build())
    }
}