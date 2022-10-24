package com.example.potenseek.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.potenseek.Utils.FirebaseWrapper
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository @Inject constructor(private val query : FirebaseAuth){
    suspend fun login(email : String, password : String) : FirebaseWrapper<String, Boolean, java.lang.Exception>{
        val dataWrapper = FirebaseWrapper<String, Boolean, java.lang.Exception>("", true, null)
        try{
            dataWrapper.data = "success"
            dataWrapper.loading = false
            query.signInWithEmailAndPassword(email, password).await()
        }catch(e : java.lang.Exception){
            dataWrapper.data = "failed"
            dataWrapper.e = e
            dataWrapper.loading = false
        }
        return dataWrapper
    }

    suspend fun register(email : String, password : String) : FirebaseWrapper<String, Boolean, java.lang.Exception>{
        val dataWrapper = FirebaseWrapper<String, Boolean, java.lang.Exception>("", true, null)
        try{
            query.createUserWithEmailAndPassword(email, password).await()
            dataWrapper.data = "success"
            dataWrapper.loading = false
        }catch(e : java.lang.Exception){
            dataWrapper.data = "failed"
            dataWrapper.e = e
            dataWrapper.loading = false

        }
        return dataWrapper
    }


}