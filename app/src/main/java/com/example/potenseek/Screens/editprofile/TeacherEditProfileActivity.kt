package com.example.potenseek.Screens.editprofile

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.potenseek.Model.CertificateAchievement
import com.example.potenseek.Navigation.NavigationEnum
import com.example.potenseek.R
import com.example.potenseek.Screens.ui.theme.*
import com.example.potenseek.Screens.ui.theme.PotenSeekTheme
import com.example.potenseek.components.CertificateAchievementCard
import com.example.potenseek.components.TeacherPsychologistCard
import com.example.potenseek.ui.theme.*

class TeacherEditProfileActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PotenSeekTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                }
            }
        }
    }
}

@Composable
fun TeacherEditProfile(navController: NavController, teacherEditProfileViewModel: TeacherEditProfileViewModel) {
    val certificateAchievementSectionLoading = remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 =  teacherEditProfileViewModel.certificateAchievementData.collectAsState().value.data){
        teacherEditProfileViewModel.getCertificateAchievementData("0Z0AiQBhYGqUqukSWia7")
        Log.d(ContentValues.TAG, "teacherPsychologistHomeData: ${teacherEditProfileViewModel.certificateAchievementData.value.data}")
        if(teacherEditProfileViewModel.certificateAchievementData.value.data != null){
            certificateAchievementSectionLoading.value = false
        }
    }

    Column(modifier = Modifier
        .verticalScroll(rememberScrollState(), true)
        .background(color = GreyBackground)) {
        Box(modifier = Modifier
            .clip(shape = RoundedCornerShape(0.dp, 0.dp, 75.dp, 75.dp))
            .background(color = Coral)
            .wrapContentHeight()
            .fillMaxWidth()
        ) {
            Image(painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24), contentDescription = "Back", modifier = Modifier
                .height(72.dp)
                .width(88.dp)
                .padding(16.dp, 16.dp).clickable {
                    navController.popBackStack()
                    navController.navigate(NavigationEnum.TeacherPsychologistHomeActivity.name)
                })
            Column(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()) {

                Image(painter = painterResource(id = R.drawable.defaultprofile), contentDescription = "Edit Profile", modifier = Modifier
                    .height(144.dp)
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(0.dp, 0.dp, 0.dp, 24.dp)
                    .clip(shape = RoundedCornerShape(16.dp)))
                Box(modifier = Modifier
                    .clip(shape = RoundedCornerShape(8.dp))
                    .align(Alignment.CenterHorizontally)) {
                    Text(
                        text = "Edit Profile",
                        modifier = Modifier
                            .background(color = Coral100)
                            .padding(8.dp, 8.dp),
                        color = Color.Black,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Surface(shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                    shadowElevation = 6.dp) {
                    Row(modifier = Modifier
                        .wrapContentWidth()
                        .background(color = Coral200)
                        .padding(20.dp, 4.dp)) {
                        Text(
                            text = "Rate: ",
                            color = Coral,
                            fontSize = 24.sp
                        )
                        Image(painter = painterResource(id = R.drawable.ic_baseline_star_rate_24), contentDescription = "Back", modifier = Modifier
                            .height(32.dp)
                            .width(32.dp)
                            .align(alignment = Alignment.CenterVertically))
                        Text(
                            text = "4.88",
                            color = Coral,
                            fontSize = 24.sp
                        )
                    }
                }
                Text(
                    text = "Psychologist",
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(0.dp, 20.dp)
                        .align(alignment = Alignment.CenterHorizontally)
                )
            }
        }
        Text(text = "About Me",
            color = Color.Black,
            modifier = Modifier
                .padding(20.dp, 16.dp),
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium
        )
        Box(modifier = Modifier
            .padding(20.dp, 0.dp)
            .wrapContentWidth()
            .background(color = GreyBackground)) {
            Surface(shadowElevation = 4.dp,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .background(color = GreyBackground)
            ) {
                Row(modifier = Modifier
                    .background(color = Color.White)) {
                    Text(text = "I am a qualified Child Psychologist. I have 25 years experience in this field which makes me an expert.",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(26.dp, 12.dp),
                        fontSize = 18.sp
                    )
                }
            }
        }
        Text(text = "Certificate And Achievement",
            color = Color.Black,
            modifier = Modifier
                .padding(20.dp, 16.dp, 20.dp, 8.dp),
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium
        )

        if (certificateAchievementSectionLoading.value) {
            Spacer(modifier = Modifier.height(60.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                CircularProgressIndicator()
            }
            Spacer(modifier = Modifier.height(60.dp))
        } else {
            Row {
                LazyRow(modifier = Modifier
                    .padding(12.dp, 0.dp)) {
                    var cert = ArrayList<CertificateAchievement>(teacherEditProfileViewModel.certificateAchievementData.value.data!!)
                    cert.add(CertificateAchievement("add", "add"))
                    itemsIndexed(items = cert) {index, item ->
                        CertificateAchievementCard(certificateAchievement = item)
                    }
                }
                Surface(modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp, 8.dp)
                    .background(color = GreyBackground),
                    shape = RoundedCornerShape(8.dp),
                    shadowElevation = 6.dp
                ) {
                    Column(modifier = Modifier
                        .wrapContentWidth()
                        .background(color = Red800)
                        .height(height = 216.dp)
                        .padding(16.dp, 0.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center) {
                        Image(painter = painterResource(id = R.drawable.ic_baseline_add_24), contentDescription = "Add Medal", modifier = Modifier
                            .height(88.dp)
                            .width(128.dp))
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier,
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Coral
                ),
                elevation = ButtonDefaults.elevation(10.dp)
            ) {
                Text(
                    text = "Save Changes",
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
    }

}