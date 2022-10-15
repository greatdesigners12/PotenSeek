package com.example.potenseek.components

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun BasicButton(text : String, modifier: Modifier, fontWeight: FontWeight, fontSize : TextUnit, onClick : () -> Unit) {
    Button(onClick) {
        Text(text, fontWeight = fontWeight, fontSize = fontSize)
    }
}