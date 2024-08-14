package com.example.loanconnect.presentation.navigation

import SignInScreen
import SignUpScreen
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.loanconnect.presentation.viewModels.AuthViewModel


@Composable
fun MyNavGraph(
    navController: NavHostController
) {
    val authViewModel: AuthViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = MyNavGraphRoutes.SignInScreen.route
    ) {
        composable(route = MyNavGraphRoutes.SignUpScreen.route) {
            SignUpScreen(navController, authViewModel)
        }
        composable(
            route = MyNavGraphRoutes.SignInScreen.route,
        ) {
            SignInScreen(navController, authViewModel)
        }
    }
}
