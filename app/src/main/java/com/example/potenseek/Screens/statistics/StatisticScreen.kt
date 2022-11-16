package com.example.potenseek.Screens.statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.example.potenseek.components.PieChart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.potenseek.Navigation.NavigationEnum
import com.example.potenseek.components.GameStat
import com.example.potenseek.ui.theme.*

@Composable
fun StatisticScreen(
    viewModel: StatisticViewModel, navController: NavController
) {
    val state = viewModel.state
    val totalHour = state.gamesPlayedList.sumOf { it.hours }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                        Text(
                            text = "Statistic",
                            fontSize = 22.sp,
                            fontFamily = OpenSans,
                            fontWeight = FontWeight.SemiBold,
                        )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                            navController.navigate(NavigationEnum.HomeParentActivity.name)
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
                .fillMaxSize()
                .padding(bottom = it.calculateBottomPadding()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PieChart(
                progress = state.gamesPlayedList
                    .map { it.hours.toFloat() }
                    .take(4)
            )
            Text(
                text = "Most Played Games",
                fontSize = 20.sp
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                items(state.gamesPlayedList.size) { i ->
                    val gameStat = state.gamesPlayedList[i]
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(18.dp)
                                .clip(CircleShape)
                                .background(listOf(orderOne, orderTwo, orderThree, orderFour)[i])
                        )
                        Text(
                            text = gameStat.title,
                            fontSize = 12.sp
                        )
                    }
                }
            }
            Column(
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(
                    text = "Games",
                    fontSize = 28.sp,
                    modifier = Modifier.padding(start = 16.dp)
                )
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.gamesPlayedList.size) { i ->
                        val gameStat = state.gamesPlayedList[i]
                        GameStat(
                            totalHour = totalHour,
                            data = gameStat,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                    }
                }
            }
        }
    }
    
}

