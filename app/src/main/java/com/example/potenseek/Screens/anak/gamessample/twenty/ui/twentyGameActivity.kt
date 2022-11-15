package com.example.potenseek.Screens.anak.gamessample.twenty.ui

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import com.example.potenseek.Screens.anak.gamessample.twenty.store.PreferenceRepository

class twentyGameActivity : ComponentActivity() {

    private val preferenceRepository: PreferenceRepository by lazy { PreferenceRepository(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        preferenceRepository.useSystemUiMode = true

        setContent {
            GameApp()
        }
    }

    override fun onResume() {
        super.onResume()
        preferenceRepository.paused = true
    }

}