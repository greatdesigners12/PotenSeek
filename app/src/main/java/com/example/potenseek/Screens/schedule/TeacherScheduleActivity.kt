package com.example.potenseek.Screens.schedule

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.CalendarView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.potenseek.R
import com.example.potenseek.Screens.ui.theme.Grey100
import com.example.potenseek.Screens.ui.theme.GreyBackground
import com.example.potenseek.components.TeacherPsychologistCard
import com.example.potenseek.components.TeacherScheduleCard
import com.example.potenseek.ui.theme.PotenSeekTheme
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.util.*

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
                    }
                }
            }
        }
    }
}

@Composable
fun TeacherSchedule(navController: NavController, teacherScheduleViewModel: TeacherScheduleViewModel) {
    var date by remember {
        mutableStateOf("" + LocalDate.now().dayOfMonth + "-" + LocalDate.now().monthValue + "-" + LocalDate.now().year)
    }

    val scheduleSectionLoading = remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 =  teacherScheduleViewModel.scheduleData.collectAsState().value.data, key2 = date){
        teacherScheduleViewModel.getScheduleData("0Z0AiQBhYGqUqukSWia7", date)
        Log.d(ContentValues.TAG, "teacherScheduleHomeData: ${teacherScheduleViewModel.scheduleData.value.data}")
        if(teacherScheduleViewModel.scheduleData.value.data != null){
            scheduleSectionLoading.value = false
        }
    }

    Surface(modifier = Modifier.background(color = GreyBackground).fillMaxHeight()) {
        Column(modifier = Modifier
            .background(color = GreyBackground).fillMaxHeight()) {
            Text(
                text = "Schedule",
                modifier = Modifier
                    .padding(20.dp, 16.dp),
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold)
            AndroidView(factory = { CalendarView(ContextThemeWrapper(it, R.style.CalendarSchedule))}, update = {
                it.setOnDateChangeListener { calendarView, year, month, day ->
                    date = "$day-${month + 1}-$year"
                }
            }, modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .fillMaxWidth()
                .wrapContentHeight(align = Alignment.CenterVertically))
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 12.dp)) {
                Text(
                    text = "Consultation Time",
                    modifier = Modifier.padding(20.dp, 0.dp),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.SemiBold)
                Image(painter = painterResource(id = R.drawable.ic_baseline_add_circle_outline_24), contentDescription = "Add", modifier = Modifier
                    .height(48.dp)
                    .width(64.dp)
                    .padding(0.dp, 0.dp, 20.dp, 0.dp))
            }
            if (scheduleSectionLoading.value) {
                Spacer(modifier = Modifier.height(60.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                    CircularProgressIndicator()
                }
                Spacer(modifier = Modifier.height(60.dp))
            } else {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    itemsIndexed(items = teacherScheduleViewModel.scheduleData.value.data!!) {index, item ->
                            TeacherScheduleCard(tpSchedule = item)
                    }
                }
            }
        }
    }
}
