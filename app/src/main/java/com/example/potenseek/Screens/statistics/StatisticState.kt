package com.example.potenseek.Screens.statistics

import com.example.potenseek.models.GamesPlayed

data class StatisticState(
    val gamesPlayedList: List<GamesPlayed> = emptyList()
)
