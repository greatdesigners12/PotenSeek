package com.example.potenseek.components

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Radio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.potenseek.models.GamesPlayed
import com.example.potenseek.ui.theme.OpenSans

@Composable
fun GameStat(
    totalHour: Long,
    data: GamesPlayed,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .padding(16.dp)
                    .background(Color.Black)
            ) {

            }
            Column(

            ) {
                Text(
                    text = data.title,
                    fontSize = 22.sp,
                    fontFamily = OpenSans,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 8.dp, bottom = 2.dp)
                )
                Text(
                    text = "${((data.timeElapsed.toDouble()/totalHour.toDouble())*100)}% Activity (${data.timeElapsed} Hours)",
                    fontSize = 14.sp,
                    fontFamily = OpenSans,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
                Text(
                    text = "${data.attemptPass}/${data.attemptTotal} Attempt",
                    fontSize = 14.sp,
                    fontFamily = OpenSans,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(

                    ){
                        if (data.attemptPass.toFloat()/data.attemptTotal.toFloat() >= 0.75) {
                            Icon(Icons.Default.Done, "Good", tint = Color.Green)
                        }
                        else {
                            Icon(Icons.Default.Close, "Bad", tint = Color.Red)
                        }
                        Text(
                            text = "Talented",
                            fontSize = 13.sp,
                            fontFamily = OpenSans,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }
                    Row(

                    ){
                        if (data.attemptTotal >= 100) {
                            Icon(Icons.Default.Done, "Good", tint = Color.Green)
                        }
                        else {
                            Icon(Icons.Default.Close, "Bad", tint = Color.Red)
                        }
                        Text(
                            text = "Interested",
                            fontSize = 13.sp,
                            fontFamily = OpenSans,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun GameStatPrev() {
    GameStat(
        totalHour = 100,
        data = GamesPlayed(
            title = "Sing'A'Long",
            timeElapsed = 70,
            attemptPass = 1231,
            attemptTotal = 1421
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}