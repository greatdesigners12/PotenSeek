package com.example.potenseek.Screens.homeortu

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.potenseek.Model.ChildProfile
import com.example.potenseek.models.Schedule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.datetime.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeortuViewModel @Inject constructor(

): ViewModel() {

    var state by mutableStateOf(HomeortuState())

    init {
        state = state.copy(
            scheduleList = listOf(
                Schedule(
                    title = "Felix's Piano Lesson",
                    with = "Dylan Lie",
                    hourStart = "16.00",
                    hourEnd = "17.00",
                    date = LocalDate.parse("2022-11-14"),
                    isOffline = true
                ),
                Schedule(
                    title = "Online Consultation Jennifer",
                    with = "Alex M. Psi. T.",
                    hourStart = "19.00",
                    hourEnd = "20.00",
                    date = LocalDate.parse("2022-11-14"),
                    isOffline = false
                ),
                Schedule(
                    title = "Online Consultation Jennifer",
                    with = "Alex M. Psi. T.",
                    hourStart = "20.30",
                    hourEnd = "21.30",
                    date = LocalDate.parse("2022-11-15"),
                    isOffline = false
                )
            ),
            childList = listOf(
                ChildProfile(
                    name = "Felix",
                    age = 5
                ),

            )
        )
    }

}