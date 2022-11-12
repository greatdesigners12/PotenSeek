package com.example.potenseek.Screens.editprofile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Switch
import androidx.compose.material.SwitchColors
import androidx.compose.material.SwitchDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.potenseek.R
import com.example.potenseek.Screens.ui.theme.*
import com.example.potenseek.Screens.ui.theme.PotenSeekTheme
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
                    Settings()
                }
            }
        }
    }
}

@Composable
fun Settings() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState(), true)) {
        val mCheckedState = remember{
            mutableStateOf(false)
        }

        Box(modifier = Modifier
            .clip(shape = RoundedCornerShape(0.dp, 0.dp, 75.dp, 75.dp))
            .background(color = Coral)
            .wrapContentHeight()
            .fillMaxWidth()
        ) {
            Image(painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24), contentDescription = "Back", modifier = Modifier
                .height(72.dp)
                .width(88.dp)
                .padding(16.dp, 16.dp))
            Column(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()) {
                Text(text = "Edit Profile", modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(0.dp, 16.dp), color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Medium)
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
            .wrapContentWidth(),) {
            Surface(shadowElevation = 4.dp,
                shape = RoundedCornerShape(16.dp)
            ) {
                Row() {
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
                .padding(20.dp, 16.dp),
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium
        )
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    PotenSeekTheme {
        Settings()
    }
}