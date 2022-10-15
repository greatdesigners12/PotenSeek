package com.example.potenseek.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun SimpleAlertDialog(title : String, message : String, onAction : () -> Unit) {
    val isSuccess = title == "success"
    AlertDialog(title={Text(title)}, text = {Text(message)}, buttons = {
        Button(onClick = onAction) {
            Text(if(isSuccess) "Ok" else "Try again")
        }
    }, onDismissRequest = {})
}