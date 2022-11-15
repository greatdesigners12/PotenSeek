package com.example.potenseek.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.potenseek.R
import com.example.potenseek.models.Schedule
import com.example.potenseek.ui.theme.OpenSans
import kotlinx.datetime.LocalDate

@Composable
fun ScheduleCard(
    schedule: Schedule,
    modifier: Modifier = Modifier,
    elevation: Dp = 8.dp
) {
    Card (
        shape = RoundedCornerShape(16.dp),
        elevation = elevation,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.carbon_light),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(75.dp).padding(16.dp)
            )
            Column(

            ) {
                Text(
                    text = schedule.title,
                    fontSize = 20.sp,
                    fontFamily = OpenSans,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 8.dp, bottom = 2.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp, top = 4.dp)
                ) {
                    Column() {
                        Text(
                            text = "With ${schedule.with}",
                            fontSize = 16.sp,
                            fontFamily = OpenSans,
                            modifier = Modifier.padding(vertical = 2.dp)
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                text = "${schedule.date}",
                                fontSize = 16.sp,
                                fontFamily = OpenSans,
                                modifier = Modifier.padding(vertical = 2.dp)
                            )
                            Text(
                                text = if (schedule.isOffline) "Offline" else "Online",
                                fontSize = 16.sp,
                                fontFamily = OpenSans,
                                modifier = Modifier.padding(vertical = 2.dp)
                            )
                        }
                    }
                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text(
                            text = "${schedule.hourStart}-${schedule.hourEnd}",
                            fontSize = 16.sp,
                            fontFamily = OpenSans,
                            modifier = Modifier.padding(vertical = 2.dp)
                        )
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            elevation = 8.dp,
                            backgroundColor = Color(0xFFFFAA47)
                        ) {
                            Text(
                                text = if (schedule.isOffline) "Location" else "Link",
                                fontSize = 16.sp,
                                fontFamily = OpenSans,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ScheduleCardPrev() {
    ScheduleCard(
        schedule = Schedule(
            title = "Felix's Piano Lesson",
            with = "Dylan Lie",
            hourStart = "16.00",
            hourEnd = "17.00",
            date = LocalDate.parse("2022-11-14"),
            isOffline = true
        )
    )
}