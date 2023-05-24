package com.example.potenseek.Screens.anak.gamessample

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.example.potenseek.Screens.anak.color
import com.example.potenseek.Screens.anak.ui.theme.PotenSeekTheme
import com.example.potenseek.models.gmsplayed
import com.example.potenseek.tempDataStorage.globalVar
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
    private var startTime: Long = 0L
    private var endTime: Long = 0L
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

    data class State(val food:Pair<Int, Int>, val snake:List<Pair<Int,Int>>)

    class Score{
        companion object{
            var skor = 0
            var lost = false
            var urskor = 0
        }
    }

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
                    delay(210)
                    mutableState.update {
                        val newPosition = it.snake.first().let { poz ->
                            mutex.withLock {
                                Pair(
                                    (poz.first + move.first + BOARD_SIZE) % BOARD_SIZE,
                                    (poz.second + move.second + BOARD_SIZE) % BOARD_SIZE
                                )
                            }
                        }

                        if (newPosition == it.food){
                            snakeLength++
                            Score.skor = snakeLength - 4
                            Score.urskor++
                        }

                        if(it.snake.contains(newPosition)){
                            Score.lost = true
                            snakeLength = 4
                            Score.skor = snakeLength - 4
                            globalVar.snakeScoreList.add(Score.skor)
                            if(Score.skor >= 20){
                                var games = gmsplayed.games
                                for (i in games) {
                                    if (i.title == "Snake") {
                                        i.attemptPass++
                                    }
                                }
                            }
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
        val contxt = LocalContext.current

        LaunchedEffect(true){
            startTime = System.currentTimeMillis()
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally){
            state.value?.let{
                Board(it)
            }
            if(Score.lost){
                val openDialog = remember { mutableStateOf(false)  }
                AlertDialog(
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    title = {
                        Text(text = "GAME OVER",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp,
                        )
                    },
                    backgroundColor = Color.DarkGray,
                    modifier = Modifier
                        .padding(vertical = 200.dp)
                        .fillMaxSize()
                        .fillMaxWidth(),
                    buttons = {
                        Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(text = "GAME OVER",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 40.sp,
                            )
                            Text(text = "Score: " + Score.urskor.toString(),
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                modifier = Modifier.padding(top = 20.dp)
                            )
                            Row(
                                modifier = Modifier
                                    .padding(15.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                            ) {
                                Button(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f)
                                        .padding(4.dp)
                                        .background(color = Color.Gray),
                                    onClick = { openDialog.value = false
                                        Score.lost = false
                                        Score.urskor = 0
                                        for (i in gmsplayed.games) {
                                            if (i.title == "Snake") {
                                                i.attemptTotal++
                                            }
                                        }},
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color.Gray)
                                ) {
                                    Text("Play Again", color = Color.White,modifier = Modifier.background(color = Color.Gray))
                                }

                                Button(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f)
                                        .padding(4.dp)
                                        .background(color = "#EE4F48".color),
                                    onClick = {
                                        openDialog.value = false
                                        Score.lost = false
                                        endTime = System.currentTimeMillis()
                                        val activityDuration = endTime - startTime
                                        for (i in gmsplayed.games) {
                                            if (i.title == "Snake") {
                                                i.timeElapsed += activityDuration
                                            }
                                        }
                                        finish(contxt)
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = "#EE4F48".color)
                                ) {
                                    Text("Quit", modifier = Modifier.background(color = "#EE4F48".color), color = Color.White,)
                                }
                            }
                        }
                    }

                )
            }
            Buttons{
                game.move = it
            }
        }
    }

    fun finish(contxt: Context) {
        val activity = (contxt as? Activity)

        activity?.finish()
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
            Text(text = "Score", modifier = Modifier.padding(top = 24.dp))
            Text(text = Score.skor.toString())
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
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PotenSeekTheme {

    }
}