package com.example.potenseek.Screens.teacherpsychologisthomepage

import com.example.potenseek.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.potenseek.Screens.ui.theme.Coral

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TeacherPsychologistCard() {
    val mContext = LocalContext.current

    Surface(
        modifier = Modifier
            .padding(16.dp, 8.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = Color.White),
        shape = RoundedCornerShape(12.dp),
        shadowElevation = 8.dp,
        color = Color.White,
        onClick = {

        }
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .wrapContentHeight()
            ) {
                Card(shape = RoundedCornerShape(200.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.bank_logo),
                        contentDescription = "TeacherPsychologistProfile",
                        modifier = Modifier
                            .size(88.dp, 88.dp)
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(4.dp, 16.dp)
                    .wrapContentHeight()
            ) {
                Text(
                    text = "Teacher/Psychologist Name",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp
                )
                Row(
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(0.dp, 6.dp, 0.dp, 0.dp)
                ) {
                    Text(
                        text = "Rate : ",
                        fontSize = 18.sp,
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically)
                    )
                    Image(
                        painter = painterResource(
                            id = R.drawable.ic_baseline_star_rate_24
                        ),
                        contentDescription = "",
                        modifier = Modifier
                            .size(18.dp, 18.dp)
                            .align(alignment = Alignment.CenterVertically)
                    )
                    Text(
                        text = " 4.81",
                        fontSize = 18.sp,
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically)
                    )
                }
                Text(
                    text = "2124 Cases Solved",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(0.dp, 2.dp, 0.dp, 0.dp)
                )
                Row(
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(0.dp, 12.dp, 0.dp, 0.dp)
                ) {
                    Surface(
                        modifier = Modifier
                            .wrapContentSize()
                            .align(alignment = Alignment.CenterVertically),
                        shape = RoundedCornerShape(25.dp),
                        shadowElevation = 8.dp,
                        color = Color.LightGray
                    ) {
                        Text(
                            text = "Psychologist",
                            fontSize = 15.sp,
                            modifier = Modifier
                                .padding(8.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier,
                        shape = RoundedCornerShape(25.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Coral
                        ),
                        elevation = ButtonDefaults.elevation(10.dp)
                    ) {
                        Text(
                            text = "Profile",
                            fontSize = 15.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }

    }
}