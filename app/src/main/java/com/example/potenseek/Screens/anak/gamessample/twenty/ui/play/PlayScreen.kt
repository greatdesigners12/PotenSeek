package com.example.potenseek.Screens.anak.gamessample.twenty.ui.play

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.potenseek.R
import com.example.potenseek.Screens.anak.gamessample.twenty.model.Game
import com.example.potenseek.Screens.anak.gamessample.twenty.ui.components.Footer
import com.example.potenseek.Screens.anak.gamessample.twenty.ui.components.GameBoard
import com.example.potenseek.Screens.anak.gamessample.twenty.ui.components.Header
import com.forntoh.twofoureight.ui.theme.GameTheme
import com.forntoh.twofoureight.ui.theme.Padding
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun PlayScreen(
    score: Int,
    bestScore: Int,
    moves: Int,
    game: Game,
    grid: List<IntArray>,
    timeElapsed: Long,
    modifier: Modifier = Modifier,
    onNewRequest: () -> Unit = {},
    onUndoRequest: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .statusBarsPadding()
            .padding(Padding.large),
        verticalArrangement = Arrangement.spacedBy(Padding.large),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Header(
            score = score,
            bestScore = bestScore,
            onNewRequest = onNewRequest,
            onUndoRequest = onUndoRequest,
        )

        Text(text = stringResource(R.string.play_prompt))

        GameBoard(
            grid = grid,
            onSwipe = game::swipe
        )

        Spacer(modifier = Modifier.weight(1f))

        Footer(
            moves = moves,
            secondsElapsed = timeElapsed
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PlayScreenPreview() {
    val game = Game(4)
    GameTheme {
        PlayScreen(
            score = 24,
            bestScore = 1024,
            moves = 10,
            timeElapsed = 36000L,
            game = game,
            grid = game.grid
        )
    }
}