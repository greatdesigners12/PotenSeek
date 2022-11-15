package com.example.potenseek.Screens.editprofile

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.potenseek.Model.CertificateAchievement
import com.example.potenseek.Model.WhatsHot
import com.example.potenseek.Utils.FirebaseWrapper
import com.example.potenseek.repository.TeacherPsychologistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeacherEditProfileViewModel @Inject constructor(private val repository: TeacherPsychologistRepository) : ViewModel() {

    var certificateAchievementData = MutableStateFlow<FirebaseWrapper<List<CertificateAchievement>, Boolean, Exception>>(
        FirebaseWrapper(null, false, Exception())
    )

    fun getCertificateAchievementData(id: String){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                certificateAchievementData.value = repository.getCertificateAchievement(id)
                Log.d(ContentValues.TAG, "getCertificateAchievementData: ${certificateAchievementData.value}")
            }catch (e : Exception){
                certificateAchievementData.value.data = listOf()
                certificateAchievementData.value.e = e
                certificateAchievementData.value.loading = false
                Log.d(ContentValues.TAG, "getCertificateAchievementDataErr: ${e.message}")
            }
        }
    }
}