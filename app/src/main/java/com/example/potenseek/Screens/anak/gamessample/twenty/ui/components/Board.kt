package com.example.potenseek.Screens.anak.gamessample.twenty.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import com.example.potenseek.Screens.anak.gamessample.twenty.model.Game
import com.forntoh.twofoureight.ui.theme.Padding

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun GameBoard(
    grid: List<IntArray>,
    onSwipe: (Game.Swipe) -> Unit = {}
) {
    BoxWithConstraints(
        modifier = Modifier
            .aspectRatio(1f)
            .border(
                width = Dp.Hairline,
                color = MaterialTheme.colors.onSurface.copy(0.25f),
                shape = MaterialTheme.shapes.large
            )
            .padding(Padding.medium)
            .pointerInput(Unit) {
                var direction: Game.Swipe? = null
                detectHorizontalDragGestures(
                    onDragEnd = { direction?.let { onSwipe(it) } },
                ) { change, x ->
                    change.consume()
                    when {
                        x > 50 -> direction = Game.Swipe.RIGHT
                        x < -50 -> direction = Game.Swipe.LEFT
                    }
                }
            }
            .pointerInput(Unit) {
                var direction: Game.Swipe? = null
                detectVerticalDragGestures(
                    onDragEnd = { direction?.let { onSwipe(it) } },
                ) { change, y ->
                    change.consume()
                    when {
                        y > 50 -> direction = Game.Swipe.DOWN
                        y < -50 -> direction = Game.Swipe.UP
                    }
                }
            }
    ) {

        val tileSize = maxWidth / grid.size

        for (i in grid.indices) {
            for (j in grid.indices) {
                GameTile(number = grid[i][j], size = tileSize, i, j)
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun BoardPreview() {
//    GameTheme {
//        GameBoard(game = Game(4))
//    }
//}
//
//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun BoardPreviewDark() {
//    GameTheme {
//        GameBoard(game = Game(4))
//    }
//}