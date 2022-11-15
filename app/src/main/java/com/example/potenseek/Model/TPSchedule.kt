package com.example.potenseek.Model

data class TPSchedule(
    var childUID: String? = null,
    var hourStart: String? = null,
    var hourEnd: String? = null,
    var with: String? = null,
    var date: String? = null,
    var isOffline: Boolean? = null,
    var parentUID: String? = null,
    var teapsyUID: String? = null,
    var title: String? = null
)
