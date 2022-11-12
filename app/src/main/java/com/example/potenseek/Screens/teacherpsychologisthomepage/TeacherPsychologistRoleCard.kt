package com.example.potenseek.Screens.teacherpsychologisthomepage

import android.util.Size
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.potenseek.Model.TeacherPsychologistRole
import com.example.potenseek.Screens.ui.theme.*

@Composable
fun TeacherPsychologistRoleCard(teacherPsychologistRole: TeacherPsychologistRole) {

    var bgcolor: Color
    var paddingSize: Dp
    if (teacherPsychologistRole.id == "1") {
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
    } else {
        bgcolor = GuitarColor
    }

    Surface(
        modifier = Modifier
            .padding(paddingSize, 16.dp, 8.dp, 16.dp)
            .wrapContentSize()
            .background(color = GreyBackground),
        shape = RoundedCornerShape(25.dp),
        shadowElevation = 2.dp,
        onClick = {

        }
    ) {
        Row(modifier = Modifier
            .wrapContentWidth()
            .background(color = bgcolor)
            .padding(12.dp, 4.dp)) {
            Text(
                text = teacherPsychologistRole.role.toString(),
                fontSize = 16.sp
            )
        }
    }
}
