package com.example.potenseek.models

data class GamesPlayed(
    val title: String,
    var timeElapsed: Long,
    var attemptPass: Int,
    var attemptTotal: Int
)
