package com.example.potenseek.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.potenseek.Model.*
import com.example.potenseek.R
import com.example.potenseek.Screens.teacherpsychologisthomepage.TeacherPsychologistHomeViewModel
import com.example.potenseek.Screens.ui.theme.*
import java.text.NumberFormat
import java.time.LocalDateTime
import java.util.*
import kotlin.math.roundToInt

@Composable
fun profileCard(name : String){
    Column(horizontalAlignment = Alignment.CenterHorizontally){
        Image(painter = painterResource(id = R.drawable.ic_launcher_background), modifier = Modifier
            .width(100.dp)
            .height(100.dp) ,contentDescription = "", contentScale = ContentScale.Fit)
        Text(name)
    }
}

@Composable
fun TeacherScheduleCard(tpSchedule: TPSchedule) {
    Surface(
        modifier = Modifier
            .padding(16.dp, 8.dp, 16.dp, 16.dp)
            .fillMaxWidth()
            .background(color = GreyBackground),
        shape = RoundedCornerShape(12.dp),
        shadowElevation = 4.dp,
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(0.dp, 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth(0.865f)) {
                androidx.compose.material3.Text(
                    text = tpSchedule.from + " - " + tpSchedule.to,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(12.dp, 0.dp, 0.dp, 0.dp)
                )
                androidx.compose.material3.Text(
                    text = tpSchedule.place + " with " + tpSchedule.with,
                    fontSize = 17.sp,
                    modifier = Modifier
                        .padding(12.dp, 0.dp, 0.dp, 0.dp)
                )
            }
            Image(painter = painterResource(id = R.drawable.ic_baseline_close_24), contentDescription = "Add", modifier = Modifier
                .height(36.dp)
                .width(48.dp)
                .padding(0.dp, 0.dp, 12.dp, 0.dp))
        }

    }
}

@Composable
fun TeacherPsychologistCard(teacherPsychologist: TeacherPsychologist, teacherPsychologistRole: List<TeacherPsychologistRole>) {
    var rating = ((teacherPsychologist.totalRating!! / teacherPsychologist.totalParentsRating!!) * 100.0).roundToInt() / 100.0
    var bgcolor: Color

    if (teacherPsychologist.roleID == "1") {
        bgcolor = Orange300
    } else if (teacherPsychologist.roleID == "2") {
        bgcolor = PianoColor
    } else if (teacherPsychologist.roleID == "3") {
        bgcolor = MathColor
    } else {
        bgcolor = GuitarColor
    }

    Surface(
        modifier = Modifier
            .padding(16.dp, 8.dp, 16.dp, 16.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = GreyBackground),
        shape = RoundedCornerShape(12.dp),
        shadowElevation = 4.dp,
        color = Color.White,
        onClick = {

        }
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .background(color = Color.White)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .wrapContentHeight()
            ) {
                Card(shape = RoundedCornerShape(200.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.defaultprofile),
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
                androidx.compose.material3.Text(
                    text = teacherPsychologist.name.toString(),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp
                )
                Row(
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(0.dp, 6.dp, 0.dp, 0.dp)
                ) {
                    androidx.compose.material3.Text(
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
                    androidx.compose.material3.Text(
                        text = " $rating",
                        fontSize = 18.sp,
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically)
                    )
                }
                androidx.compose.material3.Text(
                    text = "${teacherPsychologist.casesSolved} Cases Solved",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(0.dp, 2.dp, 0.dp, 0.dp)
                )
                Row(
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(0.dp, 6.dp, 0.dp, 0.dp)
                ) {
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier,
                        shape = RoundedCornerShape(25.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = bgcolor
                        ),
                        elevation = ButtonDefaults.elevation(6.dp)
                    ) {
                        androidx.compose.material3.Text(
                            text = teacherPsychologistRole[teacherPsychologist.roleID!!.toInt()].role.toString(),
                            fontSize = 15.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Normal
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
                        elevation = ButtonDefaults.elevation(6.dp)
                    ) {
                        androidx.compose.material3.Text(
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

@Composable
fun TeacherPsychologistRoleCard(teacherPsychologistRole: TeacherPsychologistRole, teacherPsychologistHomeViewModel: TeacherPsychologistHomeViewModel) {
    var bgcolor: Color
    var paddingSize: Dp
    if (teacherPsychologistRole.role == "All") {
        paddingSize = 24.dp
    } else {
        paddingSize = 8.dp
    }

    if (teacherPsychologistRole.role == "Psychologist") {
        bgcolor = Orange300
    } else if (teacherPsychologistRole.role == "Piano Lesson") {
        bgcolor = PianoColor
    } else if (teacherPsychologistRole.role == "Math Lesson") {
        bgcolor = MathColor
    } else if (teacherPsychologistRole.role == "Guitar Lesson") {
        bgcolor = GuitarColor
    } else {
        bgcolor = AllColor
    }

    Surface(
        modifier = Modifier
            .padding(paddingSize, 6.dp, 8.dp, 2.dp)
            .wrapContentSize()
            .background(color = GreyBackground),
        shape = RoundedCornerShape(25.dp),
        shadowElevation = 4.dp,
        onClick = {
            if (teacherPsychologistRole.role == "Psychologist") {
                teacherPsychologistHomeViewModel.getTeacherPsychologistData("1")
            } else if (teacherPsychologistRole.role == "Piano Lesson") {
                teacherPsychologistHomeViewModel.getTeacherPsychologistData("2")
            } else if (teacherPsychologistRole.role == "Math Lesson") {
                teacherPsychologistHomeViewModel.getTeacherPsychologistData("3")
            } else if (teacherPsychologistRole.role == "Guitar Lesson") {
                teacherPsychologistHomeViewModel.getTeacherPsychologistData("4")
            } else {
                teacherPsychologistHomeViewModel.getTeacherPsychologistData()
            }
        }
    ) {
        Row(modifier = Modifier
            .wrapContentWidth()
            .background(color = bgcolor)
            .padding(12.dp, 4.dp)) {
            androidx.compose.material3.Text(
                text = teacherPsychologistRole.role.toString(),
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun CertificateAchievementCard(certificateAchievement: CertificateAchievement) {
    var rand = (0..5).random()
    var arr = arrayListOf(R.drawable.medal1, R.drawable.medal2, R.drawable.medal3, R.drawable.medal4, R.drawable.medal5, R.drawable.medal6, R.drawable.ic_baseline_add_24)
    var caption = certificateAchievement.award + " In\n" + certificateAchievement.event

    if (caption.length > 55) {
        caption = caption.substring(0, 50).trim() + "..."
    }

    if (certificateAchievement.award == "add") {
        rand = 6
    }

    Surface(modifier = Modifier
        .wrapContentSize()
        .padding(8.dp, 8.dp)
        .background(color = GreyBackground),
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 6.dp
    ) {
        Column(modifier = Modifier
            .wrapContentWidth()
            .background(color = Red800)
            .height(height = 216.dp)
            .padding(16.dp, 12.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = arr[rand]), contentDescription = "Medal", modifier = Modifier
                .height(88.dp)
                .width(128.dp))
            if (certificateAchievement.award != "add") {
                androidx.compose.material3.Text(
                    text = caption,
                    color = Color.White,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .width(width = 128.dp)
                        .padding(0.dp, 16.dp, 0.dp, 8.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun ChatCard(chat: Chat, parentProfile: ParentProfile) {
    var msg = chat.message.toString().substring(0, 20).trim() + "..."
    var time = LocalDateTime.parse(chat.time).hour.toString() + "." + LocalDateTime.parse(chat.time).minute.toString()
    Surface(
        modifier = Modifier
            .padding(16.dp, 8.dp, 16.dp, 16.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = GreyBackground),
        shape = RoundedCornerShape(12.dp),
        shadowElevation = 8.dp,
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .background(color = Color.White)
        ) {
            Row(modifier = Modifier
                .fillMaxWidth(0.8f),
                verticalAlignment = Alignment.CenterVertically) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(16.dp)
                        .wrapContentHeight()
                ) {
                    Card(shape = RoundedCornerShape(200.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.defaultprofile),
                            contentDescription = "Chat Profile",
                            modifier = Modifier
                                .size(64.dp, 64.dp)
                        )
                    }
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp, 16.dp)
                        .wrapContentHeight()
                ) {
                    androidx.compose.material3.Text(
                        text = parentProfile.name.toString(),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp
                    )
                    androidx.compose.material3.Text(
                        text = msg,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(0.dp, 2.dp, 0.dp, 0.dp),
                        color = Color.Gray
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(4.dp, 16.dp)
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 0.dp, 4.dp),
                    shape = RoundedCornerShape(100.dp),
                    shadowElevation = 4.dp,
                    color = Orange300
                ) {
                    androidx.compose.material3.Text(
                        text = "1",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .background(color = Orange300)
                            .padding(16.dp, 6.dp)
                    )
                }
                androidx.compose.material3.Text(
                    text = time,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(0.dp, 2.dp, 0.dp, 0.dp)
                )
            }
        }
    }
}

@Composable
fun PaymentCard(payment: Payment, parentProfile: ParentProfile) {
    val localeID = Locale("in", "ID")
    val formatRupiah: NumberFormat = NumberFormat.getCurrencyInstance(localeID)

    Surface(
        modifier = Modifier
            .padding(16.dp, 8.dp, 16.dp, 16.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = GreyBackground),
        shape = RoundedCornerShape(12.dp),
        shadowElevation = 8.dp,
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .padding(24.dp, 16.dp)
                .background(color = Color.White)
        ) {
            androidx.compose.material3.Text(
                text = "PotenPay: Pembayaran Berhasil",
                fontSize = 20.sp,
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 4.dp),
                fontWeight = FontWeight.SemiBold
            )
            androidx.compose.material3.Text(
                text = "Pembayaran oleh " + parentProfile.name + " sebesar " + formatRupiah.format(payment.amount) + " berhasil masuk!",
                fontSize = 18.sp,
                modifier = Modifier.padding(0.dp, 2.dp, 0.dp, 0.dp)
            )
        }
    }
}