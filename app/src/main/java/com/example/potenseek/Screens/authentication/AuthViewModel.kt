package com.example.potenseek.Screens.authentication

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.potenseek.Navigation.NavigationEnum
import com.example.potenseek.Utils.FirebaseWrapper
import com.example.potenseek.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository : AuthRepository) : ViewModel() {
    var data = MutableStateFlow(FirebaseWrapper<String, Boolean, Exception>("", false, Exception()))

    fun login(email : String, password : String){

        viewModelScope.launch(Dispatchers.IO){

            data.value = authRepository.login(email, password)

        }

    }

    fun checkIfUserExist(navController: NavController){
        val curUser = FirebaseAuth.getInstance().currentUser
        if(curUser != null){
            navController.navigate(NavigationEnum.InputUserDetailActivity.name)
            navController.popBackStack()
        }
    }

    fun register(email : String, password : String){

        viewModelScope.launch(Dispatchers.IO){
            data.value = authRepository.register(email, password, "parent")

        }
    }

    fun registerJob(email : String, password : String, fullName : String, role : String){

        viewModelScope.launch(Dispatchers.IO){
            data.value = authRepository.registerJob(email, password, fullName, role)

        }
    }

    fun resetData(){
        data.value = FirebaseWrapper("", false, Exception())
    }
}