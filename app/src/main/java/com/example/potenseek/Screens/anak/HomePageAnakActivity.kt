package com.example.potenseek.Screens.anak

import android.content.Intent
import android.graphics.Color.parseColor
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.potenseek.R
import com.example.potenseek.Screens.anak.gamessample.snakeGameActivity
import com.example.potenseek.Screens.anak.gamessample.twenty.ui.twentyGameActivity
import com.example.potenseek.models.GamesPlayed
import com.example.potenseek.models.gmsplayed
import com.example.potenseek.ui.theme.PotenSeekTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch


//hexcode color
val String.color get() = Color(parseColor(this))

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun homepageanak() {

    PotenSeekTheme {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        ModalDrawer(
            drawerState = drawerState,
            drawerContent = {

                Column(
                    Modifier
                        .fillMaxHeight()
                        .background(color = "#EE4F48".color),
                    verticalArrangement = Arrangement.SpaceBetween) {

                    //All elements
                    Column(modifier = Modifier.padding(top = 24.dp)) {

                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "PotenSeek",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .size(100.dp),
                        )
                        Row(modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp)) {
                            Text(
                                text = "Leaderboard",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 22.sp,
                            )

                            Icon(
                                Icons.Default.Lock,
                                null,
                                tint = Color.White,
                            )
                        }

                        Row(modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp)) {

                            Text(
                                text = "Shop",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 22.sp,
                            )

                            Icon(
                                Icons.Default.Lock,
                                null,
                                tint = Color.White,
                            )
                        }

                        Row(modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp)) {

                            Text(
                                text = "Friends",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 22.sp,
                            )

                            Icon(
                                Icons.Default.Lock,
                                null,
                                tint = Color.White,
                            )
                        }
                    }

                    //LAST ROW
                    Column(
                        modifier = Modifier
                            .weight(1f, false)
                    ) {
                        Text(
                            text = "Account",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                            modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp)
                        )
                        Text(
                            text = "Logout",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                            modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp)
                        )
                    }
                }
            }
        ) {
            // Screen content
            Column() {
                //search box
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 0.dp, top = 20.dp, end = 0.dp, bottom = 0.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Card(
                        shape = RoundedCornerShape(100.dp),
                        backgroundColor = MaterialTheme.colors.surface,
                        modifier = Modifier
                            .padding(vertical = 1.dp, horizontal = 24.dp)
                            .size(width = 300.dp, height = 50.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .height(200.dp)
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Icon(
                                Icons.Default.Menu,
                                null,
                                modifier = Modifier
                                    .clickable {
                                        scope.launch {
                                            drawerState.apply {
                                                if (isClosed) open() else close()
                                            }
                                        }
                                    }
                                    .size(50.dp, 50.dp),
                            )
                            Text(text = "Search...",
                                fontSize = 16.sp,
                                style = MaterialTheme.typography.h4)
                            Image(
                                modifier = Modifier
                                    .size(50.dp, 50.dp),
                                painter = painterResource(id = R.drawable.bigprofile),
                                contentDescription = "PotenSeek",
                            )
                        }
                    }
                }
                //recentgames()
                games()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun recentgames(){

    //dummy data
    class recentGame(var title : String)
    var data = ArrayList<recentGame>()
    var dummy = recentGame("dummy")
    data.add(dummy)
    val context = LocalContext.current


    if(!data.isEmpty()){
        Column(){
            Text(
                text = "Recent Games",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp)
            )

            //recent game lazyrow
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ){
                items(data) { game ->
                    Column() {
                        Image(
                            painter = painterResource(id = R.drawable.icongame),
                            contentDescription = "GameIcon",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.clip(RoundedCornerShape(10.dp)).clickable {
                                context.startActivity(Intent(context, snakeGameActivity::class.java))
                            }.size(80.dp)
                        )
                        Text(
                            text = game.title,
                            textAlign = TextAlign.Center,
                            )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun games(){

    //disini tambah gamenya lex

    class recentGame(var title : String, var activityname: String)
    var data = ArrayList<recentGame>()
    var snake = recentGame("Snake", "snakeGameActivity")
    var twenty = recentGame("2048", "twentyGameActivity")
    data.add(snake)
    data.add(twenty)
    //insert games here
    val context = LocalContext.current

    Column(modifier = Modifier.padding(top = 18.dp)){
        Text(
            text = "Games",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp)
        )
        //game array
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ){
            items(data) { game ->
                Column() {
                    if(game.activityname == "snakeGameActivity"){
                        Image(
                            painter = painterResource(id = R.drawable.snakgame),
                            contentDescription = "GameIcon",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.clip(RoundedCornerShape(10.dp)).clickable {
                                val gamess = gmsplayed.games
                                var test : MutableList<GamesPlayed> = emptyList<GamesPlayed>().toMutableList()
                                for (i in gamess){
                                    if(i.title == "Snake"){
                                        test.add(i)
                                    }
                                }
                                if(test.isNotEmpty()){
                                    var j = 0
                                    for (i in gamess){
                                        if (i.title == "Snake"){
                                            val atmptnew = i.attemptTotal + 1
                                            var updte = GamesPlayed(i.title, i.hours,atmptnew, i.attemptPass)
                                            gmsplayed.games.set(j, updte)
                                        }
                                        j++
                                    }
                                }else{
                                    var snake = GamesPlayed("Snake", 2, 4, 20)
                                    gmsplayed.games.add(snake)
                                }

                                context.startActivity(Intent(context, snakeGameActivity::class.java))
                            }.size(80.dp)
                        )

                        Text(text = game.title)
                    }else if(game.activityname == "twentyGameActivity"){
                        Image(
                            painter = painterResource(id = R.drawable.twentygame),
                            contentDescription = "GameIcon",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.clip(RoundedCornerShape(10.dp)).clickable {
                                val gamess = gmsplayed.games
                                var test : MutableList<GamesPlayed> = emptyList<GamesPlayed>().toMutableList()
                                for (i in gamess){
                                    if(i.title == "2048"){
                                        test.add(i)
                                    }
                                }
                                if(test.isNotEmpty()){
                                    var j = 0
                                    for (i in gamess){
                                        if (i.title == "2048"){
                                            val atmptnew = i.attemptTotal + 1
                                            var updte = GamesPlayed(i.title, i.hours,atmptnew, i.attemptPass)
                                            gmsplayed.games[j] = updte
                                        }
                                        j++
                                    }
                                }else{
                                    var twenty = GamesPlayed("2048", 1, 2, 13)
                                    gmsplayed.games.add(twenty)
                                }

                                    context.startActivity(Intent(context, twentyGameActivity::class.java))
                            }.size(80.dp).border(BorderStroke(5.dp, SolidColor("#e6e6e6".color)))
                        )
                        Text(text = game.title)
                    }
                }
            }
        }
    }
}


