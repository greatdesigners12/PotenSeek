package com.example.potenseek.Navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.potenseek.Screens.Settings
import com.example.potenseek.Screens.SplashScreenActivity
import com.example.potenseek.Screens.TPHome.TeacherPsychologistHome
import com.example.potenseek.Screens.authentication.AuthViewModel
import com.example.potenseek.Screens.authentication.LoginScreenActivity
import com.example.potenseek.Screens.authentication.RegisterScreenActivity
import com.example.potenseek.Screens.authentication.*
import com.example.potenseek.Screens.homepage.HomeScreenActivity

@Composable
fun AppNavigation(authViewModel: AuthViewModel = viewModel(), profileViewModel: ProfileViewModel = viewModel()) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = NavigationEnum.TeacherPsychologistHomeActivity.name){
        composable(NavigationEnum.SplashScreenActivity.name){
            SplashScreenActivity(navController)
        }

        composable(NavigationEnum.LoginScreenActivity.name){
            LoginScreenActivity(navController, authViewModel)
        }

        composable(NavigationEnum.RegisterScreenActivity.name){
            RegisterScreenActivity(navController, authViewModel)
        }

        composable(NavigationEnum.SettingsActivity.name){
            Settings()
        }

        composable(NavigationEnum.TeacherPsychologistHomeActivity.name){
            TeacherPsychologistHome()
        }

        composable(NavigationEnum.InputUserDetailActivity.name){
            inputUserDetailActivity(navController, profileViewModel)
        }

        composable(NavigationEnum.ChooseProfileActivity.name){
            chooseAccountActivity(navController, profileViewModel)
        }

        composable(NavigationEnum.HomeScreenActivity.name){
            HomeScreenActivity(navController)
        }
    }
}