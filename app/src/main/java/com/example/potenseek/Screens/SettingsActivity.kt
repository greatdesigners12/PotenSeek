package com.example.potenseek.Screens

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
import androidx.navigation.NavController
import com.example.potenseek.R
import com.example.potenseek.Screens.ui.theme.*
import com.example.potenseek.Screens.ui.theme.PotenSeekTheme
import com.example.potenseek.ui.theme.*

class SettingsActivity : ComponentActivity() {

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
fun Settings(navController: NavController) {
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState(), true)
        .background(color = GreyBackground)) {
        val mCheckedState = remember{
            mutableStateOf(false)
        }

        Box(modifier = Modifier
            .clip(shape = RoundedCornerShape(0.dp, 0.dp, 75.dp, 75.dp))
            .background(color = Coral)
            .wrapContentHeight()) {
            Image(painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24), contentDescription = "Back", modifier = Modifier
                .height(80.dp)
                .width(88.dp)
                .padding(24.dp, 16.dp))
            Column(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()) {
                Text(text = "Settings", modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(0.dp, 16.dp), color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Medium)
                Image(painter = painterResource(id = R.drawable.ic_baseline_settings_24), contentDescription = "Settings", modifier = Modifier
                    .height(88.dp)
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 24.dp))
            }

        }

        Spacer(Modifier.height(48.dp))

        Box(modifier = Modifier
            .shadow(elevation = 2.dp)
            .background(color = Color.White)
            .fillMaxWidth()) {
            Column() {
                Box(modifier = Modifier
                    .wrapContentHeight()
                    .shadow(elevation = 0.dp)
                    .border(1.dp, GreyBorder, RectangleShape)) {
                    Row(modifier = Modifier
                        .wrapContentHeight()
                        .align(alignment = Alignment.Center)
                        .fillMaxWidth()) {
                        Image(painter = painterResource(id = R.drawable.bank_logo), contentDescription = "Back", modifier = Modifier
                            .height(56.dp)
                            .width(64.dp)
                            .padding(16.dp, 8.dp)
                            .align(alignment = Alignment.CenterVertically),
                            colorFilter = ColorFilter.tint(Red700))
                        Text(text = "Connected Bank", modifier = Modifier
                            .align(alignment = Alignment.CenterVertically),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal)
                        Spacer(Modifier.weight(1f))
                        Image(painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_24), contentDescription = "Back", modifier = Modifier
                            .height(56.dp)
                            .width(88.dp)
                            .padding(24.dp, 8.dp)
                            .align(alignment = Alignment.CenterVertically),
                            colorFilter = ColorFilter.tint(Brown700))
                    }
                }
                Box(modifier = Modifier
                    .wrapContentHeight()
                    .border(1.dp, GreyBorder, RectangleShape)) {
                    Row(modifier = Modifier
                        .wrapContentHeight()
                        .align(alignment = Alignment.Center)
                        .fillMaxWidth()) {
                        Image(painter = painterResource(id = R.drawable.theme_logo), contentDescription = "Back", modifier = Modifier
                            .height(56.dp)
                            .width(64.dp)
                            .padding(16.dp, 8.dp)
                            .align(alignment = Alignment.CenterVertically),
                            colorFilter = ColorFilter.tint(Red700))
                        Text(text = "Theme Preference", modifier = Modifier
                            .align(alignment = Alignment.CenterVertically),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal)
                        Spacer(Modifier.weight(1f))
                        Image(painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_24), contentDescription = "Back", modifier = Modifier
                            .height(56.dp)
                            .width(88.dp)
                            .padding(24.dp, 8.dp)
                            .align(alignment = Alignment.CenterVertically),
                            colorFilter = ColorFilter.tint(Brown700))
                    }
                }
                Box(modifier = Modifier
                    .wrapContentHeight()
                    .border(1.dp, GreyBorder, RectangleShape)) {
                    Row(modifier = Modifier
                        .wrapContentHeight()
                        .align(alignment = Alignment.Center)
                        .fillMaxWidth()) {
                        Image(painter = painterResource(id = R.drawable.language_icon), contentDescription = "Back", modifier = Modifier
                            .height(56.dp)
                            .width(64.dp)
                            .padding(16.dp, 8.dp)
                            .align(alignment = Alignment.CenterVertically),
                            colorFilter = ColorFilter.tint(Red700))
                        Text(text = "Language", modifier = Modifier
                            .align(alignment = Alignment.CenterVertically),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal)
                        Spacer(Modifier.weight(1f))
                        Image(painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_24), contentDescription = "Back", modifier = Modifier
                            .height(56.dp)
                            .width(88.dp)
                            .padding(24.dp, 8.dp)
                            .align(alignment = Alignment.CenterVertically),
                            colorFilter = ColorFilter.tint(Brown700))
                    }
                }
                Box(modifier = Modifier
                    .wrapContentHeight()
                    .border(1.dp, GreyBorder, RectangleShape)) {
                    Row(modifier = Modifier
                        .wrapContentHeight()
                        .align(alignment = Alignment.Center)
                        .fillMaxWidth()) {
                        Image(painter = painterResource(id = R.drawable.ic_baseline_notifications_24), contentDescription = "Back", modifier = Modifier
                            .height(56.dp)
                            .width(64.dp)
                            .padding(16.dp, 8.dp)
                            .align(alignment = Alignment.CenterVertically),
                            colorFilter = ColorFilter.tint(Red700))
                        Text(text = "Notification", modifier = Modifier
                            .align(alignment = Alignment.CenterVertically),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal)
                        Spacer(Modifier.weight(1f))
                        Switch(checked = mCheckedState.value, onCheckedChange = {
                            mCheckedState.value = it
                        }, colors = SwitchDefaults.colors(
                            checkedThumbColor = Orange300,
                            uncheckedThumbColor = Grey300,
                            checkedTrackColor = Color.Black,
                            uncheckedTrackColor = Color.Black
                        ), modifier = Modifier
                            .height(56.dp)
                            .width(88.dp)
                            .padding(24.dp, 8.dp)
                            .align(alignment = Alignment.CenterVertically)
                            .scale(1.25f))
                    }
                }
                Box(modifier = Modifier
                    .wrapContentHeight()
                    .border(1.dp, GreyBorder, RectangleShape)) {
                    Row(modifier = Modifier
                        .wrapContentHeight()
                        .align(alignment = Alignment.Center)
                        .fillMaxWidth()) {
                        Image(painter = painterResource(id = R.drawable.ic_baseline_privacy_tip_24), contentDescription = "Back", modifier = Modifier
                            .height(56.dp)
                            .width(64.dp)
                            .padding(16.dp, 8.dp)
                            .align(alignment = Alignment.CenterVertically),
                            colorFilter = ColorFilter.tint(Red700))
                        Text(text = "Privacy & Security", modifier = Modifier
                            .align(alignment = Alignment.CenterVertically),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal)
                        Spacer(Modifier.weight(1f))
                        Image(painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_24), contentDescription = "Back", modifier = Modifier
                            .height(56.dp)
                            .width(88.dp)
                            .padding(24.dp, 8.dp)
                            .align(alignment = Alignment.CenterVertically),
                            colorFilter = ColorFilter.tint(Brown700))
                    }
                }
                Box(modifier = Modifier
                    .wrapContentHeight()
                    .border(1.dp, GreyBorder, RectangleShape)) {
                    Row(modifier = Modifier
                        .wrapContentHeight()
                        .align(alignment = Alignment.Center)
                        .fillMaxWidth()) {
                        Image(painter = painterResource(id = R.drawable.ic_baseline_aboutus_24), contentDescription = "Back", modifier = Modifier
                            .height(56.dp)
                            .width(64.dp)
                            .padding(16.dp, 8.dp)
                            .align(alignment = Alignment.CenterVertically),
                            colorFilter = ColorFilter.tint(Red700))
                        Text(text = "About Us", modifier = Modifier
                            .align(alignment = Alignment.CenterVertically),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal)
                        Spacer(Modifier.weight(1f))
                        Image(painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_24), contentDescription = "Back", modifier = Modifier
                            .height(56.dp)
                            .width(88.dp)
                            .padding(24.dp, 8.dp)
                            .align(alignment = Alignment.CenterVertically),
                            colorFilter = ColorFilter.tint(Brown700))
                    }
                }
                Box(modifier = Modifier
                    .wrapContentHeight()
                    .border(1.dp, GreyBorder, RectangleShape)) {
                    Row(modifier = Modifier
                        .wrapContentHeight()
                        .align(alignment = Alignment.Center)
                        .fillMaxWidth()) {
                        Image(painter = painterResource(id = R.drawable.ic_baseline_help_outline_24), contentDescription = "Back", modifier = Modifier
                            .height(56.dp)
                            .width(64.dp)
                            .padding(16.dp, 8.dp)
                            .align(alignment = Alignment.CenterVertically),
                            colorFilter = ColorFilter.tint(Red700))
                        Text(text = "Help & Support", modifier = Modifier
                            .align(alignment = Alignment.CenterVertically),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal)
                        Spacer(Modifier.weight(1f))
                        Image(painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_24), contentDescription = "Back", modifier = Modifier
                            .height(56.dp)
                            .width(88.dp)
                            .padding(24.dp, 8.dp)
                            .align(alignment = Alignment.CenterVertically),
                            colorFilter = ColorFilter.tint(Brown700))
                    }
                }
                Box(modifier = Modifier
                    .wrapContentHeight()
                    .border(1.dp, GreyBorder, RectangleShape)) {
                    Row(modifier = Modifier
                        .wrapContentHeight()
                        .align(alignment = Alignment.Center)
                        .fillMaxWidth()) {
                        Image(painter = painterResource(id = R.drawable.ic_baseline_account_circle_24), contentDescription = "Back", modifier = Modifier
                            .height(56.dp)
                            .width(64.dp)
                            .padding(16.dp, 8.dp)
                            .align(alignment = Alignment.CenterVertically),
                            colorFilter = ColorFilter.tint(Red700))
                        Text(text = "Account", modifier = Modifier
                            .align(alignment = Alignment.CenterVertically),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal)
                        Spacer(Modifier.weight(1f))
                        Image(painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_24), contentDescription = "Back", modifier = Modifier
                            .height(56.dp)
                            .width(88.dp)
                            .padding(24.dp, 8.dp)
                            .align(alignment = Alignment.CenterVertically),
                            colorFilter = ColorFilter.tint(Brown700))
                    }
                }
                Box(modifier = Modifier
                    .wrapContentHeight()
                    .border(1.dp, GreyBorder, RectangleShape)) {
                    Row(modifier = Modifier
                        .wrapContentHeight()
                        .align(alignment = Alignment.Center)
                        .fillMaxWidth()) {
                        Image(painter = painterResource(id = R.drawable.ic_baseline_logout_24), contentDescription = "Back", modifier = Modifier
                            .height(56.dp)
                            .width(64.dp)
                            .padding(16.dp, 8.dp)
                            .align(alignment = Alignment.CenterVertically),
                            colorFilter = ColorFilter.tint(Red700))
                        Text(text = "Logout", modifier = Modifier
                            .align(alignment = Alignment.CenterVertically),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal)
                        Spacer(Modifier.weight(1f))
                        Image(painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_24), contentDescription = "Back", modifier = Modifier
                            .height(56.dp)
                            .width(88.dp)
                            .padding(24.dp, 8.dp)
                            .align(alignment = Alignment.CenterVertically),
                            colorFilter = ColorFilter.tint(Brown700))
                    }
                }
            }
            Spacer(Modifier.height(60.dp))
        }
    }

}