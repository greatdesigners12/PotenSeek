package com.example.potenseek.Screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

import com.example.potenseek.Navigation.NavigationEnum
import com.example.potenseek.ui.theme.PotenSeekTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreenActivity(navController: NavController?) {
    LaunchedEffect(true){
        delay(3000)
        navController?.navigate(NavigationEnum.LoginScreenActivity.name)
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(text = "This is splashscreen")
    }

}

@Preview
@Composable
fun preview(){
    SplashScreenActivity(null)
}



