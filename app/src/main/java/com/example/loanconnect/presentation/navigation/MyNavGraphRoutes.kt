package com.example.loanconnect.presentation.navigation

sealed class MyNavGraphRoutes(val route: String) {

    data object HomeScreen : MyNavGraphRoutes(route = "homeScreen")
    data object SignInScreen : MyNavGraphRoutes(route = "signInScreen")
    data object SignUpScreen : MyNavGraphRoutes(route = "signUpScreen")

}