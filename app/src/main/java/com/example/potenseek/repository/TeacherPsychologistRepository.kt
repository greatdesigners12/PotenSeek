package com.example.potenseek.repository

import android.content.ContentValues
import android.util.Log
import com.example.potenseek.Model.*
import com.example.potenseek.Utils.FirebaseWrapper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class TeacherPsychologistRepository @Inject constructor(private val query : FirebaseFirestore, private val queryAuth : FirebaseAuth) {
    suspend fun getTeacherPsychologistData() : FirebaseWrapper<List<TeacherPsychologist>, Boolean, java.lang.Exception> {
        val dataWrapper = FirebaseWrapper<List<TeacherPsychologist>, Boolean, java.lang.Exception>(null, true, null)
        try{
            dataWrapper.data = query.collection("TeacherPsychologistData").get().await().toObjects(
                TeacherPsychologist::class.java)
            dataWrapper.loading = false
            Log.d(ContentValues.TAG, "getTeacherPsychologistData: ${dataWrapper.data}")
        }catch(e : Exception){
            dataWrapper.e = e
            dataWrapper.loading = false
            Log.d(ContentValues.TAG, "getTeacherPsychologistDataError: ${e.message}")
        }
        return dataWrapper
    }

    suspend fun getTeacherPsychologistRoleData() : FirebaseWrapper<List<TeacherPsychologistRole>, Boolean, java.lang.Exception> {
        val dataWrapper = FirebaseWrapper<List<TeacherPsychologistRole>, Boolean, java.lang.Exception>(null, true, null)

        try{
            dataWrapper.data = query.collection("TeacherPsychologistRole").get().await().toObjects(
                TeacherPsychologistRole::class.java)
            dataWrapper.loading = false
            Log.d(ContentValues.TAG, "getTeacherPsychologistRoleData: ${dataWrapper.data}")
        }catch(e : Exception){
            dataWrapper.e = e
            dataWrapper.loading = false
            Log.d(ContentValues.TAG, "getTeacherPsychologistRoleDataError: ${e.message}")
        }

        return dataWrapper
    }

    suspend fun getWhatsHot() : FirebaseWrapper<List<WhatsHot>, Boolean, java.lang.Exception> {
        val dataWrapper = FirebaseWrapper<List<WhatsHot>, Boolean, java.lang.Exception>(null, true, null)

        try{
            dataWrapper.data = query.collection("WhatsHot").get().await().toObjects(
                WhatsHot::class.java)
            dataWrapper.loading = false
            Log.d(ContentValues.TAG, "getWhatsHotData: ${dataWrapper.data}")
        }catch(e : Exception){
            dataWrapper.e = e
            dataWrapper.loading = false
            Log.d(ContentValues.TAG, "getWhatsHotDataError: ${e.message}")
        }

        return dataWrapper
    }

    suspend fun getCertificateAchievement(id: String) : FirebaseWrapper<List<CertificateAchievement>, Boolean, java.lang.Exception> {
        val dataWrapper = FirebaseWrapper<List<CertificateAchievement>, Boolean, java.lang.Exception>(null, true, null)

        try{
            dataWrapper.data = query.collection("TeacherPsychologistData").document(id).collection("CertificateAchievement").get().await().toObjects(
                CertificateAchievement::class.java)
            dataWrapper.loading = false
            Log.d(ContentValues.TAG, "getWhatsHotData: ${dataWrapper.data}")
        }catch(e : Exception){
            dataWrapper.e = e
            dataWrapper.loading = false
            Log.d(ContentValues.TAG, "getWhatsHotDataError: ${e.message}")
        }

        return dataWrapper
    }
}