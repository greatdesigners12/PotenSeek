package com.example.potenseek.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview




@Composable
fun basicInputField(label : String, inputValue : String, keyboardType: KeyboardType = KeyboardType.Text, onValueChanged : (String) -> Unit){
    OutlinedTextField(value = inputValue, onValueChanged, singleLine = true, label= {Text(label)}, modifier = Modifier.fillMaxWidth(), keyboardOptions = KeyboardOptions(keyboardType = keyboardType))
}

@Composable
fun passwordInputField(inputValue : String, passwordVisible : Boolean, pwdIcon : () -> Unit, onValueChanged : (String) -> Unit){
    OutlinedTextField(value = inputValue, onValueChanged, modifier = Modifier.fillMaxWidth(), singleLine = true, label= {Text("Password")},
        visualTransformation = if(passwordVisible) VisualTransformation.None  else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            IconButton(onClick = pwdIcon) {
                Icon(imageVector = if(passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff, contentDescription = "")

            }
        }
    )
}

