package com.example.potenseek.Screens.profileanak

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Grade
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.potenseek.ui.theme.*

@Composable
fun ProfileanakScreen(
    viewModel: ProfileanakViewModel = hiltViewModel(),
    navController: NavController
) {

    var state = viewModel.state

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Profile",
                        fontSize = 22.sp,
                        fontFamily = OpenSans,
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                                  navController.popBackStack()

                        },
                        modifier = Modifier
                    ) {
                        Icon(Icons.Filled.ArrowBack, "Back")
                    }
                },
                backgroundColor = Color.White,
                elevation = 0.dp
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = it.calculateBottomPadding())
                .verticalScroll(rememberScrollState())
        ) {
            Card(
                backgroundColor = Color(0xFFFF4E47),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row() {
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .padding(16.dp)
                            .background(Color.Black)
                    )
                    Column() {
                        Text(
                            text = "${state.childProfile?.name}",
                            fontSize = 30.sp,
                            fontFamily = OpenSans,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 6.dp)
                        )
                        Text(
                            text = "Age: ${state.childProfile?.age} Years Old",
                            fontSize = 20.sp,
                            fontFamily = OpenSans,
                            color = Color.White
                        )
                        Text(
                            text = "Display: BestPlayer123",
                            fontSize = 20.sp,
                            fontFamily = OpenSans,
                            color = Color.White
                        )
                        Button(
                            onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFFAA47)),
                            modifier = Modifier.padding(vertical = 6.dp)
                        ) {
                            Text(
                                text = "0 Friends",
                                fontSize = 16.sp,
                                fontFamily = OpenSans,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                        }
                    }
                }
            }
            Text(
                text = "Lessons",
                fontSize = 30.sp,
                fontFamily = OpenSans,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                elevation = 8.dp
            ) {
                Row() {
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .padding(16.dp)
                            .background(Color.Black)
                    )
                    Column() {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 8.dp, top = 8.dp, bottom = 6.dp)
                        ) {
                            Text(
                                text = "Dylan's Lie",
                                fontSize = 22.sp,
                                fontFamily = OpenSans,
                                fontWeight = FontWeight.SemiBold,
                            )
                            Icon(Icons.Filled.ThumbUp, "Thumb", tint = Color(0xFFF3DF28))
                        }
                        Row() {
                            Text(
                                text = "Rate : ",
                                fontSize = 18.sp,
                                fontFamily = OpenSans,
                            )
                            Icon(Icons.Filled.Grade, "Grade", tint = Color(0xFFFFCE31))
                            Text(
                                text = "4.81",
                                fontSize = 18.sp,
                                fontFamily = OpenSans,
                            )
                        }
                        Text(
                            text = "2321 Kids Taught",
                            fontSize = 16.sp,
                            fontFamily = OpenSans,
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.padding(bottom = 6.dp)
                        ) {
                            Button(
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color(
                                        0xFFEEBB03
                                    )
                                ),
                                onClick = { /*TODO*/ }
                            ) {
                                Text(
                                    text = "Guitar Lesson",
                                    fontSize = 16.sp,
                                    fontFamily = OpenSans,
                                )
                            }
                            Button(
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color(
                                        0xFFFF4E47
                                    )
                                ),
                                onClick = { /*TODO*/ }
                            ) {
                                Text(
                                    text = "Profile",
                                    fontSize = 16.sp,
                                    fontFamily = OpenSans,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
            Text(
                text = "Top 3 Recommendations",
                fontSize = 30.sp,
                fontFamily = OpenSans,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 12.dp)
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    elevation = 8.dp,
                    backgroundColor = Color(0xFFFFAA47)
                ) {
                    Text(
                        text = "spatial awareness",
                        fontSize = 16.sp,
                        fontFamily = OpenSans,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                    )
                }
                Card(
                    shape = RoundedCornerShape(16.dp),
                    elevation = 8.dp,
                    backgroundColor = Color(0xFFFFAA47)
                ) {
                    Text(
                        text = "future prediction",
                        fontSize = 16.sp,
                        fontFamily = OpenSans,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                    )
                }
                Card(
                    shape = RoundedCornerShape(16.dp),
                    elevation = 8.dp,
                    backgroundColor = Color(0xFFFFAA47)
                ) {
                    Text(
                        text = "calculation",
                        fontSize = 16.sp,
                        fontFamily = OpenSans,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                    )
                }
            }
            Text(
                text = "Favorite Games",
                fontSize = 30.sp,
                fontFamily = OpenSans,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(state.gamesPlayedList.size) { i ->
                    val gameStat = state.gamesPlayedList[i]
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .padding(16.dp)
                                .background(Color.Black)
                        )
                        Text(
                            text = gameStat.title,
                            fontSize = 14.sp
                        )
                    }
                }
            }
            Text(
                text = "Talented Games",
                fontSize = 30.sp,
                fontFamily = OpenSans,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(state.gamesPlayedList.size) { i ->
                    val gameStat = state.gamesPlayedList[i]
                    if ( gameStat.attemptPass.toFloat()/gameStat.attemptTotal.toFloat() >= 0.75) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(50.dp)
                                    .padding(16.dp)
                                    .background(Color.Black)
                            )
                            Text(
                                text = gameStat.title,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
            Text(
                text = "Games Interest",
                fontSize = 30.sp,
                fontFamily = OpenSans,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(state.gamesPlayedList.size) { i ->
                    val gameStat = state.gamesPlayedList[i]
                    if (gameStat.attemptTotal >= 100) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(50.dp)
                                    .padding(16.dp)
                                    .background(Color.Black)
                            )
                            Text(
                                text = gameStat.title,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

