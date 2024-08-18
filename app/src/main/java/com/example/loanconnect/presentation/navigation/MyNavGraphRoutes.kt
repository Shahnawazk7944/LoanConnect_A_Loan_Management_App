package com.example.loanconnect.presentation.navigation

sealed class MyNavGraphRoutes(val route: String) {

    data object HomeScreen : MyNavGraphRoutes(route = "homeScreen")
    data object SignInScreen : MyNavGraphRoutes(route = "signInScreen")
    data object SignUpScreen : MyNavGraphRoutes(route = "signUpScreen")
    data object ProfileScreen : MyNavGraphRoutes(route = "profileScreen")
    data object AdminScreen : MyNavGraphRoutes(route = "adminScreen")
    data object AdminScreenCheckAllUsers :
        MyNavGraphRoutes(route = "adminScreenCheckAllUsers")

    data object AdminScreenCheckAllLoanApplications :
        MyNavGraphRoutes(route = "adminScreenCheckAllLoanApplications")

    data object AdminScreenCheckUserLoans :
        MyNavGraphRoutes(route = "adminScreenCheckAllLoanApplications/{userIndex}") {
        fun passUserIndex(userIndex: Int): String {
            return "adminScreenCheckAllLoanApplications/$userIndex"
        }
    }
}


