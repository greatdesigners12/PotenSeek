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
                    title = "Snake Game",
                    timeElapsed = 70,
                    attemptPass = 1231,
                    attemptTotal = 1421
                ),
                GamesPlayed(
                    title = "2048 Game",
                    timeElapsed = 10,
                    attemptPass = 70,
                    attemptTotal = 123
                )
            )
        )
    }

}