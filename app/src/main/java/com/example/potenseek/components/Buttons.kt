package com.example.potenseek.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.potenseek.Navigation.NavigationEnum
import com.example.potenseek.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun BasicButton(components : @Composable () -> Unit, modifier: Modifier = Modifier.padding(top = 10.dp), onClick : () -> Unit) {
    Button(onClick, modifier = modifier.fillMaxWidth().padding(0.dp)) {
        components()
    }
}

@Composable

fun logoutBtn(navController : NavController){
    Image(
        painter = painterResource(id = R.drawable.ic_baseline_exit), contentDescription = "",
        modifier = Modifier
            .width(50.dp)
            .height(50.dp)
            .padding(vertical = 10.dp)
            .padding(end = 20.dp)
            .clickable {

                Firebase.auth.signOut()

                navController.popBackStack()
                navController.navigate(NavigationEnum.LoginScreenActivity.name)


            }
    )
}