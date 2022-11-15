package com.example.potenseek.Screens.schedule

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.potenseek.Model.ChildProfile
import com.example.potenseek.Model.ParentProfile
import com.example.potenseek.Model.TPSchedule
import com.example.potenseek.Utils.FirebaseWrapper
import com.example.potenseek.repository.InboxRepository
import com.example.potenseek.repository.TeacherPsychologistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class TeacherScheduleViewModel @Inject constructor(private val repository: TeacherPsychologistRepository) : ViewModel() {
    var data = MutableStateFlow<FirebaseWrapper<String, Boolean, Exception>>(FirebaseWrapper("", false, Exception()))

    var scheduleData = MutableStateFlow<FirebaseWrapper<List<TPSchedule>, Boolean, Exception>>(
        FirebaseWrapper(null, false, Exception())
    )

    var parentData = MutableStateFlow<FirebaseWrapper<ParentProfile, Boolean, Exception>>(
        FirebaseWrapper(null, false, Exception())
    )

    var parentsData = MutableStateFlow<FirebaseWrapper<List<ParentProfile>, Boolean, Exception>>(
        FirebaseWrapper(null, false, Exception())
    )

    var parentsChildData = MutableStateFlow<FirebaseWrapper<List<ChildProfile>, Boolean, Exception>>(
        FirebaseWrapper(null, false, Exception())
    )

    var parentsID = MutableStateFlow<FirebaseWrapper<String, Boolean, Exception>>(
        FirebaseWrapper(null, false, Exception())
    )

    var childID = MutableStateFlow<FirebaseWrapper<String, Boolean, Exception>>(
        FirebaseWrapper(null, false, Exception())
    )

    fun getScheduleData(tID: String, date: String){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                scheduleData.value = repository.getSchedule(tID, date)
                Log.d(ContentValues.TAG, "getScheduleData: ${scheduleData.value}")
            }catch (e : Exception){
                scheduleData.value.data = null
                scheduleData.value.e = e
                scheduleData.value.loading = false
                Log.d(ContentValues.TAG, "getScheduleDataErr: ${e.message}")
            }
        }
    }

    fun getParentsData(){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                parentsData.value = repository.getParentsData()
                Log.d(ContentValues.TAG, "getParentsData: ${parentsData.value}")
            }catch (e : Exception){
                parentsData.value.data = null
                parentsData.value.e = e
                parentsData.value.loading = false
                Log.d(ContentValues.TAG, "getParentsDataErr: ${e.message}")
            }
        }
    }

    fun getParentsID(name: String){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                parentsID.value = repository.getParentsID(name)
                Log.d(ContentValues.TAG, "getParentsIDData: ${parentsID.value}")
            }catch (e : Exception){
                parentsID.value.data = null
                parentsID.value.e = e
                parentsID.value.loading = false
                Log.d(ContentValues.TAG, "getParentsIDDataErr: ${e.message}")
            }
        }
    }

    fun getChildID(name: String, pID: String){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                childID.value = repository.getChildID(name, pID)
                Log.d(ContentValues.TAG, "getChildIDData: ${childID.value}")
            }catch (e : Exception){
                childID.value.data = null
                childID.value.e = e
                childID.value.loading = false
                Log.d(ContentValues.TAG, "getChildIDDataErr: ${e.message}")
            }
        }
    }

    fun createSchedule(childUID : String, date : String, hourEnd : String, hourStart : String, isOffline : Boolean, parentUID : String, teapsyUID : String, title : String){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                data.value = repository.createSchedule(childUID, date, hourEnd, hourStart, isOffline, parentUID, teapsyUID, title)
                Log.d(ContentValues.TAG, "createScheduleData: ${data.value}")
            }catch (e : Exception){
                data.value.data = null
                data.value.e = e
                data.value.loading = false
                Log.d(ContentValues.TAG, "createScheduleData: ${e.message}")
            }
        }
    }

    fun getParentsChildData(name: String){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                getParentsID(name)

                delay(1000)

                parentsChildData.value = repository.getParentsChildData(parentsID.value.data!!)

                Log.d(ContentValues.TAG, "getParentsChildData: ${parentsChildData.value.data}")

                parentsID.value.data = null
            }catch (e : Exception){
                parentsChildData.value.data = null
                parentsChildData.value.e = e
                parentsChildData.value.loading = false
                Log.d(ContentValues.TAG, "getParentsChildDataErr: ${e.printStackTrace()}")
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

    fun deleteSchedule(tpSchedule: TPSchedule){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                repository.deleteSchedule(tpSchedule.childUID!!, tpSchedule.parentUID!!, tpSchedule.teapsyUID!!, tpSchedule.title!!)
                var temp = scheduleData.value.data as ArrayList
                temp.remove(tpSchedule)
                scheduleData.value.data = temp
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