package com.example.potenseek.Screens.homeortu

import com.example.potenseek.Model.ChildProfile
import com.example.potenseek.models.Schedule

data class HomeortuState(
    val scheduleList: List<Schedule> = emptyList(),
    val childList: List<ChildProfile> = emptyList()
)
