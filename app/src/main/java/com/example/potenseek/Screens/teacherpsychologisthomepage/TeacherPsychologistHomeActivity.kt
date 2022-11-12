package com.example.potenseek.Screens.teacherpsychologisthomepage

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.potenseek.Model.TeacherPsychologistRole
import com.example.potenseek.R
import com.example.potenseek.Screens.ui.theme.Coral
import com.example.potenseek.Screens.ui.theme.PotenSeekTheme
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay

class TeacherPsychologistHomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PotenSeekTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold() {
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TeacherPsychologistHome(teacherPsychologistHomeViewModel: TeacherPsychologistHomeViewModel) {
    val tpProfileLoading = remember{
        mutableStateOf(true)
    }

    val focusManager = LocalFocusManager.current
    val kc = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val mContext = LocalContext.current
    val db = Firebase.firestore
    var roles = ArrayList<TeacherPsychologistRole>()
    kc!!.hide()

    LaunchedEffect(key1 =  teacherPsychologistHomeViewModel.teacherPsychologistData.collectAsState().value.data){
        teacherPsychologistHomeViewModel.getTeacherPsychologistData()
        Log.d(ContentValues.TAG, "teacherPsychologistHome: ${teacherPsychologistHomeViewModel.teacherPsychologistData.value.data}")
        if(teacherPsychologistHomeViewModel.teacherPsychologistData.value.data != null){
            tpProfileLoading.value = false
        }
        Toast.makeText(mContext, "" + (teacherPsychologistHomeViewModel.teacherPsychologistData.value.data?.name), Toast.LENGTH_SHORT).show()
    }

    LaunchedEffect(key1 =  teacherPsychologistHomeViewModel._teacherPsychologistRoleData.collectAsState().value.data){
        teacherPsychologistHomeViewModel.getTeacherPsychologistRoleData()
        roles = teacherPsychologistHomeViewModel._teacherPsychologistRoleData.value.data!!
        Log.d(ContentValues.TAG, "teacherPsychologistHomeActivity: ${teacherPsychologistHomeViewModel._teacherPsychologistRoleData.value.data}")
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        var text by remember {
            mutableStateOf("")
        }

        var textColor by remember {
            mutableStateOf(Color.Gray)
        }

        Text(
            text = "What's Hot",
            modifier = Modifier.padding(20.dp, 16.dp),
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold
        )
        Card(
            backgroundColor = Coral,
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .padding(0.dp, 20.dp)
                .fillMaxWidth(0.9f)
                .align(alignment = Alignment.CenterHorizontally)
        ) {
            Text(
                text = "What's Hot",
                modifier = Modifier.padding(20.dp, 16.dp),
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Text(
            text = "Service List",
            modifier = Modifier.padding(20.dp, 16.dp),
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold
        )
        Surface(
            elevation = 10.dp,
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .align(alignment = Alignment.CenterHorizontally)
                .height(50.dp)
        ) {
            LaunchedEffect(Unit) {
                delay(200)// <-- This is crucial.
                focusRequester.requestFocus()
            }
            OutlinedTextField(
                value = text,
                placeholder = {Text(text = "Search...", color = Color.Gray, fontSize = 18.sp)},
                singleLine = true,
                onValueChange = {newText ->
                    text = newText

                    if (text != "") {
                        textColor = Color.Gray
                    } else {
                        textColor = Color.Black
                    }
                },
                enabled = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White
                ),
                textStyle = TextStyle.Default.copy(fontSize = 20.sp, color = textColor),
                trailingIcon = {
                    IconButton(onClick = {}) {
                        Icon(painter = painterResource(id = R.drawable.ic_baseline_search_24), contentDescription = "", modifier = Modifier.size(30.dp))
                    }
                },
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                }),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .focusRequester(focusRequester)
                    .align(alignment = Alignment.CenterHorizontally)
                    .wrapContentHeight(unbounded = true),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                )
            )
        }
        TeacherPsychologistList(tpRole = roles)
    }
}

@Composable
fun TeacherPsychologistList(tpRole: ArrayList<TeacherPsychologistRole>) {
    LazyRow {
        itemsIndexed(items = tpRole) {index, item ->
            TeacherPsychologistRoleCard(teacherPsychologistRole = item)
        }
    }
}