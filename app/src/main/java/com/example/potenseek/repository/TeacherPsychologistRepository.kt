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

    suspend fun getTeacherPsychologistData(roleID: Int) : FirebaseWrapper<List<TeacherPsychologist>, Boolean, java.lang.Exception> {
        val dataWrapper = FirebaseWrapper<List<TeacherPsychologist>, Boolean, java.lang.Exception>(null, true, null)
        try{
            dataWrapper.data = query.collection("TeacherPsychologistData").whereEqualTo("roleID", "" + roleID).get().await().toObjects(
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

    suspend fun getTeacherPsychologistData(search: String) : FirebaseWrapper<List<TeacherPsychologist>, Boolean, java.lang.Exception> {
        val dataWrapper = FirebaseWrapper<List<TeacherPsychologist>, Boolean, java.lang.Exception>(null, true, null)
        try{
            dataWrapper.data = query.collection("TeacherPsychologistData").orderBy("name").startAt(search).endAt(search+"\uf8ff").get().await().toObjects(
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
            Log.d(ContentValues.TAG, "getCertificateAchievementData: ${dataWrapper.data}")
        }catch(e : Exception){
            dataWrapper.e = e
            dataWrapper.loading = false
            Log.d(ContentValues.TAG, "getCertificateAchievementDataError: ${e.message}")
        }

        return dataWrapper
    }

    suspend fun getSchedule(tid: String, date: String) : FirebaseWrapper<List<TPSchedule>, Boolean, java.lang.Exception> {
        val dataWrapper = FirebaseWrapper<List<TPSchedule>, Boolean, java.lang.Exception>(null, true, null)

        try{
            dataWrapper.data = query.collection("Schedule").whereEqualTo("teapsyUID", tid).whereEqualTo("date", date).get().await().toObjects(
                TPSchedule::class.java)
            dataWrapper.loading = false
            Log.d(ContentValues.TAG, "getScheduleData: ${dataWrapper.data}")
        }catch(e : Exception){
            dataWrapper.e = e
            dataWrapper.loading = false
            Log.d(ContentValues.TAG, "getScheduleDataError: ${e.message}")
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

    suspend fun getParentsData() : FirebaseWrapper<List<ParentProfile>, Boolean, Exception>{
        val dataWrapper = FirebaseWrapper<List<ParentProfile>, Boolean, java.lang.Exception>(null, true, null)
        try{
            dataWrapper.data = query.collection("UserData").whereEqualTo("role", "parent").get().await().toObjects(
                ParentProfile::class.java)
            dataWrapper.loading = false
            Log.d(ContentValues.TAG, "getParentsData: ${dataWrapper.data}")
        }catch(e : Exception){

            dataWrapper.e = e
            dataWrapper.loading = false
            Log.d(ContentValues.TAG, "getParentsDataErr: ${e.message}")
        }
        return dataWrapper
    }

    suspend fun getParentsID(name: String) : FirebaseWrapper<String, Boolean, Exception>{
        val dataWrapper = FirebaseWrapper<String, Boolean, java.lang.Exception>(null, true, null)
        try{
            query.collection("UserData").whereEqualTo("name", name).whereEqualTo("role", "parent").get().addOnCompleteListener {
                dataWrapper.data = it.result.documents.first().id
            }
            dataWrapper.loading = false
            Log.d(ContentValues.TAG, "getParentsIDData: ${dataWrapper.data}")
        }catch(e : Exception){

            dataWrapper.e = e
            dataWrapper.loading = false
            Log.d(ContentValues.TAG, "getParentsIDDataErr: ${e.message}")
        }
        return dataWrapper
    }

    suspend fun getChildID(name: String, pID: String) : FirebaseWrapper<String, Boolean, Exception>{
        val dataWrapper = FirebaseWrapper<String, Boolean, java.lang.Exception>(null, true, null)
        try{
            query.collection("ChildData").whereEqualTo("name", name).whereEqualTo("parentId", pID).get().addOnCompleteListener {
                dataWrapper.data = it.result.documents.first().id
            }
            dataWrapper.loading = false
            Log.d(ContentValues.TAG, "getChildIDData: ${dataWrapper.data}")
        }catch(e : Exception){

            dataWrapper.e = e
            dataWrapper.loading = false
            Log.d(ContentValues.TAG, "getChildIDDataErr: ${e.message}")
        }
        return dataWrapper
    }


    suspend fun deleteSchedule(childUID: String, parentUID: String, teapsyUID: String, title: String) : FirebaseWrapper<String, Boolean, Exception>{
        val dataWrapper = FirebaseWrapper<String, Boolean, java.lang.Exception>(null, true, null)
        try{
            query.collection("Schedule").whereEqualTo("childUID", childUID).whereEqualTo("parentUID", parentUID).whereEqualTo("teapsyUID", teapsyUID).whereEqualTo("title", title).get().addOnCompleteListener {
                query.collection("Schedule").document(it.result.documents.first().id).delete()
            }
            dataWrapper.loading = false
            Log.d(ContentValues.TAG, "getChildIDData: ${dataWrapper.data}")
        }catch(e : Exception){

            dataWrapper.e = e
            dataWrapper.loading = false
            Log.d(ContentValues.TAG, "getChildIDDataErr: ${e.message}")
        }
        return dataWrapper
    }

    suspend fun createSchedule(childUID : String, date : String, hourEnd : String, hourStart : String, isOffline : Boolean, parentUID : String, teapsyUID : String, title : String) : FirebaseWrapper<String, Boolean, Exception>{
        val dataWrapper = FirebaseWrapper<String, Boolean, java.lang.Exception>("", true, null)
        try{
            val schedule = hashMapOf(
                "childUID" to childUID,
                "date" to date,
                "hourEnd" to hourEnd,
                "hourStart" to hourStart,
                "isOffline" to isOffline,
                "parentUID" to parentUID,
                "teapsyUID" to teapsyUID,
                "title" to title,
                "with" to "Gary"
            )

            query.collection("Schedule").add(schedule)
            dataWrapper.data = "success"
            dataWrapper.loading = false
            Log.d(ContentValues.TAG, "createScheduleData: ${dataWrapper.data}")
        }catch(e : Exception){

            dataWrapper.data = "failed"
            dataWrapper.e = e
            dataWrapper.loading = false
            Log.d(ContentValues.TAG, "createScheduleDataErr: ${e.message}")
        }
        return dataWrapper
    }

    suspend fun getParentsChildData(pID: String) : FirebaseWrapper<List<ChildProfile>, Boolean, Exception>{
        val dataWrapper = FirebaseWrapper<List<ChildProfile>, Boolean, java.lang.Exception>(null, true, null)
        try{
            dataWrapper.data = query.collection("ChildData").whereEqualTo("parentId", pID).get().await().toObjects(
                ChildProfile::class.java)
            dataWrapper.loading = false
            Log.d(ContentValues.TAG, "getParentsChildDataRep: ${dataWrapper.data}")
        }catch(e : Exception){

            dataWrapper.e = e
            dataWrapper.loading = false
            Log.d(ContentValues.TAG, "getParentsChildDataErrRep: ${e.message}")
        }
        return dataWrapper
    }
}