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
            query.collection("TeacherPsychologistRole").get().addOnCompleteListener { result ->

                for (document in result.result) {
                    var temp = document.toObject(TeacherPsychologistRole::class.java)
                    roles.add(TeacherPsychologistRole(document.id, temp.role))
                }

                dataWrapper.data = roles
            }

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