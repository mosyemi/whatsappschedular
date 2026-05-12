package com.yourname.whatsappscheduler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yourname.whatsappscheduler.data.AppDatabase
import com.yourname.whatsappscheduler.data.Message
import com.yourname.whatsappscheduler.utils.SchedulingHelper
import com.yourname.whatsappscheduler.utils.WhatsAppHelper
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ScheduledMessagesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MessageAdapter
    private lateinit var database: AppDatabase
    private var loadMessagesJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scheduled)

        database = AppDatabase.getDatabase(this)

        recyclerView = findViewById(R.id.rvScheduledMessages)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = MessageAdapter(mutableListOf())
        recyclerView.adapter = adapter

        loadScheduledMessages()
    }

    override fun onResume() {
        super.onResume()
        loadScheduledMessages()
    }

    private fun loadScheduledMessages() {
        loadMessagesJob?.cancel()
        loadMessagesJob = lifecycleScope.launch {
            database.messageDao().getAllMessages().collect { messages ->
                adapter.updateMessages(messages)
            }
        }
    }

    inner class MessageAdapter(private var messages: List<Message>) :
        RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_scheduled_message, parent, false)
            return MessageViewHolder(view)
        }

        override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
            val message = messages[position]
            holder.bind(message)
        }

        override fun getItemCount(): Int = messages.size

        fun updateMessages(newMessages: List<Message>) {
            messages = newMessages
            notifyDataSetChanged()
        }

        inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val tvPhoneNumber: TextView = itemView.findViewById(R.id.tvPhoneNumber)
            private val tvMessage: TextView = itemView.findViewById(R.id.tvMessage)
            private val tvTime: TextView = itemView.findViewById(R.id.tvTime)
            private val tvStatus: TextView = itemView.findViewById(R.id.tvStatus)
            private val btnResend: Button = itemView.findViewById(R.id.btnResend)
            private val btnDelete: Button = itemView.findViewById(R.id.btnDelete)

            fun bind(message: Message) {
                tvPhoneNumber.text = message.phoneNumber
                tvMessage.text = message.messageText
                
                val date = Date(message.scheduledTime)
                val formatter = SimpleDateFormat("MMM dd, yyyy - HH:mm", Locale.getDefault())
                tvTime.text = formatter.format(date)
                tvStatus.text = "Status: ${message.status.replaceFirstChar { it.uppercase() }}"

                btnResend.setOnClickListener {
                    val opened = WhatsAppHelper.openWhatsApp(
                        itemView.context,
                        message.phoneNumber,
                        message.messageText
                    )

                    if (!opened) {
                        Toast.makeText(
                            itemView.context,
                            "Could not open WhatsApp for this message.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                btnDelete.setOnClickListener {
                    lifecycleScope.launch {
                        SchedulingHelper.cancelMessage(this@ScheduledMessagesActivity, message.id)
                        database.messageDao().delete(message.id)
                        Toast.makeText(
                            itemView.context,
                            "Message deleted.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}
