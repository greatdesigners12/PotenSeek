package com.example.potenseek.Screens.schedule

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.CalendarView
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.potenseek.R
import com.example.potenseek.Screens.ui.theme.*
import com.example.potenseek.components.ConsultationTimeDialog
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

    val openDialog = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = teacherScheduleViewModel.scheduleData.collectAsState().value.data?.size, key2 = date){
        teacherScheduleViewModel.getScheduleData("0Z0AiQBhYGqUqukSWia7", date)
        Log.d(ContentValues.TAG, "teacherScheduleHomeData: ${teacherScheduleViewModel.scheduleData.value.data}")
        if(teacherScheduleViewModel.scheduleData.value.data != null){
            scheduleSectionLoading.value = false
        }
    }

    Surface(modifier = Modifier
        .background(color = GreyBackground)
        .fillMaxHeight()) {
        Column(modifier = Modifier
            .background(color = GreyBackground)
            .fillMaxHeight()) {
            Text(
                text = "Schedule",
                modifier = Modifier
                    .padding(20.dp, 16.dp),
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold)
            AndroidView(factory = { CalendarView(ContextThemeWrapper(it, R.style.CalendarSchedule))}, update = {
                it.setOnDateChangeListener { calendarView, year, month, day ->
                    date = "$year-${month + 1}-$day"
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
                    .padding(0.dp, 0.dp, 20.dp, 0.dp)
                    .clickable { openDialog.value = true })
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
                            TeacherScheduleCard(tpSchedule = item, teacherScheduleViewModel)
                    }
                }
            }
        }
        if (openDialog.value) {
            val calendar = Calendar.getInstance()
            val hour = calendar[Calendar.HOUR_OF_DAY]
            val minute = calendar[Calendar.MINUTE]

            val year: Int = calendar.get(Calendar.YEAR)
            val month: Int = calendar.get(Calendar.MONTH)
            val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
            calendar.time = Date()

            val time = remember { mutableStateOf("") }

            val timePickerDialog = TimePickerDialog(
                LocalContext.current,
                {_, hour : Int, minute: Int ->
                    time.value = "$hour:$minute"
                }, hour, minute, false
            )

            val date = remember { mutableStateOf("") }

            val datePickerDialog = DatePickerDialog(
                LocalContext.current,
                { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                    date.value = "$year-${month+1}-$dayOfMonth"
                }, year, month, day
            )

            val time2 = remember { mutableStateOf("") }

            val timePickerDialog2 = TimePickerDialog(
                LocalContext.current,
                {_, hour : Int, minute: Int ->
                    time2.value = "$hour:$minute"
                }, hour, minute, false
            )

            var txtField = remember {
                mutableStateOf("")
            }

            var expanded by remember { mutableStateOf(false) }
            var expanded2 by remember { mutableStateOf(false) }

            var parentsLoaded = remember {
                mutableStateOf(false)
            }

            var childLoaded = remember {
                mutableStateOf(false)
            }

            var childIDLoaded = remember {
                mutableStateOf(false)
            }

            var isOffline = remember {
                mutableStateOf(false)
            }

            var selectedParent by remember { mutableStateOf("") }
            var selectedParent2 = remember { mutableStateOf("") }
            var childTF by remember { mutableStateOf(false) }
            var selectedChild by remember { mutableStateOf("") }
            var selectedChild2 = remember { mutableStateOf("") }

            var textfieldSize by remember { mutableStateOf(Size.Zero)}
            var textfieldSize2 by remember { mutableStateOf(Size.Zero)}

            val icon = if (expanded)
                Icons.Filled.KeyboardArrowUp
            else
                Icons.Filled.KeyboardArrowDown

            val icon2 = if (expanded2)
                Icons.Filled.KeyboardArrowUp
            else
                Icons.Filled.KeyboardArrowDown

            LaunchedEffect(key1 =  teacherScheduleViewModel.parentsData.collectAsState().value.data){
                teacherScheduleViewModel.getParentsData()
                Log.d(ContentValues.TAG, "dialogParentsData: ${teacherScheduleViewModel.parentsData.value.data}")
                if(teacherScheduleViewModel.parentsData.value.data != null){
                    parentsLoaded.value = true
                }
            }

            LaunchedEffect(key1 =  selectedParent2.value){
                if (selectedParent2.value != "") {
                    teacherScheduleViewModel.getParentsChildData(selectedParent2.value)
                    delay(1500)
                    Log.d(
                        ContentValues.TAG,
                        "dialogParentsChildData: ${teacherScheduleViewModel.parentsChildData.value.data}"
                    )
                    if (teacherScheduleViewModel.parentsChildData.value.data != null) {
                        childLoaded.value = true
                        childTF = true
                    }
                }
            }

            LaunchedEffect(key1 =  selectedChild2.value){
                Log.e("Testy", teacherScheduleViewModel.parentsID.value.data.toString())
                if (selectedChild2.value != "" && teacherScheduleViewModel.parentsID.value.data != null) {
                    teacherScheduleViewModel.getChildID(selectedChild2.value, teacherScheduleViewModel.parentsID.value.data!!)
                    delay(1000)
                    Log.d(
                        ContentValues.TAG,
                        "dialogChildIDData: ${teacherScheduleViewModel.childID.value.data}"
                    )
                    if (teacherScheduleViewModel.childID.value.data != null) {
                        childIDLoaded.value = true
                    }
                }
            }

            if (parentsLoaded.value && openDialog.value) {
                AlertDialog(text = {
                    Column(modifier = Modifier
                        .wrapContentHeight()
                        .scrollable(
                            state = rememberScrollState(),
                            orientation = Orientation.Vertical
                        )) {
                        Text(
                            text = "Add Consultation Time", fontSize = 18.sp, modifier = Modifier
                                .padding(0.dp, 0.dp, 0.dp, 12.dp), color = Color.Black
                        )
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp, 0.dp, 0.dp, 12.dp)
                                .border(
                                    BorderStroke(
                                        width = 2.dp,
                                        color = Color.LightGray
                                    ),
                                    shape = RoundedCornerShape(20)
                                ),
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                textColor = Color.Black
                            ),
                            placeholder = { Text(text = "Title") },
                            value = txtField.value,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            onValueChange = {
                                txtField.value = it
                            })
                        Column(Modifier.fillMaxWidth()) {
                            TextField(readOnly = true,
                                value = selectedParent,
                                onValueChange = { selectedParent = it },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp, 0.dp, 0.dp, 12.dp)
                                    .onGloballyPositioned { coordinates ->
                                        //This value is used to assign to the DropDown the same width
                                        textfieldSize = coordinates.size.toSize()
                                    }
                                    .border(
                                        BorderStroke(
                                            width = 2.dp,
                                            color = Color.LightGray
                                        ),
                                        shape = RoundedCornerShape(20)
                                    )
                                    .background(color = Color.White),
                                shape = RoundedCornerShape(20),
                                label = { Text("Select Parent") },
                                trailingIcon = {
                                    Icon(icon, "contentDescription",
                                        Modifier.clickable { expanded = !expanded })
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    textColor = Color.Black
                                )
                            )
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                                modifier = Modifier
                                    .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
                            ) {
                                teacherScheduleViewModel.parentsData.collectAsState().value.data!!.forEach { label ->
                                    DropdownMenuItem(onClick = {
                                        selectedParent = label.name!!
                                        selectedParent2.value = label.name!!
                                        expanded = false
                                    }) {
                                        Text(text = label.name!!)
                                    }
                                }
                            }
                        }
                        Column(Modifier.fillMaxWidth()) {
                            TextField(readOnly = true,
                                enabled = childTF,
                                value = selectedChild,
                                onValueChange = { selectedChild = it },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp, 0.dp, 0.dp, 12.dp)
                                    .onGloballyPositioned { coordinates ->
                                        //This value is used to assign to the DropDown the same width
                                        textfieldSize2 = coordinates.size.toSize()
                                    }
                                    .border(
                                        BorderStroke(
                                            width = 2.dp,
                                            color = Color.LightGray
                                        ),
                                        shape = RoundedCornerShape(20)
                                    )
                                    .background(color = Color.White),
                                shape = RoundedCornerShape(20),
                                label = { Text("Select Child") },
                                trailingIcon = {
                                    Icon(icon2, "contentDescription",
                                        if (childTF) {Modifier.clickable { expanded2 = !expanded2 }} else {Modifier})
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    textColor = Color.Black
                                )
                            )
                            DropdownMenu(
                                expanded = expanded2,
                                onDismissRequest = { expanded2 = false },
                                modifier = Modifier
                                    .width(with(LocalDensity.current) { textfieldSize2.width.toDp() })
                            ) {
                                teacherScheduleViewModel.parentsChildData.collectAsState().value.data!!.forEach { label ->
                                    DropdownMenuItem(onClick = {
                                        selectedChild = label.name!!
                                        selectedChild2.value = label.name!!
                                        expanded2 = false
                                    }) {
                                        Text(text = label.name!!)
                                    }
                                }
                            }
                        }
                        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier
                            .padding(0.dp, 0.dp, 0.dp, 0.dp)
                            .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = isOffline.value,
                                onCheckedChange = { isOffline.value = it },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = Orange300
                                )
                            )
                            Text(text = "Offline", color = Color.Black, fontSize = 16.sp)
                        }
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Button(
                                    onClick = {
                                        datePickerDialog.show()
                                    },
                                    colors = ButtonDefaults.buttonColors(backgroundColor = MathColor),
                                    shape = RoundedCornerShape(20),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(text = if (date.value == "") "Pick Consultation Date" else date.value)
                                }
                            }
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp, 8.dp, 0.dp, 0.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "From : ",
                                    fontSize = 16.sp,
                                    color = Color.Black
                                )
                                Button(
                                    onClick = {
                                        timePickerDialog.show()
                                    },
                                    shape = RoundedCornerShape(20),
                                    colors = ButtonDefaults.buttonColors(backgroundColor = MathColor)
                                ) {
                                    Text(text = if (time.value == "") "00.00" else time.value)
                                }
                                Text(
                                    text = " To : ",
                                    fontSize = 16.sp,
                                    color = Color.Black
                                )
                                Button(
                                    onClick = {
                                        timePickerDialog2.show()
                                    },
                                    shape = RoundedCornerShape(20),
                                    colors = ButtonDefaults.buttonColors(backgroundColor = MathColor)
                                ) {
                                    Text(text = if (time2.value == "") "00.00" else time2.value)
                                }
                            }
                        }
                    }
                }, buttons = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            onClick = {
                                openDialog.value = false
                            },
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .padding(0.dp, 0.dp, 8.dp, 8.dp),
                            colors = ButtonDefaults.buttonColors(Close)
                        ) {
                            Text(text = "Close", color = Color.White)
                        }
                        Button(
                            onClick = {
                                if (childTF) {
                                    Log.e(TAG, "Test")
                                    teacherScheduleViewModel.createSchedule(
                                        "DoHw6FR945Ee16v2KyWI",
                                        date.value,
                                        time.value,
                                        time2.value,
                                        isOffline.value,
                                        "aB0k1vBTSaXNSUuO3MwrFf1YBQo1",
                                        "0Z0AiQBhYGqUqukSWia7",
                                        txtField.value
                                    )
                                    openDialog.value = false
                                    Log.e(TAG, "Test")
                                }
                            },
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .padding(0.dp, 0.dp, 24.dp, 8.dp),
                            colors = ButtonDefaults.buttonColors(Save),
                            enabled =
                                (time.value.isNotEmpty() && time2.value.isNotEmpty() && date.value.isNotEmpty() && txtField.value.isNotEmpty() && selectedParent.isNotEmpty() && selectedChild.isNotEmpty())
                        ) {
                            Text(text = "Add", color = Color.White)
                        }
                    }

                }, onDismissRequest = {
                    openDialog.value = false
                },
                    shape = RoundedCornerShape(15.dp)
                )
            }
        }
    }
}
