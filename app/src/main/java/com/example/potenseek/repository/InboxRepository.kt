package com.example.potenseek.repository

import android.content.ContentValues
import android.util.Log
import com.example.potenseek.Model.Chat
import com.example.potenseek.Model.ParentProfile
import com.example.potenseek.Model.Payment
import com.example.potenseek.Model.TeacherPsychologist
import com.example.potenseek.Utils.FirebaseWrapper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.type.DateTime
import kotlinx.coroutines.tasks.await
import java.time.LocalDateTime
import java.util.Date
import java.util.Objects
import javax.inject.Inject

class InboxRepository @Inject constructor(private val query : FirebaseFirestore, private val queryAuth : FirebaseAuth) {
    suspend fun getInboxData(uID: String) : FirebaseWrapper<List<Any>, Boolean, java.lang.Exception> {
        val dataWrapper = FirebaseWrapper<List<Any>, Boolean, java.lang.Exception>(null, true, null)
        try{
            var temp = arrayListOf<Any>()
            var temp2 = arrayListOf<Chat>()

            temp2.addAll(query.collection("Chats").whereEqualTo("to", uID).get().await().toObjects(
                Chat::class.java).distinctBy { it.to })

            temp2.addAll(query.collection("Chats").whereEqualTo("from", uID).get().await().toObjects(
                Chat::class.java).distinctBy { it.from })
            Log.d(ContentValues.TAG, "getInboxData: ${temp2}")

            temp2.sortedByDescending { (LocalDateTime.parse(it.time)) }

            temp.addAll(temp2)

            temp.addAll(query.collection("Payments").whereEqualTo("to", uID).get().await().toObjects(
                Payment::class.java).sortedByDescending { (LocalDateTime.parse(it.time)) })

            dataWrapper.data = temp

            dataWrapper.loading = false
            Log.d(ContentValues.TAG, "getInboxData: ${dataWrapper.data}")
        }catch(e : Exception){
            dataWrapper.e = e
            dataWrapper.loading = false
            Log.d(ContentValues.TAG, "getInboxDataError: ${e.message}")
        }
        return dataWrapper
    }

    suspend fun getUserData(id: String) : FirebaseWrapper<ParentProfile, Boolean, Exception>{
        val dataWrapper = FirebaseWrapper<ParentProfile, Boolean, java.lang.Exception>(null, true, null)
        try{
            dataWrapper.data = query.collection("UserData").document(id).get().await().toObject(
                ParentProfile::class.java)
            dataWrapper.loading = false
            Log.d(ContentValues.TAG, "getUserData: ${dataWrapper.data}")
        }catch(e : Exception){

            dataWrapper.e = e
            dataWrapper.loading = false
            Log.d(ContentValues.TAG, "getUserDataErr: ${e.message}")
        }
        return dataWrapper
    }
}