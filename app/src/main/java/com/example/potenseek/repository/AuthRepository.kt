package com.example.potenseek.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.potenseek.Utils.FirebaseWrapper
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class AuthRepository @Inject constructor(private val query : FirebaseAuth){
    fun login(email : String, password : String) : FirebaseWrapper<String, Boolean, java.lang.Exception>{
        val dataWrapper = FirebaseWrapper<String, Boolean, java.lang.Exception>("", true, null)
        try{
            query.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                dataWrapper.data = "success"
            }.addOnFailureListener {
                dataWrapper.data = "failed"
                dataWrapper.e = it
            }
            dataWrapper.loading = false
        }catch(e : java.lang.Exception){
            dataWrapper.data = "failed"
            dataWrapper.e = e
            dataWrapper.loading = false
        }
        return dataWrapper
    }

    fun register(email : String, password : String) : FirebaseWrapper<String, Boolean, java.lang.Exception>{
        val dataWrapper = FirebaseWrapper<String, Boolean, java.lang.Exception>("", true, null)
        try{
            query.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                dataWrapper.data = "success"
            }.addOnFailureListener {
                dataWrapper.data = "failed"
                dataWrapper.e = it
                Log.d(TAG, "register: ${it.message}")
            }

            dataWrapper.loading = false
        }catch(e : java.lang.Exception){
            dataWrapper.data = "failed"
            dataWrapper.e = e
            dataWrapper.loading = false
            Log.d(TAG, "register: ${e.message}")
        }
        return dataWrapper
    }
}