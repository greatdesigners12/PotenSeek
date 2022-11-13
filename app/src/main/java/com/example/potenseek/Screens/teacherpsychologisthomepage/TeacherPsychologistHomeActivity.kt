package com.example.potenseek.Screens.teacherpsychologisthomepage

import android.content.ContentValues.TAG
import android.icu.number.Scale
import android.os.Bundle
import android.util.Log
import android.widget.GridLayout.FILL
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.potenseek.Model.TeacherPsychologist
import com.example.potenseek.Model.TeacherPsychologistRole
import com.example.potenseek.R
import com.example.potenseek.Screens.ui.theme.Coral
import com.example.potenseek.Screens.ui.theme.GreyBackground
import com.example.potenseek.Screens.ui.theme.PotenSeekTheme
import com.example.potenseek.components.TeacherPsychologistCard
import com.example.potenseek.components.TeacherPsychologistRoleCard
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
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

@OptIn(ExperimentalComposeUiApi::class, ExperimentalPagerApi::class)
@Composable
fun TeacherPsychologistHome(teacherPsychologistHomeViewModel: TeacherPsychologistHomeViewModel) {
    val teacherPsychologistSectionLoading = remember {
        mutableStateOf(true)
    }

    val teacherPsychologistRoleSectionLoading = remember {
        mutableStateOf(true)
    }

    val whatsHotSectionLoading = remember {
        mutableStateOf(true)
    }

    val focusRequester = remember {
        FocusRequester()
    }

    val state = rememberPagerState()

    val imageUrl = remember {
        mutableStateOf("")
    }

    val focusManager = LocalFocusManager.current
    val kc = LocalSoftwareKeyboardController.current
    kc!!.hide()

    LaunchedEffect(key1 =  teacherPsychologistHomeViewModel.teacherPsychologistData.collectAsState().value.data){
        teacherPsychologistHomeViewModel.getTeacherPsychologistData()
        Log.d(TAG, "teacherPsychologistHomeData: ${teacherPsychologistHomeViewModel.teacherPsychologistData.value.data}")
        if(teacherPsychologistHomeViewModel.teacherPsychologistData.value.data != null){
            teacherPsychologistSectionLoading.value = false
        }
    }

    LaunchedEffect(key1 =  teacherPsychologistHomeViewModel.teacherPsychologistRoleData.collectAsState().value.data){
        teacherPsychologistHomeViewModel.getTeacherPsychologistRoleData()
        Log.d(TAG, "teacherPsychologistRoleHomeData: ${teacherPsychologistHomeViewModel.teacherPsychologistRoleData.value.data}")
        if(teacherPsychologistHomeViewModel.teacherPsychologistRoleData.value.data != null){
            teacherPsychologistRoleSectionLoading.value = false
        }
    }

    LaunchedEffect(key1 =  teacherPsychologistHomeViewModel.whatsHotData.collectAsState().value.data){
        teacherPsychologistHomeViewModel.getWhatsHotData()
        Log.d(TAG, "whatsHotHomeData: ${teacherPsychologistHomeViewModel.whatsHotData.value.data}")
        if(teacherPsychologistHomeViewModel.whatsHotData.value.data != null){
            whatsHotSectionLoading.value = false
        }
    }

        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = GreyBackground)) {
            var text by remember {
                mutableStateOf("")
            }

            var textColor by remember {
                mutableStateOf(Color.Gray)
            }

            Text(
                text = "What's Hot",
                modifier = Modifier.padding(20.dp, 16.dp, 20.dp, 8.dp),
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold
            )

            if (whatsHotSectionLoading.value) {
                Spacer(modifier = Modifier.height(40.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                    CircularProgressIndicator()
                }
                Spacer(modifier = Modifier.height(40.dp))
            } else {
                HorizontalPager(
                    state = state,
                    count = teacherPsychologistHomeViewModel.whatsHotData.collectAsState().value.data!!.size, modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                ) { page ->
                    imageUrl.value = teacherPsychologistHomeViewModel.whatsHotData.collectAsState().value.data!![page].url.toString()

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(contentAlignment = Alignment.BottomCenter) {
                            Image(
                                painter = rememberAsyncImagePainter(imageUrl.value), contentDescription = "",
                                modifier = Modifier
                                    .padding(20.dp, 8.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .fillMaxSize(), contentScale = ContentScale.Crop
                            )
                        }
                    }
                }

                DotsIndicator(totalDots = teacherPsychologistHomeViewModel.whatsHotData.collectAsState().value.data!!.size, selectedIndex = state.currentPage)
            }

            Text(
                text = "Service List",
                modifier = Modifier.padding(20.dp, 16.dp),
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold
            )
            Surface(
                elevation = 4.dp,
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

                        if (text == "") {
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

            if (!teacherPsychologistRoleSectionLoading.value) {
                LazyRow {
                    itemsIndexed(items = teacherPsychologistHomeViewModel.teacherPsychologistRoleData.value.data!!) {index, item ->
                        TeacherPsychologistRoleCard(teacherPsychologistRole = item)
                    }
                }
            }

            if (teacherPsychologistSectionLoading.value || teacherPsychologistRoleSectionLoading.value) {
                Spacer(modifier = Modifier.height(60.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                    CircularProgressIndicator()
                }
                Spacer(modifier = Modifier.height(60.dp))
            } else {
                LazyColumn {
                    itemsIndexed(items = teacherPsychologistHomeViewModel.teacherPsychologistData.value.data!!) {index, item ->
                        TeacherPsychologistCard(
                            teacherPsychologist = item,
                            teacherPsychologistHomeViewModel.teacherPsychologistRoleData.collectAsState().value.data!!
                        )
                    }
                }
            }
        }

}

@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int
) {

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(), horizontalArrangement = Arrangement.Center
    ) {
        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(color = Color.DarkGray)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(color = Color.LightGray)
                )
            }

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}