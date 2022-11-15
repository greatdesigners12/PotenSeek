package com.example.potenseek.components

import android.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.potenseek.Screens.ui.theme.Coral

@Composable
fun SimpleAlertDialog(title : String, message : String, onAction : () -> Unit) {
    val isSuccess = title == "success"
    AlertDialog(title={Text(title)}, text = {Text(message)}, buttons = {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(onClick = onAction) {
                Text(if(isSuccess) "Ok" else "Try again")
            }
        }

    }, onDismissRequest = {})
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ConsultationTimeDialog() {
    val isSuccess = true
    var txtField = remember {
        mutableStateOf("")
    }
    AlertDialog(title={Text(text = "Add Consultation Time", fontSize = 16.sp)}, text = {
        Row(modifier = Modifier
            .padding(0.dp, 8.dp, 0.dp, 0.dp)) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        BorderStroke(
                            width = 2.dp,
                            color = Color.LightGray
                        ),
                        shape = RoundedCornerShape(20)
                    ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                placeholder = { Text(text = "Enter value") },
                value = txtField.value,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    txtField.value = it
                })
        }
    }, buttons = {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(onClick = {},
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 8.dp)
                    .background(color = Coral)
            ) {
                Text(text = "Add", color = Color.White)
            }
        }

    }, onDismissRequest = {},
    shape = RoundedCornerShape(15.dp)
    )
}