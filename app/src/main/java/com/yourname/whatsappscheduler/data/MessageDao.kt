package com.yourname.whatsappscheduler.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {
    @Insert
    suspend fun insert(message: Message): Long

    @Query("SELECT * FROM messages WHERE status = 'pending' ORDER BY scheduledTime ASC")
    fun getPendingMessages(): Flow<List<Message>>

    @Query("SELECT * FROM messages ORDER BY scheduledTime DESC")
    fun getAllMessages(): Flow<List<Message>>

    @Query("SELECT * FROM messages WHERE status = 'pending' AND scheduledTime > :now ORDER BY scheduledTime ASC")
    suspend fun getPendingMessagesAfter(now: Long): List<Message>

    @Query("UPDATE messages SET status = :status WHERE id = :id")
    suspend fun updateStatus(id: Long, status: String)

    @Query("DELETE FROM messages WHERE id = :id")
    suspend fun delete(id: Long)
}
