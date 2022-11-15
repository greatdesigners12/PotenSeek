package com.example.potenseek.Screens.authentication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.potenseek.Navigation.NavigationEnum
import com.example.potenseek.components.*

@Composable
fun RegisterJobActivity(navController: NavController, authViewModel: AuthViewModel) {
    val btnLoading = remember{
        mutableStateOf(false)
    }

    val showAlertDialog = remember{
        mutableStateOf(false)
    }
    val loading = remember{
        mutableStateOf(false)
    }

    val namaPekerjaanSpesifik = remember{
        mutableStateOf("")
    }

    val expanded = remember{
        mutableStateOf(false)
    }

    val selectedText = remember{
        mutableStateOf("")
    }


    LaunchedEffect(key1 = authViewModel.data.collectAsState().value.data){
        loading.value = authViewModel.data.value.loading

        if(authViewModel.data.value.data == "failed"){
            loading.value = authViewModel.data.value.loading
            showAlertDialog.value = true

        }else if(authViewModel.data.value.data == "success"){
            loading.value = authViewModel.data.value.loading
            navController.popBackStack()
            navController.navigate(NavigationEnum.LoginScreenActivity.name)

        }

    }

    if(showAlertDialog.value){
        (if(authViewModel.data.collectAsState().value.data == "success") authViewModel.data.collectAsState().value.data else authViewModel.data.collectAsState().value.e?.message.toString())?.let {
            SimpleAlertDialog(title = if(authViewModel.data.collectAsState().value.data == "success") "Success" else "Register Failed",
                message = it
            ){

                showAlertDialog.value = false
                authViewModel.resetData()
            }
        }
    }

    val emailValue = remember{
        mutableStateOf("")
    }

    val passwordValue = remember{
        mutableStateOf("")
    }

    val name = remember{
        mutableStateOf("")
    }

    val passwordVisible = remember{
        mutableStateOf(false)
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)){
        Column(modifier = Modifier
            .padding(10.dp)
            .fillMaxSize() ,horizontalAlignment = Alignment.CenterHorizontally){
            heading()
            basicInputField("Full Name", emailValue.value){
                name.value = it
            }
            basicInputField("Email", emailValue.value){
                emailValue.value = it
            }
            passwordInputField(
                inputValue = passwordValue.value,
                passwordVisible = passwordVisible.value,
                pwdIcon = { passwordVisible.value = !passwordVisible.value },

                ){
                passwordValue.value = it
            }
            Text("Jenis Pekerjaan")
            DropdownMenu(

                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                modifier = Modifier.fillMaxWidth()) {

                DropdownMenuItem(onClick = {
                    selectedText.value = "Guru"
                    expanded.value = false
                }) {
                    Text(text = "Teacher")
                }

                DropdownMenuItem(onClick = {
                    selectedText.value = "Psychologist"
                    expanded.value = false
                }) {
                    Text(text = "Psychologist")
                }

            }
            Spacer(modifier = Modifier.height(10.dp))
            basicInputField("Jenis ${selectedText.value}", namaPekerjaanSpesifik.value){
                namaPekerjaanSpesifik.value = it
            }
            Spacer(modifier = Modifier.height(10.dp))
            Box(modifier = Modifier.padding(top = 10.dp)){
                BasicButton( components = {

                    if(loading.value){
                        CircularProgressIndicator(color = Color.White)
                    }else{
                        Text(text = "REGISTER",modifier = Modifier.padding(top = 10.dp),fontWeight = FontWeight.Bold, fontSize = 20.sp)

                    }
                }) {
                    loading.value = true

                    authViewModel.registerJob(emailValue.value, passwordValue.value, name.value, selectedText.value)
                }

            }
            Row(modifier = Modifier.padding(top = 10.dp)){
                Text("Back to login ? ")
                Text("Click here", style = TextStyle(color = MaterialTheme.colors.primary) , modifier = Modifier.clickable {
                    authViewModel.resetData()
                    navController.popBackStack()
                    navController.navigate(NavigationEnum.LoginScreenActivity.name)

                })
            }

        }

    }
}