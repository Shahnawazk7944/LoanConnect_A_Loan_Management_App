package com.example.loanconnect.data.local

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.provider.CallLog
import android.provider.ContactsContract
import android.provider.Telephony
import android.telephony.SubscriptionManager
import com.example.loanconnect.domain.model.CallLogs

import com.example.loanconnect.domain.model.Contact
import com.example.loanconnect.domain.model.Message
import javax.inject.Inject

class UserPersonalDataLocalDataSource @Inject constructor(private val context: Context) {
    @SuppressLint("Range")
    fun getContacts(): List<Contact> {
        val contactsList = mutableListOf<Contact>()
        val cursor = context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        cursor?.use {
            while (it.moveToNext()) {
                val name =
                    it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val phone =
                    it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                contactsList.add(Contact(name, phone))
            }
        }
        return contactsList
    }

    @SuppressLint("Range")
    fun getCallLogs(): List<CallLogs> {
        val callLogsList = mutableListOf<CallLogs>()
        val cursor = context.contentResolver.query(
            CallLog.Calls.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        cursor?.use {
            while (it.moveToNext()) {
                val caller = it.getString(it.getColumnIndex(CallLog.Calls.NUMBER))
                val type = getCallType(it.getInt(it.getColumnIndex(CallLog.Calls.TYPE)))
                val duration = it.getString(it.getColumnIndex(CallLog.Calls.DURATION))
                callLogsList.add(CallLogs(caller, type, duration))
            }
        }
        return callLogsList
    }

    private fun getCallType(type: Int): String {
        return when (type) {
            CallLog.Calls.INCOMING_TYPE -> "Incoming"
            CallLog.Calls.OUTGOING_TYPE -> "Outgoing"
            CallLog.Calls.MISSED_TYPE -> "Missed"
            else -> "Unknown"
        }
    }

    @SuppressLint("Range")
    fun getMessages(): List<Message> {
        val messagesList = mutableListOf<Message>()
        val cursor = context.contentResolver.query(
            Telephony.Sms.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        cursor?.use {
            while (it.moveToNext()) {
                val sender = it.getString(it.getColumnIndex(Telephony.Sms.ADDRESS))
                val receiver = "Me"
//                val type = it.getInt(it.getColumnIndex(Telephony.Sms.TYPE))
//                val receiver = if (type == Telephony.Sms.MESSAGE_TYPE_SENT) {
//                    sender
//                } else {
//                  "Me"
//                }
                val message = it.getString(it.getColumnIndex(Telephony.Sms.BODY))
                messagesList.add(Message(sender, receiver, message))
            }
        }
        return messagesList
    }

//    // i thought i can fetch SMS sender/receiver name also but its only work
//    // when contact is saved in phone else it will return null
//    private fun getContactName(phoneNumber: String): String? {
//        val uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber))
//        val cursor = context.contentResolver.query(uri, arrayOf(ContactsContract.PhoneLookup.DISPLAY_NAME), null, null, null)
//        cursor?.use {
//            if (it.moveToFirst()) {
//                return it.getString(it.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME))
//            }
//        }
//        return null
//    }
}