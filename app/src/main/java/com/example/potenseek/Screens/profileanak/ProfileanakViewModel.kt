package com.example.potenseek.Screens.profileanak

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.potenseek.Model.ChildProfile
import com.example.potenseek.models.GamesPlayed
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileanakViewModel @Inject constructor(

): ViewModel() {

    var state by mutableStateOf(ProfileanakState())

    init {
        state = state.copy(
            gamesPlayedList = listOf(
                GamesPlayed(
                    title = "Sing'A'Long",
                    timeElapsed = 70,
                    attemptPass = 1231,
                    attemptTotal = 1421
                ),
                GamesPlayed(
                    title = "AnimaLove",
                    timeElapsed = 10,
                    attemptPass = 70,
                    attemptTotal = 123
                ),
                GamesPlayed(
                    title = "Trainager",
                    timeElapsed = 10,
                    attemptPass = 40,
                    attemptTotal = 107
                ),
                GamesPlayed(
                    title = "ColorIT",
                    timeElapsed = 10,
                    attemptPass = 35,
                    attemptTotal = 93
                )
            ),
            childProfile = ChildProfile(
                name = "Felix",
                age = 5
            )
        )
    }

}