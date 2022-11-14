package com.example.potenseek.Screens.profileanak

import com.example.potenseek.Model.ChildProfile
import com.example.potenseek.models.GamesPlayed

data class ProfileanakState(
    val gamesPlayedList: List<GamesPlayed> = emptyList(),
    val childProfile: ChildProfile? = null
)
