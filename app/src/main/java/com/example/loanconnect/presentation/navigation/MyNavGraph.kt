package com.example.loanconnect.presentation.navigation

import HomeScreen
import SignInScreen
import SignUpScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.loanconnect.presentation.features.profile.StoreProfileScreen
import com.example.loanconnect.presentation.viewModels.AppViewModel
import com.example.loanconnect.presentation.viewModels.AuthViewModel


@Composable
fun MyNavGraph(
    navController: NavHostController
) {
    val authViewModel: AuthViewModel = hiltViewModel()
    val appViewModel: AppViewModel = hiltViewModel()
    val authState by authViewModel.authStates.collectAsStateWithLifecycle()
    val appState by appViewModel.appStates.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = MyNavGraphRoutes.ProfileScreen.route
    ) {
        composable(route = MyNavGraphRoutes.SignUpScreen.route) {
            SignUpScreen(navController, authViewModel, authState)
        }
        composable(
            route = MyNavGraphRoutes.SignInScreen.route,
        ) {
            SignInScreen(navController, authViewModel, authState)
        }
        composable(
            route = MyNavGraphRoutes.HomeScreen.route,
        ) {
            HomeScreen(
                navController = navController, authViewModel = authViewModel,
                appState = appState, appViewModel = appViewModel, authState = authState
            )
        }
        composable(
            route = MyNavGraphRoutes.ProfileScreen.route,
        ) {
            StoreProfileScreen(
                navController = navController,appState = appState, appViewModel = appViewModel, authState = authState
            )
        }
    }
}
