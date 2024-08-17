package com.example.loanconnect.domain.model

data class Contact(val name: String, val phone: String)
data class ContactRequest(val mobile: String, val contacts: List<Contact>)

data class CallLog(val caller: String, val type: String, val duration: String)
data class CallLogRequest(val mobile: String, val call_logs: List<CallLog>)

data class Message(val sender: String, val receiver: String, val message: String)
data class MessageRequest(val mobile: String, val messages: List<Message>)