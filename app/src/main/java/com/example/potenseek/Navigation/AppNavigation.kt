package com.example.potenseek.Navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.potenseek.Screens.SplashScreenActivity
import com.example.potenseek.Screens.authentication.AuthViewModel
import com.example.potenseek.Screens.authentication.LoginScreenActivity
import com.example.potenseek.Screens.authentication.RegisterScreenActivity

@Composable
fun AppNavigation(authViewModel: AuthViewModel = viewModel()) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = NavigationEnum.SplashScreenActivity.name){
        composable(NavigationEnum.SplashScreenActivity.name){
            SplashScreenActivity(navController)
        }

        composable(NavigationEnum.LoginScreenActivity.name){
            LoginScreenActivity(navController)
        }

        composable(NavigationEnum.RegisterScreenActivity.name){
            RegisterScreenActivity(navController, authViewModel)
        }
    }
}