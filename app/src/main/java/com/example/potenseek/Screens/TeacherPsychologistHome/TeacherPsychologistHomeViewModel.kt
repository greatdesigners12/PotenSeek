package com.example.potenseek.Screens.TeacherPsychologistHome

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.potenseek.Model.TeacherPsychologist
import com.example.potenseek.Model.TeacherPsychologistRole
import com.example.potenseek.Utils.FirebaseWrapper
import com.example.potenseek.repository.TeacherPsychologistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeacherPsychologistHomeViewModel @Inject constructor(private val repository: TeacherPsychologistRepository) : ViewModel() {

    var data = MutableStateFlow<FirebaseWrapper<String, Boolean, Exception>>(
        FirebaseWrapper("", false, Exception())
    )

    var teacherPsychologistData = MutableStateFlow<FirebaseWrapper<TeacherPsychologist, Boolean, Exception>>(
        FirebaseWrapper(null, false, Exception())
    )

    var teacherPsychologistRoleData = MutableStateFlow<FirebaseWrapper<ArrayList<TeacherPsychologistRole>, Boolean, Exception>>(
        FirebaseWrapper(null, false, Exception())
    )

    fun getTeacherPsychologistData(){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                teacherPsychologistData.value = repository.getTeacherPsychologistData()
                Log.d(ContentValues.TAG, "getTeacherPsychologistData: ${teacherPsychologistData.value}")
            }catch (e : Exception){
                data.value.data = "failed"
                data.value.e = e
                data.value.loading = false
                Log.d(ContentValues.TAG, "getTeacherPsychologistData: ${e.message}")
            }
        }
    }

    fun getTeacherPsychologistRoleData(){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                teacherPsychologistRoleData.value = repository.getTeacherPsychologistRoleData()
                Log.d(ContentValues.TAG, "getTeacherPsychologistRoleData: ${teacherPsychologistRoleData.value}")
            }catch (e : Exception){
                data.value.data = "failed"
                data.value.e = e
                data.value.loading = false
                Log.d(ContentValues.TAG, "getTeacherPsychologistRoleData: ${e.message}")
            }
        }
    }

}