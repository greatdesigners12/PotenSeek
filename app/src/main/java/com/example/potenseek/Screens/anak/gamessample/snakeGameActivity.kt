package com.example.potenseek.Screens.anak.gamessample

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.potenseek.Screens.anak.color
import com.example.potenseek.Screens.anak.ui.theme.PotenSeekTheme
import com.example.potenseek.ui.theme.Shapes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.*

class snakeGameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val game = Game(lifecycleScope)

        setContent {
            PotenSeekTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    Snake(game)
                }
            }
        }
    }
}



data class State(val food:Pair<Int, Int>, val snake:List<Pair<Int,Int>>)

class Game(private val scope: CoroutineScope){
    private val mutex: Mutex = Mutex()
    private val mutableState: MutableStateFlow<State> = MutableStateFlow(State(food = Pair(5,5), snake = listOf(Pair(7,7))))
    val state: Flow<State> = mutableState
    var move = Pair(1,0)
    set(value: Pair<Int, Int>){
        scope.launch {
            mutex.withLock {
                field = value
            }
        }
    }

    init {
        scope.launch{
            var snakeLength = 4

            while(true){
                delay(150)
                mutableState.update {
                    val newPosition = it.snake.first().let { poz ->
                        mutex.withLock {
                            Pair(
                                poz.first + move.first,
                                poz.second + move.second
                            )
                        }
                    }

                    if (newPosition == it.food){
                        snakeLength++
                    }

                    if(it.snake.contains(newPosition)){
                        snakeLength = 4
                    }

                    it.copy(
                        food = if (newPosition == it.food) {
                            Pair(
                                Random().nextInt(BOARD_SIZE),
                                Random().nextInt(BOARD_SIZE)
                            )
                        }else it.food,
                        snake = listOf(newPosition) + it.snake.take(snakeLength - 1)
                    )
                }

            }
        }
    }

    companion object{
        const val BOARD_SIZE = 16
    }
}

@Composable
fun Snake(game: Game) {
    val state = game.state.collectAsState(initial = null)

    Column(horizontalAlignment = Alignment.CenterHorizontally){
        state.value?.let{
            Board(it)
        }
        Buttons{
            game.move = it
        }
    }
}

@Composable
fun Buttons(onDirectionChange: (Pair<Int, Int>)->Unit) {
    val buttonSize = Modifier.size(64.dp)
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(24.dp)) {
        Button(onClick = { onDirectionChange(Pair(0, -1)) }, modifier = buttonSize) {
            Icon(Icons.Default.KeyboardArrowUp, null)
        }
        Row {
            Button(onClick = { onDirectionChange(Pair(-1, 0)) }, modifier = buttonSize) {
                Icon(Icons.Default.KeyboardArrowLeft, null)
            }
            Spacer(modifier = buttonSize)
            Button(onClick = { onDirectionChange(Pair(1, 0)) }, modifier = buttonSize) {
                Icon(Icons.Default.KeyboardArrowRight, null)
            }
        }
        Button(onClick = { onDirectionChange(Pair(0, 1)) }, modifier = buttonSize) {
            Icon(Icons.Default.KeyboardArrowDown, null)
        }
    }
}

@Composable
fun Board(state: State){
    BoxWithConstraints(Modifier.padding(16.dp)) {
        val tileSize = maxWidth / Game.BOARD_SIZE

        Box(
            Modifier
                .size(maxWidth)
                .border(2.dp, color = "#EE4F48".color)
        )

        Box(
            Modifier
                .offset(x = tileSize * state.food.first, y = tileSize * state.food.second)
                .size(tileSize)
                .background(color = "#EE4F48".color, CircleShape)
        )

        state.snake.forEach{
            Box(modifier = Modifier
                .offset(x = tileSize * it.first, y = tileSize * it.second)
                .size(tileSize)
                .background(color = "#EE4F48".color, Shapes.small))
        }
    }
    

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PotenSeekTheme {

    }
}