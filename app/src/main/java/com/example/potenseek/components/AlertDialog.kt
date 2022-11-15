package com.example.potenseek.components

import android.R
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ContentValues
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.ui.geometry.Size
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.potenseek.Model.ParentProfile
import com.example.potenseek.Screens.schedule.TeacherScheduleViewModel
import com.example.potenseek.Screens.ui.theme.Coral
import com.example.potenseek.Screens.ui.theme.MathColor
import com.example.potenseek.Screens.ui.theme.Orange300
import kotlinx.coroutines.delay
import java.util.*

@Composable
fun SimpleAlertDialog(title : String, message : String, onAction : () -> Unit) {
    val isSuccess = title == "success"
    AlertDialog(title={Text(title)}, text = {Text(message)}, buttons = {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(onClick = onAction) {
                Text(if(isSuccess) "Ok" else "Try again")
            }
        }

    }, onDismissRequest = {})
}

@Composable
fun ConsultationTimeDialog(teacherScheduleViewModel: TeacherScheduleViewModel, openDialog: Boolean) {
    val calendar = Calendar.getInstance()
    val hour = calendar[Calendar.HOUR_OF_DAY]
    val minute = calendar[Calendar.MINUTE]
    var opdialog by remember {
        mutableStateOf(true)
    }

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
            date.value = "$year-$month-$dayOfMonth"
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

    var selectedParent by remember { mutableStateOf("") }
    var childTF by remember { mutableStateOf(false) }
    var selectedChild by remember { mutableStateOf("") }

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

    LaunchedEffect(key1 =  selectedParent){
        if (selectedParent != "") {
            teacherScheduleViewModel.getParentsChildData(selectedParent)
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

    if (parentsLoaded.value && opdialog) {
        AlertDialog(text = {
            Column(modifier = Modifier
                .wrapContentHeight()
                .scrollable(state = rememberScrollState(), orientation = Orientation.Vertical)) {
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
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    placeholder = { Text(text = "Title") },
                    value = txtField.value,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = {
                        txtField.value = it
                    })
                Column(Modifier.fillMaxWidth()) {
                    TextField(
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
                            unfocusedIndicatorColor = Color.Transparent
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
                                expanded = false
                            }) {
                                Text(text = label.name!!)
                            }
                        }
                    }
                }
                    Column(Modifier.fillMaxWidth()) {
                        TextField(
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
                                unfocusedIndicatorColor = Color.Transparent
                            )
                        )
                        DropdownMenu(
                            expanded = expanded2,
                            onDismissRequest = { expanded2 = false },
                            modifier = Modifier
                                .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
                        ) {
                            teacherScheduleViewModel.parentsChildData.collectAsState().value.data!!.forEach { label ->
                                DropdownMenuItem(onClick = {
                                    selectedChild = label.name!!
                                    expanded2 = false
                                }) {
                                    Text(text = label.name!!)
                                }
                            }
                        }
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
                              opdialog = false
                    },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 8.dp, 8.dp),
                    colors = ButtonDefaults.buttonColors(Color.Red)
                ) {
                    Text(text = "Close", color = Color.Black)
                }
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 24.dp, 8.dp),
                    colors = ButtonDefaults.buttonColors(Color.Green)
                ) {
                    Text(text = "Add", color = Color.Black)
                }
            }

        }, onDismissRequest = {
                              opdialog = false
        },
            shape = RoundedCornerShape(15.dp)
        )
    }
}