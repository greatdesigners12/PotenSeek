package com.example.potenseek.Screens.authentication

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.navigation.NavController
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.potenseek.Model.ChildProfile
import com.example.potenseek.Navigation.NavigationEnum
import com.example.potenseek.R
import com.example.potenseek.components.*
import com.google.firebase.auth.FirebaseAuth

@Composable
fun chooseAccountActivity(navController: NavController, profileViewModel: ProfileViewModel) {
    val parentSectionLoading = remember{
        mutableStateOf(true)
    }

    val childSectionLoading = remember{
        mutableStateOf(true)
    }

    val childData = remember{
        mutableStateOf<List<ChildProfile>>(listOf<ChildProfile>())
    }

    val openPinDialog = remember{
        mutableStateOf(false)
    }

    val pin = remember{
        mutableStateOf("")
    }

    val idUser = remember{
        mutableStateOf("")
    }

    val isAdd = remember{
        mutableStateOf(false)
    }

    val isModalLoading = remember{
        mutableStateOf(true)
    }

    val alertTitle : (Boolean) -> String = {
        if(it) "Tambahkan" else "Verifikasi"
    }

    val isBtnModalLoading = remember{
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true){
        if(FirebaseAuth.getInstance().currentUser == null){
            navController.popBackStack()
            navController.navigate(NavigationEnum.LoginScreenActivity.name)
        }


    }


    LaunchedEffect(profileViewModel.isPinExist.collectAsState().value){
        if(profileViewModel.isPinExist.value != null){
            isAdd.value = !profileViewModel.isPinExist.value.data!!
            isModalLoading.value = false
            Log.d(TAG, "chooseAccountActivity: ${profileViewModel.isPinExist.value.data!!}")
        }

    }

    LaunchedEffect(profileViewModel.isPinCorrent.collectAsState().value){
        if(profileViewModel.isPinCorrent.value.data != null){
            if(profileViewModel.isPinCorrent.value.data == true){
                Log.d(TAG, "${profileViewModel.isPinCorrent.value.data}")
                profileViewModel.isPinCorrent.value.data = null
                Log.d(TAG, "bruhhhh wae")
                Log.d(TAG, "${profileViewModel.isPinAdded.value.data}")
                navController.popBackStack()
                navController.navigate(NavigationEnum.HomePageAnakActivity.name)
            }else{
                isBtnModalLoading.value = false
            }
        }
    }

    LaunchedEffect(profileViewModel.isPinAdded.collectAsState().value){
        if(profileViewModel.isPinAdded.value != null){
            if(profileViewModel.isPinAdded.value.data == true){

                profileViewModel.isPinAdded.value.data = null
                navController.popBackStack()
                navController.navigate(NavigationEnum.HomePageAnakActivity.name)
            }else{
                isBtnModalLoading.value = false
            }
        }
    }

    LaunchedEffect(profileViewModel.isParentPinExist.collectAsState().value){
        if(profileViewModel.isParentPinExist.value.data != null){
            isAdd.value = !profileViewModel.isParentPinExist.value.data!!
            isModalLoading.value = false
            Log.d(TAG, "chooseAccountActivity: ${profileViewModel.isParentPinExist.value.data!!}")
        }

    }

    LaunchedEffect(profileViewModel.isParentPinCorrent.collectAsState().value){
        if(profileViewModel.isParentPinCorrent.value.data != null){
            if(profileViewModel.isParentPinCorrent.value.data == true){
                navController.popBackStack()
                navController.navigate(NavigationEnum.HomeParentActivity.name)
            }else{
                isBtnModalLoading.value = false
            }
        }
    }

    LaunchedEffect(profileViewModel.isParentPinAdded.collectAsState().value){
        if(profileViewModel.isParentPinAdded.value.data != null){
            if(profileViewModel.isParentPinAdded.value.data == true){
                navController.popBackStack()
                navController.navigate(NavigationEnum.HomeParentActivity.name)
            }else{
                isBtnModalLoading.value = false
            }
        }
    }


    if(openPinDialog.value){
        CustomAlertDialog(title = "${alertTitle(isAdd.value)} PIN", onDismiss = {
            pin.value = ""
            openPinDialog.value = false
        }) {
            if(isModalLoading.value){
                CircularProgressIndicator()
            }else{

                Column() {
                    basicInputField(label = "PIN", inputValue = pin.value, keyboardType = KeyboardType.Number){
                        pin.value = it

                    }
                    BasicButton(components = { if(isBtnModalLoading.value) CircularProgressIndicator(color=Color.White) else Text("MASUK") }) {
                        isBtnModalLoading.value = true
                        if(isAdd.value){
                            if(idUser.value == ""){
                                profileViewModel.createParentPin(pin.value)
                            }else{
                                profileViewModel.createPin(idUser.value, pin.value)
                            }

                        }else{
                            if(idUser.value == ""){
                                profileViewModel.checkIfParentPinCorrect(pin.value)
                            }else{
                                profileViewModel.checkIfPinCorrect(idUser.value, pin.value)
                            }

                        }

                    }
                }
            }


        }
    }


    LaunchedEffect(key1 = profileViewModel.parentData.collectAsState().value.data){
        profileViewModel.getChildData()
        profileViewModel.getParentData()
        Log.d(TAG, "chooseAccountActivity: ${profileViewModel.parentData.value.data}")
        if(profileViewModel.parentData.value.data != null){
            parentSectionLoading.value = false
        }

    }

    LaunchedEffect(key1 = profileViewModel.childData.collectAsState().value){
        childData.value = profileViewModel.childData.value
        childSectionLoading.value = false
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp), contentAlignment = Alignment.TopEnd){
        Image(painter = painterResource(id = R.drawable.ic_baseline_exit), contentDescription = "logout", modifier = Modifier.clickable {
            FirebaseAuth.getInstance().signOut()
            navController.popBackStack()
            navController.navigate(NavigationEnum.LoginScreenActivity.name)
        })
    }

    Column(modifier = Modifier.fillMaxSize() ,verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        heading()
        Text("Orang tua", fontWeight = FontWeight.Bold, fontSize = 25.sp)
        if(!parentSectionLoading.value){
            Row(){
                if(FirebaseAuth.getInstance().currentUser != null){
                    profileCard(name = profileViewModel.parentData.collectAsState().value.data!!.name!!){
                        openPinDialog.value = !openPinDialog.value
                        profileViewModel.checkIfParentPinExist()
                        isModalLoading.value = true
                    }
                }

            }
        }else{
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                    CircularProgressIndicator()
            }
        }

        Text("Anak", fontWeight = FontWeight.Bold, fontSize = 25.sp)
        if(!childSectionLoading.value){
            LazyRow{
                items(items = childData.value){child ->
                    profileCard(child.name!!){
                        idUser.value = child.id.toString()
                        openPinDialog.value = !openPinDialog.value
                        profileViewModel.checkIfPinExist(idUser.value)
                        isModalLoading.value = true
                    }

                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
        }else{
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                CircularProgressIndicator()
            }
        }

    }
}


