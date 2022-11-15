package com.example.potenseek.Screens.authentication

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.potenseek.Navigation.NavigationEnum
import com.example.potenseek.R
import com.example.potenseek.Utils.FirebaseWrapper
import com.example.potenseek.components.*
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreenActivity(navController: NavController, authViewModel : AuthViewModel, profileViewModel: ProfileViewModel) {
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

    val mainLoading = remember{
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = true){
        if(FirebaseAuth.getInstance().currentUser == null){
            mainLoading.value = false
        }
        profileViewModel.checkIfUserDataExist()
        profileViewModel.checkIfUserJobExist()

    }

    LaunchedEffect(key1 = authViewModel.data.collectAsState().value.data){



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

    LaunchedEffect(key1 = profileViewModel.isJobExist.collectAsState().value){



        if(profileViewModel.isJobExist.value == "exist"){
            navController.popBackStack()
            navController.navigate(NavigationEnum.TeacherPsychologistHomeActivity.name)
        }




    }

    LaunchedEffect(key1 = profileViewModel.isExist.collectAsState().value){
        if(profileViewModel.isExist.value == "exist"){
            Log.d(TAG, "LoginScreenActivity: hi bro")
            navController.popBackStack()
            navController.navigate(NavigationEnum.InputUserDetailActivity.name)
        }
    }

    if(showAlertDialog.value){
        (if(authViewModel.data.collectAsState().value.data == "success") authViewModel.data.collectAsState().value.data else authViewModel.data.collectAsState().value.e?.message.toString())?.let {
            SimpleAlertDialog(title = if(authViewModel.data.collectAsState().value.data == "success") "Success" else "Login Failed",
                message = it
            ){

                showAlertDialog.value = false
                authViewModel.resetData()
            }
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(top = 50.dp, start = 10.dp, end = 10.dp)) {
        if (mainLoading.value) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                heading()
                basicInputField("Email", emailValue.value) {
                    emailValue.value = it
                }
                passwordInputField(
                    inputValue = passwordValue.value,
                    passwordVisible = passwordVisible.value,
                    pwdIcon = { passwordVisible.value = !passwordVisible.value },

                    ) {
                    passwordValue.value = it
                }

                    BasicButton(components = {

                        if (loading.value) {
                            CircularProgressIndicator(color = Color.White)
                        } else {
                            Text(
                                text = "LOGIN",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )

                        }
                    }) {
                        loading.value = true
                        authViewModel.login(emailValue.value, passwordValue.value)
                    }
                Spacer(modifier = Modifier.height(20.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Don't have any account ? ")
                    Text("Create a new one !", modifier = Modifier.clickable {

                        navController.navigate(NavigationEnum.RegisterScreenActivity.name)

                    }, color = MaterialTheme.colors.primary)
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row() {
                    Text("Are you psychologist or teacher ? ")
                    Text("Click here !", modifier = Modifier.clickable {

                        navController.navigate(NavigationEnum.RegisterJobActivity.name)

                    }, color = MaterialTheme.colors.primary)
                }

            }

        }
    }


}