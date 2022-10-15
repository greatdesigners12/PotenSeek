package com.example.potenseek.Screens.authentication

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.potenseek.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository : AuthRepository) : ViewModel() {
    val status = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>(false)

    fun login(email : String, password : String){
        loading.value = true
        viewModelScope.launch(Dispatchers.IO){
            try{
                val result = authRepository.login(email, password)
                status.value = result.data
                loading.value = result.loading
            }catch (e : java.lang.Exception){
                val result = authRepository.login(email, password)
                status.value = result.e?.message
                loading.value = result.loading
            }

        }

    }

    fun register(email : String, password : String){
        loading.value = true
        viewModelScope.launch(Dispatchers.Main){
            try{
                val result = authRepository.register(email, password)
                status.value = result.data
                loading.value = result.loading
            }catch (e : java.lang.Exception){
                val result = authRepository.login(email, password)
                status.value = result.e?.message
                loading.value = result.loading
            }

        }
    }
}