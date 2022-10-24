package com.example.potenseek.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.potenseek.Model.ChildProfile
import com.example.potenseek.Model.ParentProfile
import com.example.potenseek.Utils.FirebaseWrapper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val query : FirebaseFirestore, private val queryAuth : FirebaseAuth) {

    suspend fun createProfile(parentName : String, childName : String, childAge: Int) : FirebaseWrapper<String, Boolean, Exception>{
        val dataWrapper = FirebaseWrapper<String, Boolean, java.lang.Exception>("", true, null)
        try{
            val data = hashMapOf(
                "parentName" to parentName
            )
            val childData = hashMapOf(
                "parentId" to queryAuth.uid.toString(),
                "name" to childName,
                "age" to childAge
            )
            query.collection("UserData").document(queryAuth.uid.toString()).set(data).await()
            query.collection("ChildData").add(childData)
            dataWrapper.data = "success"
            dataWrapper.loading = false
        }catch(e : Exception){

            dataWrapper.data = "failed"
            dataWrapper.e = e
            dataWrapper.loading = false
        }
        return dataWrapper
    }

    suspend fun getParentData() : FirebaseWrapper<ParentProfile, Boolean, Exception>{
        val dataWrapper = FirebaseWrapper<ParentProfile, Boolean, java.lang.Exception>(null, true, null)
        try{
            dataWrapper.data = query.collection("UserData").document(queryAuth.uid.toString()).get().await().toObject(ParentProfile::class.java)
            dataWrapper.loading = false
            Log.d(TAG, "getParentData: ${dataWrapper.data}")
        }catch(e : Exception){

            dataWrapper.e = e
            dataWrapper.loading = false
            Log.d(TAG, "getParentDataErr: ${e.message}")
        }
        return dataWrapper
    }

    suspend fun getChildrenData() : FirebaseWrapper<List<ChildProfile>, Boolean, java.lang.Exception>{
        val dataWrapper = FirebaseWrapper<List<ChildProfile>, Boolean, java.lang.Exception>(null, true, null)
        try{
            dataWrapper.data = query.collection("ChildData").whereEqualTo("parentId", queryAuth.uid.toString()).get().await().toObjects(ChildProfile::class.java)
            dataWrapper.loading = false
            Log.d(TAG, "getChildrenData: ${dataWrapper.data}")
        }catch(e : Exception){

            dataWrapper.e = e
            dataWrapper.loading = false
            Log.d(TAG, "getChildrenDataErr: ${e.message}")
        }
        return dataWrapper
    }

    suspend fun checkIfUserDataExist() : FirebaseWrapper<String, Boolean, java.lang.Exception>{
        val dataWrapper = FirebaseWrapper<String, Boolean, java.lang.Exception>("", true, null)
        try{
            Log.d(TAG, "checkIfUserDataExist: brooo")
            val childData = query.collection("ChildData").whereEqualTo("parentId", queryAuth.uid.toString()).get().await().isEmpty
            val parentData = query.collection("UserData").document(queryAuth.uid.toString()).get().await().exists()

            if(!childData && parentData){
                dataWrapper.data = "exist"
            }else{
                dataWrapper.data = "not exist"
            }
            dataWrapper.loading = false
        }catch(e : Exception){
            dataWrapper.data = "not exist"
            dataWrapper.e = e
            dataWrapper.loading = false
        }
        return dataWrapper
    }
}