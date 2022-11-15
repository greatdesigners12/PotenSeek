package com.example.potenseek.Screens.teacherpsychologisthomepage

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.potenseek.Model.TeacherPsychologist
import com.example.potenseek.Model.TeacherPsychologistRole
import com.example.potenseek.Model.WhatsHot
import com.example.potenseek.Utils.FirebaseWrapper
import com.example.potenseek.repository.TeacherPsychologistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeacherPsychologistHomeViewModel @Inject constructor(private val repository: TeacherPsychologistRepository) : ViewModel() {

    var data = MutableStateFlow<FirebaseWrapper<String, Boolean, Exception>>(
        FirebaseWrapper("", false, Exception())
    )

    var teacherPsychologistData = MutableStateFlow<FirebaseWrapper<List<TeacherPsychologist>, Boolean, Exception>>(
        FirebaseWrapper(null, false, Exception())
    )

    var teacherPsychologistDataAll = MutableStateFlow<FirebaseWrapper<List<TeacherPsychologist>, Boolean, Exception>>(
        FirebaseWrapper(null, false, Exception())
    )

    var teacherPsychologistRoleData = MutableStateFlow<FirebaseWrapper<List<TeacherPsychologistRole>, Boolean, Exception>>(
        FirebaseWrapper(null, false, Exception())
    )

    var whatsHotData = MutableStateFlow<FirebaseWrapper<List<WhatsHot>, Boolean, Exception>>(
        FirebaseWrapper(null, false, Exception())
    )

    fun getTeacherPsychologistData(){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                teacherPsychologistData.value = repository.getTeacherPsychologistData()
                teacherPsychologistDataAll.value = teacherPsychologistData.value
                Log.d(ContentValues.TAG, "getTeacherPsychologistData: ${teacherPsychologistData.value}")
            }catch (e : Exception) {
                teacherPsychologistData.value.data = listOf()
                teacherPsychologistData.value.e = e
                teacherPsychologistData.value.loading = false
                Log.d(ContentValues.TAG, "getTeacherPsychologistData: ${e.message}")
            }
        }
    }

    fun getTeacherPsychologistData(roleID: String){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                teacherPsychologistData.value = repository.getTeacherPsychologistData(roleID)
                Log.d(ContentValues.TAG, "getTeacherPsychologistDataFilter: ${teacherPsychologistData.value}")
            }catch (e : Exception){
                teacherPsychologistData.value.data = listOf()
                teacherPsychologistData.value.e = e
                teacherPsychologistData.value.loading = false
                Log.d(ContentValues.TAG, "getTeacherPsychologistDataFilterErr: ${e.message}")
            }
        }
    }

    fun getTeacherPsychologistRoleData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                teacherPsychologistRoleData.value = repository.getTeacherPsychologistRoleData()
                Log.d(
                    ContentValues.TAG,
                    "getTeacherPsychologistRoleDataVM: ${teacherPsychologistRoleData.value}"
                )
            } catch (e: Exception) {
                teacherPsychologistRoleData.value.data = listOf()
                teacherPsychologistRoleData.value.e = e
                teacherPsychologistRoleData.value.loading = false
                Log.d(ContentValues.TAG, "getTeacherPsychologistRoleDataVMError: ${e.message}")
            }
        }
    }

    fun getWhatsHotData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                whatsHotData.value = repository.getWhatsHot()
                Log.d(
                    ContentValues.TAG,
                    "getTeacherPsychologistRoleDataVM: ${whatsHotData.value}"
                )
            } catch (e: Exception) {
                whatsHotData.value.data = listOf()
                whatsHotData.value.e = e
                whatsHotData.value.loading = false
                Log.d(ContentValues.TAG, "getTeacherPsychologistRoleDataVMError: ${e.message}")
            }
        }
    }

}