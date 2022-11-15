package com.example.potenseek

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.potenseek.Navigation.AppNavigation
import com.example.potenseek.Screens.authentication.AuthViewModel
import com.example.potenseek.Screens.homeortu.HomeortuScreen
import com.example.potenseek.Screens.profileanak.ProfileanakScreen
import com.example.potenseek.Screens.schedule.ScheduleScreen
import com.example.potenseek.Screens.statistics.StatisticScreen
import com.example.potenseek.ui.theme.PotenSeekTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PotenSeekTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

