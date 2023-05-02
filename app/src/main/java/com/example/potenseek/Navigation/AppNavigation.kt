package com.example.potenseek.Navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.potenseek.Screens.Settings
import com.example.potenseek.Screens.SplashScreenActivity

import com.example.potenseek.Screens.anak.homepageanak
import com.example.potenseek.Screens.anak.profile
import com.example.potenseek.Screens.authentication.*
import com.example.potenseek.Screens.homeortu.HomeortuScreen

import com.example.potenseek.Screens.teacherpsychologisthomepage.TeacherPsychologistHome
import com.example.potenseek.Screens.teacherpsychologisthomepage.TeacherPsychologistHomeViewModel
import com.example.potenseek.Screens.authentication.AuthViewModel
import com.example.potenseek.Screens.authentication.LoginScreenActivity
import com.example.potenseek.Screens.authentication.RegisterScreenActivity
import com.example.potenseek.Screens.authentication.*
import com.example.potenseek.Screens.editprofile.TeacherEditProfile
import com.example.potenseek.Screens.editprofile.TeacherEditProfileViewModel
import com.example.potenseek.Screens.homeortu.HomeortuViewModel

import com.example.potenseek.Screens.homepage.HomeScreenActivity
import com.example.potenseek.Screens.inbox.Inbox
import com.example.potenseek.Screens.inbox.InboxActivity
import com.example.potenseek.Screens.inbox.InboxViewModel
import com.example.potenseek.Screens.profileanak.ProfileanakScreen
import com.example.potenseek.Screens.profileanak.ProfileanakViewModel
import com.example.potenseek.Screens.schedule.TeacherSchedule
import com.example.potenseek.Screens.schedule.TeacherScheduleViewModel
import com.example.potenseek.Screens.statistics.StatisticScreen
import com.example.potenseek.Screens.statistics.StatisticViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

@Composable
fun AppNavigation(authViewModel: AuthViewModel = viewModel(),
                  profileViewModel: ProfileViewModel = viewModel(),
                  teacherPsychologistHomeViewModel: TeacherPsychologistHomeViewModel = viewModel(),
                  teacherEditProfileViewModel: TeacherEditProfileViewModel = viewModel(),
                  inboxViewModel: InboxViewModel = viewModel(),
                  teacherScheduleViewModel: TeacherScheduleViewModel = viewModel(),
                  profileanakViewModel: ProfileanakViewModel = viewModel(),
                  statisticViewModel: StatisticViewModel = hiltViewModel(),
                  homeOrtuViewModel: HomeortuViewModel = hiltViewModel(),



) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = NavigationEnum.SplashScreenActivity.name){
        composable(NavigationEnum.SplashScreenActivity.name){
            SplashScreenActivity(navController)
        }

        composable(NavigationEnum.LoginScreenActivity.name){
            LoginScreenActivity(navController, authViewModel, profileViewModel)
        }

        composable(NavigationEnum.RegisterScreenActivity.name){
            RegisterScreenActivity(navController, authViewModel)
        }

        composable(NavigationEnum.SettingsActivity.name){
            Settings(navController)
        }

        composable(NavigationEnum.TeacherPsychologistHomeActivity.name){
            TeacherPsychologistHome(navController, teacherPsychologistHomeViewModel)
        }

        composable(NavigationEnum.TeacherScheduleActivity.name){
            TeacherSchedule(navController, teacherScheduleViewModel)
        }

        composable(NavigationEnum.TeacherEditProfileActivity.name){
            TeacherEditProfile(navController, teacherEditProfileViewModel)
        }

        composable(NavigationEnum.InboxActivity.name){
            Inbox(inboxViewModel)
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

        composable(NavigationEnum.HomePageAnakActivity.name){
            homepageanak(navController)
        }

        composable(NavigationEnum.HomeParentActivity.name){
            HomeortuScreen( navController, homeOrtuViewModel )
        }

        composable(NavigationEnum.RegisterJobActivity.name){
            RegisterJobActivity(navController, authViewModel)
        }

        composable(NavigationEnum.ProfileAnakActivity.name){
            profile(navController)
        }

        composable(NavigationEnum.StatisticActivity.name){
            StatisticScreen( statisticViewModel, navController)
        }

        composable(NavigationEnum.StatisticActivity.name){
            StatisticScreen( statisticViewModel, navController)
        }

        composable(NavigationEnum.TeacherEditProfileActivity.name){
            TeacherEditProfile(navController = navController, teacherEditProfileViewModel = teacherEditProfileViewModel)
        }

        composable(NavigationEnum.profileChildScreenActivity.name){
            ProfileanakScreen( profileanakViewModel,navController)
        }

    }
}