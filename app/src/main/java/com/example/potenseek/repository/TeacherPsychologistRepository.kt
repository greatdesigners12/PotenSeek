package com.example.potenseek.repository

import android.content.ContentValues
import android.util.Log
import com.example.potenseek.Model.ParentProfile
import com.example.potenseek.Model.TeacherPsychologist
import com.example.potenseek.Model.TeacherPsychologistRole
import com.example.potenseek.Utils.FirebaseWrapper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class TeacherPsychologistRepository @Inject constructor(private val query : FirebaseFirestore, private val queryAuth : FirebaseAuth) {
    suspend fun getTeacherPsychologistData() : FirebaseWrapper<TeacherPsychologist, Boolean, Exception> {
        val dataWrapper = FirebaseWrapper<TeacherPsychologist, Boolean, java.lang.Exception>(null, true, null)
        try{
            dataWrapper.data = query.collection("TeacherPsychologistData").document().get().await().toObject(
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

    suspend fun getTeacherPsychologistRoleData() : FirebaseWrapper<ArrayList<TeacherPsychologistRole>, Boolean, Exception> {
        val dataWrapper = FirebaseWrapper<ArrayList<TeacherPsychologistRole>, Boolean, java.lang.Exception>(null, true, null)
        var roles = ArrayList<TeacherPsychologistRole>()

        try{
            query.collection("TeacherPsychologistRole").get().addOnCompleteListener {
                if (it.isSuccessful) {
                    var a = 0
                    while (a < it.result.documents.size) {
                        roles.add(TeacherPsychologistRole(it.result.documents[a].id, it.result.documents[a].getString("role")))
                        a++
                    }

                    dataWrapper.data = roles
                } else {
                    Log.e(ContentValues.TAG, "getTPRoleData Error : ${it.exception}")
                }
            }
                .addOnFailureListener {
                    Log.e(ContentValues.TAG, "getTPRoleData Error : ${it.printStackTrace()}")
                }

            dataWrapper.data = roles

            dataWrapper.loading = false
            Log.d(ContentValues.TAG, "getTeacherPsychologistRoleData: ${dataWrapper.data}")
        }catch(e : Exception){
            dataWrapper.e = e
            dataWrapper.loading = false
            Log.d(ContentValues.TAG, "getTeacherPsychologistRoleDataError: ${e.message}")
        }

        return dataWrapper
    }
}