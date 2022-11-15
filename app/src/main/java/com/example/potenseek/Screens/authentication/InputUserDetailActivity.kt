package com.example.potenseek.Screens.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.potenseek.Navigation.NavigationEnum
import com.example.potenseek.components.BasicButton
import com.example.potenseek.components.SimpleAlertDialog
import com.example.potenseek.components.basicInputField

@Composable
fun inputUserDetailActivity(navController: NavController, viewModel: ProfileViewModel) {
    val parentName = remember{
        mutableStateOf("")
    }
    val childName = remember{
        mutableStateOf("")
    }

    val childAge = remember{
        mutableStateOf("")
    }

    val showAlert = remember{
        mutableStateOf(false)
    }

    val loading = remember {
        mutableStateOf(false)
    }

    val customErrorMsg = remember{
        mutableStateOf("")
    }


    LaunchedEffect(key1 = true){
        viewModel.checkIfUserJobExist()
    }
    LaunchedEffect(key1 = viewModel.isJobExist.collectAsState().value){
        if(viewModel.isJobExist.value.data == "exist"){
            navController.navigate(NavigationEnum.TeacherPsychologistHomeActivity.name)
        }
    }



    LaunchedEffect(key1 = viewModel.isExist.collectAsState().value.data){
        if(viewModel.isExist.value.data == "exist"){
            navController.popBackStack()
            navController.navigate(NavigationEnum.ChooseProfileActivity.name)
        }
    }

    LaunchedEffect(key1 = viewModel.data.collectAsState().value.data){
        val curData = viewModel.data.value
        viewModel.checkIfUserDataExist()
        if(curData.data == "success" || curData.data == "exist"){
            navController.popBackStack()
            navController.navigate(NavigationEnum.ChooseProfileActivity.name)
        }else if (curData.data == "failed"){
            showAlert.value = true
        }
    }

    if(showAlert.value){
        val curData = viewModel.data.collectAsState().value
        SimpleAlertDialog(title = "Failed", message = if(customErrorMsg.value.isNotEmpty()) curData.e?.message.toString() else customErrorMsg.value) {
            viewModel.reset()
            showAlert.value = false
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(20.dp) ,horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text("ISI DATA DIRI", fontWeight = FontWeight.Bold, fontSize = 25.sp)
        basicInputField("Nama Orang Tua" ,inputValue = parentName.value){
            parentName.value = it
        }

        basicInputField("Nama Anak" ,inputValue = childName.value){
            childName.value = it
        }

        basicInputField("Umur Anak" , inputValue = childAge.value, keyboardType = KeyboardType.Number){
            childAge.value = it
        }
        BasicButton(components = {
            if(loading.value){
                CircularProgressIndicator(color = Color.White)
            }else{
                Text("Buat Profile")
            }

        }) {
            loading.value = true
            if(parentName.value.isNotEmpty() && childName.value.isNotEmpty() && childAge.value != ""){
                viewModel.createProfile(parentName.value, "parent",childName.value, childAge.value.toInt())
            }else{
                customErrorMsg.value = "All input mustn't be empty"
            }
        }
    }
}