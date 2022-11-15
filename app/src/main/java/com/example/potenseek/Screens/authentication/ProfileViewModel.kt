package com.example.potenseek.Screens.authentication

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.potenseek.Model.Account
import com.example.potenseek.Model.ChildProfile

import com.example.potenseek.Navigation.NavigationEnum
import com.example.potenseek.Utils.FirebaseWrapper
import com.example.potenseek.repository.ProfileRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: ProfileRepository) : ViewModel() {

    var data = MutableStateFlow<FirebaseWrapper<String, Boolean, Exception>>(FirebaseWrapper("", false, Exception()))
    var parentData = MutableStateFlow<FirebaseWrapper<Account, Boolean, Exception>>(FirebaseWrapper(null, false, Exception()))
    var childData = MutableStateFlow<ArrayList<ChildProfile>>(ArrayList<ChildProfile>())
    var isExist = MutableStateFlow<FirebaseWrapper<String, Boolean, Exception>>(FirebaseWrapper(null, false, Exception()))



    fun createProfile(parentName : String, role : String, childName : String, childAge : Int){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                data.value = repository.createProfile(parentName, role, childName, childAge)
            }catch (e : Exception){
                data.value.data = "failed"
                data.value.e = e
                data.value.loading = false
            }
        }
    }

    fun getParentData(){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                parentData.value = repository.getParentData()
                Log.d(TAG, "getParentData: ${parentData.value}")
            }catch (e : Exception){
                data.value.data = "failed"
                data.value.e = e
                data.value.loading = false
                Log.d(TAG, "getParentData: ${e.message}")
            }
        }
    }

    fun checkIfUserDataExist(){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                isExist.value = repository.checkIfUserDataExist()

            }catch (e : Exception){
                data.value.data = "not exist"
                data.value.e = e
                data.value.loading = false
            }
        }
    }


    fun getChildData(){
        val query = FirebaseFirestore.getInstance()

        viewModelScope.launch(Dispatchers.IO) {
            query.collection("ChildData").whereEqualTo("parentId", FirebaseAuth.getInstance().currentUser?.uid!!).addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.w(TAG, "listen:error", e)
                    return@addSnapshotListener
                }

                val curData = ArrayList<ChildProfile>()


                for (dc in snapshots!!.documentChanges) {

                    if (dc.type == DocumentChange.Type.ADDED) {
                        curData.add(dc.document.toObject(ChildProfile::class.java))


                    }else if(dc.type == DocumentChange.Type.REMOVED){
                        childData.value.remove(dc.document.toObject(ChildProfile::class.java))
                    }

                    Log.d(TAG, "getChildData: ${dc.document.data}")
                }
                if(curData.size == 1){
                    curData.union(childData.value)
                }

                childData.value = curData




            }
        }



    }

    fun reset(){
        data = MutableStateFlow(FirebaseWrapper("", false, Exception()))
    }
}