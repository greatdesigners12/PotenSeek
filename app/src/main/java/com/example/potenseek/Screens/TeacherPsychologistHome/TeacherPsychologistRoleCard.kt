package com.example.potenseek.Screens.TeacherPsychologistHome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
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
import com.example.potenseek.Model.TeacherPsychologistRole
import com.example.potenseek.R
import com.example.potenseek.Screens.ui.theme.*

@Composable
fun TeacherPsychologistRoleCard(teacherPsychologistRole: TeacherPsychologistRole) {

    var bgcolor: Color

    if (teacherPsychologistRole.role == "Psychologist") {
        bgcolor = Orange300
    } else if (teacherPsychologistRole.role == "Piano Lesson") {
        bgcolor = PianoColor
    } else if (teacherPsychologistRole.role == "Math Lesson") {
        bgcolor = MathColor
    } else {
        bgcolor = GuitarColor
    }

    Surface(
        modifier = Modifier
            .padding(16.dp, 8.dp)
            .wrapContentSize()
            .background(color = GreyBackground),
        shape = RoundedCornerShape(25.dp),
        shadowElevation = 8.dp,
        color = bgcolor,
        onClick = {

        }
    ) {
        Text(
            text = teacherPsychologistRole.role.toString(),
            fontSize = 20.sp,
            modifier = Modifier
                .padding(14.dp, 4.dp)
                .wrapContentWidth()
        )
    }
}
