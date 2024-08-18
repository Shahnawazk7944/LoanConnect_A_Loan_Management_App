package com.example.loanconnect.presentation.navigation

import HomeScreen
import SignInScreen
import SignUpScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.loanconnect.presentation.features.admin.AdminScreen
import com.example.loanconnect.presentation.features.admin.AdminScreenCheckAllLoanApplications
import com.example.loanconnect.presentation.features.admin.AdminScreenCheckAllUsers
import com.example.loanconnect.presentation.features.admin.AdminScreenCheckUserLoans
import com.example.loanconnect.presentation.features.profile.StoreProfileScreen
import com.example.loanconnect.presentation.viewModels.AdminViewModel
import com.example.loanconnect.presentation.viewModels.AppViewModel
import com.example.loanconnect.presentation.viewModels.AuthViewModel


@Composable
fun MyNavGraph(
    navController: NavHostController
) {
    val authViewModel: AuthViewModel = hiltViewModel()
    val appViewModel: AppViewModel = hiltViewModel()
    val adminViewModel: AdminViewModel = hiltViewModel()
    val authState by authViewModel.authStates.collectAsStateWithLifecycle()
    val appState by appViewModel.appStates.collectAsStateWithLifecycle()
    val adminState by adminViewModel.adminStates.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = MyNavGraphRoutes.AdminScreenCheckAllUsers.route
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
                navController = navController,appState = appState,
                appViewModel = appViewModel, authState = authState
            )
        }
        composable(
            route = MyNavGraphRoutes.AdminScreen.route,
        ) {
            AdminScreen(
                navController = navController,
                adminViewModel = adminViewModel, adminState = adminState
            )
        }
        composable(
            route = MyNavGraphRoutes.AdminScreenCheckAllUsers.route,
        ) {
            AdminScreenCheckAllUsers(
                navController = navController, adminState = adminState
            )
        }
        composable(
            route = MyNavGraphRoutes.AdminScreenCheckAllLoanApplications.route,
        ) {
            AdminScreenCheckAllLoanApplications(
                navController = navController, adminState = adminState
            )
        }
        composable(
            route = MyNavGraphRoutes.AdminScreenCheckUserLoans.route,
            arguments = listOf(
                navArgument("userIndex") { type = NavType.StringType }
            )
        ) { navBackStackEntry ->
            val userIndex = navBackStackEntry.arguments?.getString("userIndex")
            if (userIndex != null) {
                AdminScreenCheckUserLoans(
                    navController = navController, adminState = adminState
                )
            }
        }
    }
}
