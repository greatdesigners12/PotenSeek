package com.example.potenseek.Screens.statistics

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.potenseek.models.GamesPlayed
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StatisticViewModel @Inject constructor(

): ViewModel() {

    var state by mutableStateOf(StatisticState())

    init {
        state = state.copy(
            gamesPlayedList = listOf(
                GamesPlayed(
                    title = "Sing'A'Long",
                    hours = 70,
                    attemptPass = 1231,
                    attemptTotal = 1421
                ),
                GamesPlayed(
                    title = "AnimaLove",
                    hours = 10,
                    attemptPass = 70,
                    attemptTotal = 123
                ),
                GamesPlayed(
                    title = "Trainager",
                    hours = 10,
                    attemptPass = 40,
                    attemptTotal = 107
                ),
                GamesPlayed(
                    title = "ColorIT",
                    hours = 10,
                    attemptPass = 35,
                    attemptTotal = 93
                )
            )
        )
    }

}