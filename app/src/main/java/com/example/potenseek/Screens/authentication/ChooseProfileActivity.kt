package com.example.potenseek.Screens.authentication

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.navigation.NavController
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.potenseek.R
import com.example.potenseek.components.profileCard

@Composable
fun chooseAccountActivity(navController: NavController, profileViewModel: ProfileViewModel) {
    val parentSectionLoading = remember{
        mutableStateOf(true)
    }



    val childSectionLoading = remember{
        mutableStateOf(true)
    }
    LaunchedEffect(key1 = profileViewModel.parentData.collectAsState().value.data){
        profileViewModel.getChildData()
        Log.d(TAG, "chooseAccountActivity: ${profileViewModel.parentData.value.data}")
        if(profileViewModel.parentData.value.data != null){
            parentSectionLoading.value = false
        }

    }
    LaunchedEffect(key1 =  profileViewModel.childData.collectAsState().value.data){
        profileViewModel.getParentData()
        Log.d(TAG, "chooseAccountActivity: ${profileViewModel.childData.value.data}")
        if(profileViewModel.childData.value.data != null){
            childSectionLoading.value = false
        }
    }
    Column(modifier = Modifier.fillMaxSize() ,verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Orang tua", fontWeight = FontWeight.Bold, fontSize = 25.sp)
        if(!parentSectionLoading.value){
            Row(){
                profileCard(name = profileViewModel.parentData.collectAsState().value.data!!.parentName!!)

            }
        }else{
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                    CircularProgressIndicator()
            }
        }

        Text("Anak", fontWeight = FontWeight.Bold, fontSize = 25.sp)
        if(!childSectionLoading.value){
            LazyRow{
                items(items = profileViewModel.childData.value.data!!){child ->
                    profileCard(child.name!!)
                }
            }
        }else{
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                CircularProgressIndicator()
            }
        }

    }
}

