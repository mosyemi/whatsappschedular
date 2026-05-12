package com.yourname.whatsappscheduler.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class Message(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val phoneNumber: String,
    val messageText: String,
    val scheduledTime: Long,
    val status: String = "pending"
)