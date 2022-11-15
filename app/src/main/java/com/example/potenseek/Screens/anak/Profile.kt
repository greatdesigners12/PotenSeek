package com.example.potenseek.Screens.anak

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.potenseek.R
import com.example.potenseek.ui.theme.PotenSeekTheme
import kotlinx.coroutines.launch

class ProfileActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PotenSeekTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.primary
                ) {
                    profile()
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun profile() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            shape = RoundedCornerShape(50.dp),
            backgroundColor = "#EE4F48".color,
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 420.dp)
                .offset(y = (-100).dp)
                .padding(vertical = 1.dp)
        ){
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .offset(y = (120).dp)
                    .fillMaxWidth()
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(60.dp)
                        .align(Alignment.Start)
                        .padding(start = 20.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "profileIMG",
                    modifier = Modifier.clip(RoundedCornerShape(10.dp))
                )

                class child(var username : String, var fname : String)
                var childs = child("BestPlayer123", "Felix")

                Row() {
                    Text(
                        color = Color.White,
                        text = childs.username,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    val openDialog = remember { mutableStateOf(false)  }
                    var text by remember { mutableStateOf(childs.username) }

                    Icon(
                        Icons.Default.Edit,
                        null,
                        tint = Color.White,
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .clickable { openDialog.value = true }
                    )

                    if (openDialog.value) {

                        AlertDialog(
                            onDismissRequest = {
                                openDialog.value = false
                            },
                            title = {
                                Text(text = "Display Name")
                            },
                            text = {
                                Column() {
                                    TextField(
                                        value = text,
                                        onValueChange = { text = it }
                                    )
                                }
                            },
                            buttons = {
                                Row(
                                    modifier = Modifier.padding(horizontal = 12.dp).fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                ) {
                                    Button(
                                        modifier = Modifier.fillMaxWidth().weight(1f).padding(4.dp).background(color = Color.Gray),
                                        onClick = { openDialog.value = false },
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = Color.Gray)
                                    ) {
                                        Text("Cancel", modifier = Modifier.background(color = Color.Gray))
                                    }

                                    Button(
                                        modifier = Modifier.fillMaxWidth().weight(1f).padding(4.dp).background(color = "#EE4F48".color),
                                        onClick = {
                                            openDialog.value = false
                                            childs.username = text
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = "#EE4F48".color)
                                    ) {
                                        Text("Save", modifier = Modifier.background(color = "#EE4F48".color), color = Color.White,)
                                    }
                                }
                            }
                        )
                    }
                }


                Text(text = childs.fname)

            }
        }

        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .offset(y = (-60).dp)
        ){
            class child(var age : Int)
            var data = ArrayList<child>()
            var dummy = child(5)
            data.add(dummy)
            Row() {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "GameIcon",
                    modifier = Modifier.weight(1f)
                )
                
                Column(
                    modifier = Modifier
                        .weight(3f)
                        .padding(start = 20.dp, top = 10.dp)
                ) {
                    Text(
                        text = "Age",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black,
                    )
                    Text(
                        text = dummy.age.toString() + " years old",
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }
            }
        }

        Text(
            text = "Achievements",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.offset(y = (-30).dp)
        )


        class achievement(var title : String)
        var data = ArrayList<achievement>()
        var dummy = achievement("dummy")
        data.add(dummy)
        data.add(dummy)
        data.add(dummy)

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 24.dp)
                .offset(y = (-10).dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),

        ){
            items(data) { achievement ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "GameIcon",
                        modifier = Modifier.clip(RoundedCornerShape(10.dp))
                    )
                    Text(text = achievement.title)
                }
            }
        }
    }
    

}