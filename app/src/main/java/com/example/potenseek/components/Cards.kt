package com.example.potenseek.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.potenseek.R

@Composable
fun profileCard(name : String){
    Column(horizontalAlignment = Alignment.CenterHorizontally){
        Image(painter = painterResource(id = R.drawable.ic_launcher_background), modifier = Modifier
            .width(100.dp)
            .height(100.dp) ,contentDescription = "", contentScale = ContentScale.Fit)
        Text(name)
    }
}