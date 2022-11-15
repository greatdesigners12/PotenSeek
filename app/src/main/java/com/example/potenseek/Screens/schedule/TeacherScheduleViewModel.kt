package com.example.potenseek.Screens.schedule

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.potenseek.Model.ParentProfile
import com.example.potenseek.Model.TPSchedule
import com.example.potenseek.Utils.FirebaseWrapper
import com.example.potenseek.repository.InboxRepository
import com.example.potenseek.repository.TeacherPsychologistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeacherScheduleViewModel @Inject constructor(private val repository: TeacherPsychologistRepository) : ViewModel() {
    var scheduleData = MutableStateFlow<FirebaseWrapper<List<TPSchedule>, Boolean, Exception>>(
        FirebaseWrapper(null, false, Exception())
    )

    var parentData = MutableStateFlow<FirebaseWrapper<ParentProfile, Boolean, Exception>>(
        FirebaseWrapper(null, false, Exception())
    )

    fun getScheduleData(uID: String, date: String){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                scheduleData.value = repository.getSchedule(uID, date)
                Log.d(ContentValues.TAG, "getScheduleData: ${scheduleData.value}")
            }catch (e : Exception){
                scheduleData.value.data = listOf()
                scheduleData.value.e = e
                scheduleData.value.loading = false
                Log.d(ContentValues.TAG, "getScheduleDataErr: ${e.message}")
            }
        }
    }

    fun getUserData(uID: String){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                parentData.value = repository.getUserData(uID)
                Log.d(ContentValues.TAG, "getUserData: ${parentData.value}")
            }catch (e : Exception){
                parentData.value.data = null
                parentData.value.e = e
                parentData.value.loading = false
                Log.d(ContentValues.TAG, "getUserDataErr: ${e.message}")
            }
        }
    }
}