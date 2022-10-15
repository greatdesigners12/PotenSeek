package com.example.potenseek.Screens.authentication

import android.graphics.Color
import android.widget.Spinner
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.potenseek.Navigation.NavigationEnum
import com.example.potenseek.components.BasicButton
import com.example.potenseek.components.SimpleAlertDialog
import com.example.potenseek.components.emailInputField
import com.example.potenseek.components.passwordInputField

@Composable

fun RegisterScreenActivity(navController: NavController, authViewModel: AuthViewModel){

    val btnLoading = remember{
        mutableStateOf(false)
    }

    val showAlertDialog = remember{
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = authViewModel.loading.value){
        btnLoading.value = authViewModel.loading.value!!

        if(authViewModel.status.value != ""){
            showAlertDialog.value = true
        }

    }

    if(showAlertDialog.value){
        SimpleAlertDialog(title = if(authViewModel.status.value == "success") "Success" else "Register Failed", message = authViewModel.status.value.toString()){
            if(authViewModel.status.value == "success"){
                navController.navigate(NavigationEnum.LoginScreenActivity.name)
            }
        }
        showAlertDialog.value = false
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
    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier
            .padding(10.dp)
            .fillMaxSize() ,horizontalAlignment = Alignment.CenterHorizontally){
            Text("Register", modifier = Modifier.padding(vertical = 10.dp), fontSize = 25.sp, fontWeight = FontWeight.Bold)
            emailInputField(emailValue.value){
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
                if(btnLoading.value){
                    CircularProgressIndicator(progress = 0.5f)
                }else{
                    BasicButton(text = "REGISTER", modifier = Modifier.padding(top = 10.dp), fontWeight = FontWeight.Bold, fontSize = 20.sp) {
                        authViewModel.register(emailValue.value, passwordValue.value)
                    }
                }

            }
            Row(modifier = Modifier.padding(top = 10.dp)){
                Text("Go to password page, ")
                Text("Click here", style = TextStyle(color = MaterialTheme.colors.primary) , modifier = Modifier.clickable {
                    navController.navigate(NavigationEnum.RegisterScreenActivity.name)
                })
            }

        }

    }
}