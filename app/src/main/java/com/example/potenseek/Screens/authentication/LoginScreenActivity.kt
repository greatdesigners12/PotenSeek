package com.example.potenseek.Screens.authentication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.potenseek.Navigation.NavigationEnum
import com.example.potenseek.components.BasicButton
import com.example.potenseek.components.emailInputField
import com.example.potenseek.components.passwordInputField

@Composable
fun LoginScreenActivity(navController: NavController) {
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
            Text("LOGIN", modifier = Modifier.padding(vertical = 10.dp), fontSize = 25.sp, fontWeight = FontWeight.Bold)
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
                BasicButton(text = "LOGIN", modifier = Modifier.padding(top = 10.dp), fontWeight = FontWeight.Bold, fontSize = 20.sp) {
                    print(emailValue.value)
                }
            }
            Row{
                Text("Go to password page")
                Text("Click here", modifier = Modifier.clickable {
                    navController.navigate(NavigationEnum.RegisterScreenActivity.name)
                })
            }

        }

    }


}