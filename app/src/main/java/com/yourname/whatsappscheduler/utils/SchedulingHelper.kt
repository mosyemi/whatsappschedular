package com.yourname.whatsappscheduler.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.yourname.whatsappscheduler.MainActivity
import com.yourname.whatsappscheduler.alarm.AlarmReceiver

object SchedulingHelper {

    fun scheduleMessage(
        context: Context,
        messageId: Long,
        phoneNumber: String,
        messageText: String,
        scheduledTimeMillis: Long
    ): Boolean {
        val now = System.currentTimeMillis()
        val delay = scheduledTimeMillis - now

        if (delay <= 0) {
            return false
        }

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("message_id", messageId)
            putExtra("phone_number", phoneNumber)
            putExtra("message_text", messageText)
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            messageId.toInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val showIntent = PendingIntent.getActivity(
            context,
            messageId.toInt(),
            Intent(context, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        return try {
            alarmManager.setAlarmClock(
                AlarmManager.AlarmClockInfo(scheduledTimeMillis, showIntent),
                pendingIntent
            )
            true
        } catch (e: SecurityException) {
            false
        }
    }

    fun cancelMessage(context: Context, messageId: Long) {
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            messageId.toInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)
        pendingIntent.cancel()
    }
}
