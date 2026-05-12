package com.yourname.whatsappscheduler.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.yourname.whatsappscheduler.data.AppDatabase
import com.yourname.whatsappscheduler.utils.PhoneNumberFormatter
import com.yourname.whatsappscheduler.utils.SchedulingHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != Intent.ACTION_BOOT_COMPLETED) return

        val pendingResult = goAsync()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val database = AppDatabase.getDatabase(context)
                val messages = database.messageDao().getPendingMessagesAfter(System.currentTimeMillis())

                messages.forEach { message ->
                    SchedulingHelper.scheduleMessage(
                        context,
                        message.id,
                        PhoneNumberFormatter.toWhatsAppNumber(message.phoneNumber),
                        message.messageText,
                        message.scheduledTime
                    )
                }
            } finally {
                pendingResult.finish()
            }
        }
    }
}
