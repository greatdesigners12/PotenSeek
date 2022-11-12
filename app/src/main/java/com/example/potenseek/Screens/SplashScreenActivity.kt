package com.example.potenseek.Screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.example.potenseek.Navigation.NavigationEnum
import com.example.potenseek.R
import com.example.potenseek.ui.theme.PotenSeekTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreenActivity(navController: NavController?) {
    LaunchedEffect(true){
        delay(3000)
        navController?.popBackStack()
        navController?.navigate(NavigationEnum.LoginScreenActivity.name)
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        Image(painter = painterResource(R.drawable.box), contentDescription = "Box", modifier = Modifier.scale(2.5F))
        Spacer(modifier = Modifier.height(60.dp))
        Image(painter = painterResource(R.drawable.logo), contentDescription = "Logo", modifier = Modifier.scale(2.0F))

    }

    Box(modifier = Modifier.fillMaxSize().padding(20.dp), contentAlignment = Alignment.BottomCenter){
        Image(painter = painterResource(R.drawable.supports), contentDescription = "Supports", modifier = Modifier.scale(2.0F))


    }


    
}

@Preview
@Composable
fun preview(){
    SplashScreenActivity(null)
}



