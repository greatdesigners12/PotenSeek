package com.example.potenseek.Screens.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.potenseek.components.GameStat
import com.example.potenseek.components.ScheduleCard
import com.example.potenseek.ui.theme.OpenSans
import com.himanshoe.kalendar.Kalendar
import com.himanshoe.kalendar.color.KalendarThemeColor
import com.himanshoe.kalendar.model.KalendarEvent
import com.himanshoe.kalendar.model.KalendarType
import kotlinx.datetime.LocalDate

@Composable
fun ScheduleScreen(
    viewModel: ScheduleViewModel = hiltViewModel()
) {
    var state = viewModel.state

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Schedule",
                        fontSize = 22.sp,
                        fontFamily = OpenSans,
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {},
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
            Kalendar(
                kalendarType = KalendarType.Firey,
                kalendarThemeColor = KalendarThemeColor(
                    backgroundColor = Color.White,
                    dayBackgroundColor = Color(0xFFFFAA47),
                    headerTextColor = Color.Black
                )
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(state.scheduleList.size) { i ->
                    val schedule= state.scheduleList[i]
                    ScheduleCard(
                        schedule = schedule,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ScheduleScreenPrev() {
    ScheduleScreen()
}