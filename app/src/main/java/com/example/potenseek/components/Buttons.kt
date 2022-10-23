package com.example.potenseek.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BasicButton(components : @Composable () -> Unit, modifier: Modifier = Modifier.padding(top = 10.dp), onClick : () -> Unit) {
    Button(onClick, modifier = modifier) {
        components()
    }
}