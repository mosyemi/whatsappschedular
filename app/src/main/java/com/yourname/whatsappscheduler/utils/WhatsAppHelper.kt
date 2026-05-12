package com.yourname.whatsappscheduler.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

object WhatsAppHelper {
    fun openWhatsApp(context: Context, phoneNumber: String, message: String): Boolean {
        return try {
            val cleanNumber = PhoneNumberFormatter.toWhatsAppNumber(phoneNumber)
            val uri = Uri.parse("https://wa.me/$cleanNumber?text=${Uri.encode(message)}")
            val intent = Intent(Intent.ACTION_VIEW, uri).apply {
                setPackage("com.whatsapp")
            }
            context.startActivity(intent)
            true
        } catch (e: Exception) {
            false
        }
    }
}
