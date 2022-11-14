package com.example.potenseek.models

import kotlinx.datetime.LocalDate

data class Schedule(
    val title: String,
    val with: String,
    val hourStart: String,
    val hourEnd: String,
    val date: LocalDate,
    val isOffline: Boolean
)
