package com.example.potenseek.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.potenseek.R

@Composable
fun heading(){
    Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo", modifier= Modifier.scale(2.5f))
    Text("Discover your true potential!", modifier = Modifier.padding(top = 10.dp, bottom = 10.dp), fontSize = 17.sp, fontWeight = FontWeight.Bold)

}