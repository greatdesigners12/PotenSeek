package com.example.potenseek.Screens.schedule

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.CalendarView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.potenseek.R
import com.example.potenseek.Screens.ui.theme.Grey100
import com.example.potenseek.ui.theme.PotenSeekTheme

class TeacherScheduleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PotenSeekTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold() {
                        TeacherSchedule()
                    }
                }
            }
        }
    }
}

@Composable
fun TeacherSchedule() {
    var date by remember {
        mutableStateOf("")
    }

    Surface(modifier = Modifier.background(color = Grey100)) {
        Column {
            Text(
                text = "Schedule",
                modifier = Modifier.padding(20.dp, 16.dp),
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold
            )
            AndroidView(factory = { CalendarView(ContextThemeWrapper(it, R.style.CalendarSchedule))}, update = {
                it.setOnDateChangeListener { calendarView, year, month, day ->

                }
            }, modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .fillMaxWidth())
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPage() {
    TeacherSchedule()
}