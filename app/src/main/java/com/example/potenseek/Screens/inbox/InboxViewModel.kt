package com.example.potenseek.Screens.inbox

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.potenseek.Model.ParentProfile
import com.example.potenseek.Model.TeacherPsychologist
import com.example.potenseek.Utils.FirebaseWrapper
import com.example.potenseek.repository.InboxRepository
import com.example.potenseek.repository.TeacherPsychologistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InboxViewModel @Inject constructor(private val repository: InboxRepository) : ViewModel() {
    var inboxData = MutableStateFlow<FirebaseWrapper<List<Any>, Boolean, Exception>>(
        FirebaseWrapper(null, false, Exception())
    )

    var parentData = MutableStateFlow<FirebaseWrapper<ParentProfile, Boolean, Exception>>(
        FirebaseWrapper(null, false, Exception())
    )

    fun getInboxData(uID: String){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                inboxData.value = repository.getInboxData(uID)
                Log.d(ContentValues.TAG, "getInboxData: ${inboxData.value}")
            }catch (e : Exception){
                inboxData.value.data = listOf()
                inboxData.value.e = e
                inboxData.value.loading = false
                Log.d(ContentValues.TAG, "getInboxDataErr: ${e.message}")
            }
        }
    }

    fun getParentData(id: String){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                parentData.value = repository.getUserData(id)
                Log.d(ContentValues.TAG, "getParentData: ${parentData.value}")
            }catch (e : Exception){
                parentData.value.data = null
                parentData.value.e = e
                parentData.value.loading = false
                Log.d(ContentValues.TAG, "getParentData: ${e.message}")
            }
        }
    }
}