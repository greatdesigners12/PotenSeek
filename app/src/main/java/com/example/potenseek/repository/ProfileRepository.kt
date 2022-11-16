package com.example.potenseek.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.potenseek.Model.Account
import com.example.potenseek.Model.ChildProfile
import com.example.potenseek.Utils.FirebaseWrapper
import com.example.potenseek.models.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val query : FirebaseFirestore, private val queryAuth : FirebaseAuth) {

    suspend fun createProfile(name : String, role : String, childName : String, childAge: Int) : FirebaseWrapper<String, Boolean, Exception>{
        val dataWrapper = FirebaseWrapper<String, Boolean, java.lang.Exception>("", true, null)
        try{
            val data = hashMapOf(
                "name" to name,
                "role" to role,
                "pin" to ""
            )
            if(role != "teacher"){
                val childData = mutableMapOf<String, Any>(
                    "parentId" to queryAuth.uid.toString(),
                    "name" to childName,
                    "age" to childAge,
                    "pin" to ""
                )
                query.collection("ChildData").add(childData).addOnCompleteListener {
                    childData.put("id", it.result.id)

                }
                query.collection("ChildData").document(childData.get("id").toString()).set(childData).await()

            }

            query.collection("UserData").document(queryAuth.uid.toString()).set(data).await()

            dataWrapper.data = "success"
            dataWrapper.loading = false
        }catch(e : Exception){

            dataWrapper.data = "failed"
            dataWrapper.e = e
            dataWrapper.loading = false
        }
        return dataWrapper
    }

    suspend fun checkIfPinExist(id : String) : FirebaseWrapper<Boolean?, Boolean, java.lang.Exception>{
        val dataWrapper = FirebaseWrapper<Boolean?, Boolean, java.lang.Exception>(null, true, null)
        try{
            val data = query.collection("ChildData").document(id).get().await().toObject(ChildProfile::class.java)
            dataWrapper.data = data?.pin != ""
            dataWrapper.loading = false
            Log.d(TAG, "getParentData: ${data}")
            Log.d(TAG, "getParentData: ${dataWrapper.data}")
            Log.d(TAG, "trueData: ${data?.pin}")
            Log.d(TAG, "id: ${id}")
        }catch(e : Exception){

            dataWrapper.e = e
            dataWrapper.loading = false
            Log.d(TAG, "getParentDataErr: ${e.message}")
        }
        return dataWrapper
    }

    suspend fun checkIfParentPinExist() : FirebaseWrapper<Boolean?, Boolean, java.lang.Exception>{
        val dataWrapper = FirebaseWrapper<Boolean?, Boolean, java.lang.Exception>(null, true, null)
        try{
            val data = query.collection("UserData").document(queryAuth.uid.toString()).get().await().toObject(UserData::class.java)
            dataWrapper.data = data?.pin.toString() != ""
            dataWrapper.loading = false
            Log.d(TAG, "getParentData: ${dataWrapper.data}")
        }catch(e : Exception){

            dataWrapper.e = e
            dataWrapper.loading = false
            Log.d(TAG, "getParentDataErr: ${e.message}")
        }
        return dataWrapper
    }

    suspend fun isPinCorrect(id : String, pin: String) : FirebaseWrapper<Boolean?, Boolean, java.lang.Exception>{
        val dataWrapper = FirebaseWrapper<Boolean?, Boolean, java.lang.Exception>(null, true, null)
        try{
            val data = query.collection("ChildData").document(id).get().await().toObject(ChildProfile::class.java)

            dataWrapper.data = data?.pin == pin
            dataWrapper.loading = false
            Log.d(TAG, "getParentData: ${dataWrapper.data}")
        }catch(e : Exception){

            dataWrapper.e = e
            dataWrapper.loading = false
            Log.d(TAG, "getParentDataErr: ${e.message}")
        }
        return dataWrapper
    }

    suspend fun isParentPinCorrect(pin: String) : FirebaseWrapper<Boolean?, Boolean, java.lang.Exception>{
        val dataWrapper = FirebaseWrapper<Boolean?, Boolean, java.lang.Exception>(null, true, null)
        try{
            val data = query.collection("UserData").document(queryAuth.uid.toString()).get().await().toObject(ChildProfile::class.java)
            dataWrapper.data = data?.pin == pin
            dataWrapper.loading = false
            Log.d(TAG, "data: ${data?.pin}")
            Log.d(TAG, "getParentData: ${dataWrapper.data}")
        }catch(e : Exception){

            dataWrapper.e = e
            dataWrapper.loading = false
            Log.d(TAG, "getParentDataErr: ${e.message}")
        }
        return dataWrapper
    }

    suspend fun addPin(id : String, pin : String) : FirebaseWrapper<Boolean?, Boolean, java.lang.Exception>{
        val dataWrapper = FirebaseWrapper<Boolean?, Boolean, java.lang.Exception>(null, true, null)
        try{

            val data = query.collection("ChildData").document(id).update("pin", pin).await()
            dataWrapper.data = true
            dataWrapper.loading = false
            Log.d(TAG, "getParentData: ${dataWrapper.data}")
        }catch(e : Exception){
            dataWrapper.data = true
            dataWrapper.e = e
            dataWrapper.loading = false
            Log.d(TAG, "getParentDataErr: ${e.message}")
        }
        return dataWrapper
    }

    suspend fun addParentPin( pin : String) : FirebaseWrapper<Boolean?, Boolean, java.lang.Exception>{
        val dataWrapper = FirebaseWrapper<Boolean?, Boolean, java.lang.Exception>(null, true, null)
        try{

            val data = query.collection("UserData").document(queryAuth.uid.toString()).update("pin", pin).await()
            dataWrapper.data = true
            dataWrapper.loading = false
            Log.d(TAG, "getParentData: ${dataWrapper.data}")
        }catch(e : Exception){

            dataWrapper.e = e
            dataWrapper.data = true
            dataWrapper.loading = false
            Log.d(TAG, "getParentDataErr: ${e.message}")
        }
        return dataWrapper
    }

    suspend fun getParentData() : FirebaseWrapper<Account, Boolean, Exception>{
        val dataWrapper = FirebaseWrapper<Account, Boolean, java.lang.Exception>(null, true, null)
        try{
            dataWrapper.data = query.collection("UserData").document(queryAuth.uid.toString()).get().await().toObject(Account::class.java)
            dataWrapper.loading = false
            Log.d(TAG, "getParentData: ${dataWrapper.data}")
        }catch(e : Exception){

            dataWrapper.e = e
            dataWrapper.loading = false
            Log.d(TAG, "getParentDataErr: ${e.message}")
        }
        return dataWrapper
    }

    suspend fun getChildrenData() : FirebaseWrapper<List<ChildProfile>, Boolean, java.lang.Exception> {
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
            val parentData = query.collection("UserData").document(queryAuth.uid.toString()).get().await()

            if(!childData && parentData.exists() && (parentData.data?.get("role") != "Teacher" || parentData.data?.get("role") != "Psychologist")){
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

    suspend fun checkIfJobExist() : FirebaseWrapper<String, Boolean, java.lang.Exception>{
        val dataWrapper = FirebaseWrapper<String, Boolean, java.lang.Exception>("", true, null)
        try{
            val childData = query.collection("UserData").document(queryAuth.uid.toString()).get().await()
            if(childData.exists()){
                if(childData.data?.get("role") == "Teacher" || childData.data?.get("role") == "Psychologist"){
                    dataWrapper.data = "exist"
                }
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