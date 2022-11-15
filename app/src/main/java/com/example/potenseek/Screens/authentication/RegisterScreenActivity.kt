package com.example.potenseek.Screens.authentication

import android.graphics.Color
import android.util.Log
import android.widget.Spinner
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.potenseek.Navigation.NavigationEnum
import com.example.potenseek.R
import com.example.potenseek.components.*

@Composable

fun RegisterScreenActivity(navController: NavController, authViewModel: AuthViewModel){

    val btnLoading = remember{
        mutableStateOf(false)
    }

    val showAlertDialog = remember{
        mutableStateOf(false)
    }
    val loading = remember{
        mutableStateOf(false)
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

    val passwordVisible = remember{
        mutableStateOf(false)
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(top = 50.dp, start = 10.dp, end = 10.dp)){
        Column(modifier = Modifier
            .padding(10.dp)
            .fillMaxSize() ,horizontalAlignment = Alignment.CenterHorizontally){
            heading()
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
            Box(modifier = Modifier.padding(top = 10.dp)){
                BasicButton( components = {

                    if(loading.value){
                        CircularProgressIndicator(color = androidx.compose.ui.graphics.Color.White)
                    }else{
                        Text(text = "REGISTER",modifier = Modifier.padding(top = 10.dp),fontWeight = FontWeight.Bold, fontSize = 20.sp)

                    }
                }) {
                    loading.value = true
                    authViewModel.register(emailValue.value, passwordValue.value)
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