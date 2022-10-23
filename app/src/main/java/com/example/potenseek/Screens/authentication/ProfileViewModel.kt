package com.example.potenseek.Screens.authentication

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.potenseek.Model.ChildProfile
import com.example.potenseek.Model.ParentProfile
import com.example.potenseek.Navigation.NavigationEnum
import com.example.potenseek.Utils.FirebaseWrapper
import com.example.potenseek.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: ProfileRepository) : ViewModel() {

    var data = MutableStateFlow<FirebaseWrapper<String, Boolean, Exception>>(FirebaseWrapper("", false, Exception()))
    var parentData = MutableStateFlow<FirebaseWrapper<ParentProfile, Boolean, Exception>>(FirebaseWrapper(null, false, Exception()))
    var childData = MutableStateFlow<FirebaseWrapper<List<ChildProfile>, Boolean, Exception>>(FirebaseWrapper(null, false, Exception()))
    var isExist = MutableStateFlow<FirebaseWrapper<String, Boolean, Exception>>(FirebaseWrapper(null, false, Exception()))



    fun createProfile(parentName : String, childName : String, childAge : Int){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                data.value = repository.createProfile(parentName, childName, childAge)
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
        viewModelScope.launch(Dispatchers.IO) {
            try{
                childData.value = repository.getChildrenData()
                Log.d(TAG, "getChildData: ${childData.value}")
            }catch (e : Exception){
                data.value.data = "failed"
                data.value.e = e
                data.value.loading = false

            }
        }
    }

    fun reset(){
        data = MutableStateFlow(FirebaseWrapper("", false, Exception()))
    }
}