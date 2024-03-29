package com.example.potenseek.Screens.homeortu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import com.example.potenseek.Navigation.NavigationEnum
import com.example.potenseek.components.ScheduleCard
import com.example.potenseek.components.logoutBtn
import com.example.potenseek.ui.theme.OpenSans
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomeortuScreen(
     navController: NavController, viewModel : HomeortuViewModel
) {
    var state = viewModel.state
    LaunchedEffect(true){
        if(FirebaseAuth.getInstance().currentUser == null){
            navController.popBackStack()
            navController.navigate(NavigationEnum.LoginScreenActivity.name)
        }
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp), horizontalArrangement = Arrangement.SpaceBetween){
            Text(
                text = "Home",
                fontSize = 30.sp,
                fontFamily = OpenSans,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 8.dp, bottom = 12.dp, top = 8.dp)
            )
            logoutBtn(navController = navController)
            
        }
        
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = 8.dp,
            backgroundColor = Color(0xFFFF4E47),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Column {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Schedule",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontFamily = OpenSans,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = 8.dp, top = 6.dp, bottom = 6.dp)
                    )
                    IconButton(
                        onClick = {},
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Icon(Icons.Filled.ArrowForward, "Schedule", tint = Color.White)
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                ) {
                    for (i in 0..(if (state.scheduleList.size > 3) 2 else state.scheduleList.size - 1)) {
                        if (i > 0) Divider(color = Color.Black, thickness = 1.dp)
                        val schedule= state.scheduleList[i]
                        ScheduleCard(
                            schedule = schedule,
                            elevation = 0.dp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                        )
                    }
                }
            }
        }
        Text(
            text = "Child's List",
            fontSize = 30.sp,
            fontFamily = OpenSans,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 8.dp, bottom = 12.dp, top = 16.dp)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(state.childList.size) { i ->
                val child = state.childList[i]
                Card(
                    shape = RoundedCornerShape(16.dp),
                    elevation = 8.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row() {
                        Box(
                            modifier = Modifier
                                .size(130.dp)
                                .padding(16.dp)
                                .background(Color.Black)
                        )
                        Column(
                            modifier = Modifier.padding(start = 8.dp)
                        ) {
                            child.name?.let {
                                Text(
                                    text = it,
                                    fontSize = 36.sp,
                                    fontFamily = OpenSans,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.padding(vertical = 8.dp)
                                )
                            }
                            child.age?.let {
                                Text(
                                    text = "$it Years Old",
                                    fontSize = 24.sp,
                                    fontFamily = OpenSans,
                                )
                            }
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(end = 16.dp, top = 8.dp, bottom = 8.dp)
                            ) {
                                Button(
                                    shape = RoundedCornerShape(16.dp),
                                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFFAA47)),
                                    onClick = { navController.navigate(NavigationEnum.StatisticActivity.name)}
                                ) {
                                    Text(
                                        text = "Statistic",
                                        fontSize = 16.sp,
                                        fontFamily = OpenSans,
                                        fontWeight = FontWeight.SemiBold,
                                    )
                                }
                                Button(
                                    shape = RoundedCornerShape(16.dp),
                                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFFE266)),
                                    onClick = { navController.navigate(NavigationEnum.profileChildScreenActivity.name) }
                                ) {
                                    Text(
                                        text = "Profile",
                                        fontSize = 16.sp,
                                        fontFamily = OpenSans,
                                        fontWeight = FontWeight.SemiBold,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}

