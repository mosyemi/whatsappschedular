package com.yourname.whatsappscheduler.utils

object PhoneNumberFormatter {
    private const val DEFAULT_COUNTRY_CODE = "254"

    fun toWhatsAppNumber(phoneNumber: String): String {
        val compactNumber = phoneNumber
            .trim()
            .replace("\\s".toRegex(), "")
            .replace("-", "")
            .replace("(", "")
            .replace(")", "")

        val digitsOnly = when {
            compactNumber.startsWith("+") -> compactNumber.drop(1)
            compactNumber.startsWith("00") -> compactNumber.drop(2)
            else -> compactNumber
        }.filter { it.isDigit() }

        return when {
            digitsOnly.startsWith(DEFAULT_COUNTRY_CODE) -> digitsOnly
            digitsOnly.startsWith("0") -> DEFAULT_COUNTRY_CODE + digitsOnly.drop(1)
            digitsOnly.length == 9 && (digitsOnly.startsWith("7") || digitsOnly.startsWith("1")) ->
                DEFAULT_COUNTRY_CODE + digitsOnly
            else -> digitsOnly
        }
    }
}
