package com.example.potenseek.Screens.schedule

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.provider.Settings.Global
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
import androidx.compose.runtime.snapshots.SnapshotStateList
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
import com.example.potenseek.Model.ChildProfile
import com.example.potenseek.Model.ParentProfile
import com.example.potenseek.Model.TPSchedule
import com.example.potenseek.R
import com.example.potenseek.Screens.ui.theme.*
import com.example.potenseek.components.ConsultationTimeDialog
import com.example.potenseek.components.TeacherPsychologistCard
import com.example.potenseek.components.TeacherScheduleCard
import com.example.potenseek.helper.GlobalVar
import com.example.potenseek.ui.theme.PotenSeekTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

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
    if (GlobalVar.scheduleList.size == 0) {
        GlobalVar.scheduleList.add(
            TPSchedule(
                "1",
                "09.00",
                "10.00",
                "Jill",
                "2022-11-17",
                false,
                "1",
                "1",
                "Online with Gary"
            )
        )
        GlobalVar.scheduleList.add(
            TPSchedule(
                "2",
                "13.00",
                "14.30",
                "Anne",
                "2022-11-18",
                false,
                "1",
                "1",
                "Online with Celine"
            )
        )
        GlobalVar.scheduleList.add(
            TPSchedule(
                "3",
                "12.00",
                "11.00",
                "Kathy",
                "2022-11-18",
                false,
                "2",
                "1",
                "Online with Amanda"
            )
        )
    }

    if (GlobalVar.parentList.size == 0) {
        GlobalVar.parentList.add(ParentProfile("jack", "parent"))
        GlobalVar.parentList.add(ParentProfile("jill", "parent"))
    }

    if (GlobalVar.childList.size == 0) {
        GlobalVar.childList.add(ChildProfile("1", "Gary", 5))
        GlobalVar.childList.add(ChildProfile("1", "Celine", 4))
        GlobalVar.childList.add(ChildProfile("2", "Amanda", 5))
    }

    var date by remember {
        mutableStateOf("" + LocalDate.now().year + "-" + LocalDate.now().monthValue + "-" + LocalDate.now().dayOfMonth)
    }

    val scheduleSectionLoading = remember {
        mutableStateOf(true)
    }

    val openDialog = remember {
        mutableStateOf(false)
    }

    var temp = remember {
        mutableStateOf(listOf<TPSchedule>())
    }

    var tmpp = MutableStateFlow(listOf<TPSchedule>())
    tmpp.value = filterSchedule(date)

//    LaunchedEffect(key1 = teacherScheduleViewModel.scheduleData.collectAsState().value.data, key2 = date){
//        teacherScheduleViewModel.getScheduleData("0Z0AiQBhYGqUqukSWia7", date)
//        Log.d(ContentValues.TAG, "teacherScheduleHomeData: ${teacherScheduleViewModel.scheduleData.value.data}")
//        if(teacherScheduleViewModel.scheduleData.value.data != null){
//            scheduleSectionLoading.value = false
//        }
//    }

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

                    tmpp.value = filterSchedule(date)
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
//            if (scheduleSectionLoading.value) {
//                Spacer(modifier = Modifier.height(60.dp))
//                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
//                    CircularProgressIndicator()
//                }
//                Spacer(modifier = Modifier.height(60.dp))
//            } else {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    itemsIndexed(items = tmpp.value) { index, item ->
                        androidx.compose.material3.Surface(
                            modifier = Modifier
                                .padding(16.dp, 8.dp, 16.dp, 4.dp)
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
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth(0.865f)
                                ) {
                                    androidx.compose.material3.Text(
                                        text = item.hourStart + " - " + item.hourEnd,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        modifier = Modifier
                                            .padding(12.dp, 0.dp, 0.dp, 0.dp)
                                    )
                                    androidx.compose.material3.Text(
                                        text = item.title!!,
                                        fontSize = 17.sp,
                                        modifier = Modifier
                                            .padding(12.dp, 0.dp, 0.dp, 0.dp)
                                    )
                                }
                                Image(painter = painterResource(id = R.drawable.ic_baseline_close_24),
                                    contentDescription = "Delete",
                                    modifier = Modifier
                                        .height(36.dp)
                                        .width(48.dp)
                                        .padding(0.dp, 0.dp, 12.dp, 0.dp)
                                        .clickable {
                                            GlobalVar.scheduleList.remove(item)
                                            var tmp = tmpp.value as ArrayList
                                            tmp.remove(item)
                                            tmpp.value = tmp
                                        })
                            }

                        }
                    }
                }
//            }
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
            var selectedParent2 = remember { mutableStateOf(ParentProfile()) }
            var childTF by remember { mutableStateOf(false) }
            var selectedChild by remember { mutableStateOf("") }
            var selectedChild2 = remember { mutableStateOf(ChildProfile()) }

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

//            LaunchedEffect(key1 =  teacherScheduleViewModel.parentsData.collectAsState().value.data){
//                teacherScheduleViewModel.getParentsData()
//                Log.d(ContentValues.TAG, "dialogParentsData: ${teacherScheduleViewModel.parentsData.value.data}")
//                if(teacherScheduleViewModel.parentsData.value.data != null){
//                    parentsLoaded.value = true
//                }
//            }

//            LaunchedEffect(key1 =  selectedParent2.value){
//                if (selectedParent2.value != "") {
//                    teacherScheduleViewModel.getParentsChildData(selectedParent2.value)
//                    delay(1500)
//                    Log.d(
//                        ContentValues.TAG,
//                        "dialogParentsChildData: ${teacherScheduleViewModel.parentsChildData.value.data}"
//                    )
//                    if (teacherScheduleViewModel.parentsChildData.value.data != null) {
//                        childLoaded.value = true
//                        childTF = true
//                    }
//                }
//            }

//            LaunchedEffect(key1 =  selectedChild2.value){
//                Log.e("Testy", teacherScheduleViewModel.parentsID.value.data.toString())
//                if (selectedChild2.value != "" && teacherScheduleViewModel.parentsID.value.data != null) {
//                    teacherScheduleViewModel.getChildID(selectedChild2.value, teacherScheduleViewModel.parentsID.value.data!!)
//                    delay(1000)
//                    Log.d(
//                        ContentValues.TAG,
//                        "dialogChildIDData: ${teacherScheduleViewModel.childID.value.data}"
//                    )
//                    if (teacherScheduleViewModel.childID.value.data != null) {
//                        childIDLoaded.value = true
//                    }
//                }
//            }

            if (openDialog.value) {
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
                                GlobalVar.parentList.forEach { label ->
                                    DropdownMenuItem(onClick = {
                                        selectedParent = label.name!!
                                        selectedParent2.value = label
                                        expanded = false
                                        childTF = true
                                        selectedChild = ""
                                        selectedChild2.value = ChildProfile()
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
                                        Modifier.clickable { expanded2 = !expanded2 })
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
                                filterChild(selectedParent2.value).forEach { label ->
                                    DropdownMenuItem(onClick = {
                                        selectedChild = label.name!!
                                        selectedChild2.value = label
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
                                    Log.e(TAG, "Test")
                                    GlobalVar.scheduleList.add(TPSchedule(
                                        "" + (GlobalVar.childList.indexOf(selectedChild2.value) + 1),
                                        time.value,
                                        time2.value,
                                        "Rand",
                                        date.value,
                                        isOffline.value,
                                        selectedChild2.value.parentId,
                                        "1",
                                        txtField.value
                                    ))
                                    openDialog.value = false
                                    Log.e(TAG, "Test")

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

private fun filterSchedule(date: String): ArrayList<TPSchedule> {
    var temp = ArrayList<TPSchedule>()

    for (a in GlobalVar.scheduleList) {
        if (a.date == date) {
            temp.add(a)
        }
    }

    return temp
}

private fun filterChild(parentProfile: ParentProfile): ArrayList<ChildProfile> {
    var temp = ArrayList<ChildProfile>()

    for (a in GlobalVar.childList) {
        if (a.parentId == (GlobalVar.parentList.indexOf(parentProfile) + 1).toString()) {
            temp.add(a)
        }
    }

    return temp
}