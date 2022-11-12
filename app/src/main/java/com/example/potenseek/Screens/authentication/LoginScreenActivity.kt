package com.example.potenseek.Screens.authentication

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.potenseek.Navigation.NavigationEnum
import com.example.potenseek.Utils.FirebaseWrapper
import com.example.potenseek.components.*

@Composable
fun LoginScreenActivity(navController: NavController, authViewModel : AuthViewModel) {
    val emailValue = remember{
        mutableStateOf("")
    }

    val passwordValue = remember{
        mutableStateOf("")
    }

    val passwordVisible = remember{
        mutableStateOf(false)
    }

    val showAlertDialog = remember {
        mutableStateOf(false)
    }

    val loading = remember{
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = authViewModel.data.collectAsState().value.data){

        authViewModel.checkIfUserExist(navController)

        loading.value = authViewModel.data.value.loading

        if(authViewModel.data.value.data == "failed"){
            loading.value = authViewModel.data.value.loading
            showAlertDialog.value = true

        }else if(authViewModel.data.value.data == "success"){
            loading.value = authViewModel.data.value.loading
            navController.popBackStack()
            navController.navigate(NavigationEnum.InputUserDetailActivity.name)


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

    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier
            .padding(10.dp)
            .fillMaxSize() ,horizontalAlignment = Alignment.CenterHorizontally){
            Text("LOGIN", modifier = Modifier.padding(vertical = 10.dp), fontSize = 25.sp, fontWeight = FontWeight.Bold)
            basicInputField("Email" ,emailValue.value){
                emailValue.value = it
            }
            passwordInputField(
                inputValue = passwordValue.value,
                passwordVisible = passwordVisible.value,
                pwdIcon = { passwordVisible.value = !passwordVisible.value },

            ){
                passwordValue.value = it
            }
            Box(modifier = Modifier.padding(vertical = 10.dp)){
                BasicButton( components = {

                    if(loading.value){
                        CircularProgressIndicator(color = Color.White)
                    }else{
                        Text(text = "LOGIN",modifier = Modifier.padding(top = 10.dp),fontWeight = FontWeight.Bold, fontSize = 20.sp)

                    }
                }) {
                    loading.value = true
                    authViewModel.login(emailValue.value, passwordValue.value)
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically){
                Text("Go to register page, ")
                Text("Click here", modifier = Modifier.clickable {
                    navController.popBackStack()
                    navController.navigate(NavigationEnum.RegisterScreenActivity.name)

                }, color = MaterialTheme.colors.primary)
            }

        }

    }


}