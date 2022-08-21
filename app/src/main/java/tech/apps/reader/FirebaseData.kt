package tech.apps.reader

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseData {
    private val db = Firebase.firestore
    private val TAG = "Firebase_Data"

    fun readMessageStatus() {
        val docRef = db.collection("messageStatus").document("messageStatus")
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                Log.d(TAG, "Current data: ${snapshot.data}")
            } else {
                Log.d(TAG, "Current data: null")
            }
        }
    }

    fun updateSMSIncoming(smsData: SMSData) {
        db.collection("messageSMS").document().set(smsData).addOnSuccessListener {
            Log.d(TAG, it.toString())
        }.addOnFailureListener{
            Log.d(TAG, it.toString())
        }
    }
}

data class SMSData(val number: String?, val text: String?)