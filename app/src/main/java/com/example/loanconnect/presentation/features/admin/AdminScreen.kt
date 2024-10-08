package com.example.loanconnect.presentation.features.admin


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.loanconnect.R
import com.example.loanconnect.presentation.features.auth.components.AuthScreensHeading
import com.example.loanconnect.presentation.features.components.AdminCheckUsersOrLoans
import com.example.loanconnect.presentation.navigation.MyNavGraphRoutes
import com.example.loanconnect.presentation.states.AdminEvents
import com.example.loanconnect.presentation.states.AdminStates
import com.example.loanconnect.presentation.viewModels.AdminViewModel
import com.example.loanconnect.ui.theme.components.CustomTopAppBar
import com.example.loanconnect.ui.theme.components.LoadingDialog
import com.example.loanconnect.ui.theme.spacing


@Composable
fun AdminScreen(
    navController: NavHostController,
    adminViewModel: AdminViewModel,
    adminState: AdminStates,
) {
    Scaffold(
        topBar = {
            // CustomTopAppBar
            CustomTopAppBar(onClick = { navController.navigateUp() }, title = {}, actions = {})
        },
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoadingDialog(adminState.loadingAdminData)

            AuthScreensHeading(
                "LoanConnect Admin Panel",
                subHeading = "Access funds quickly and easily "
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))

            if (adminState.usersData.isEmpty()) {
                Button(onClick = { adminViewModel.onEvent(AdminEvents.GetUserData(true)) }) {
                    Text(text = "Get User Data")
                }
            } else {

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))

                AdminCheckUsersOrLoans(
                    painter = painterResource(R.drawable.all_users_icon),
                    content = "Check All Users Data",
                    onClick = {
                        navController.navigate(MyNavGraphRoutes.AdminScreenCheckAllUsers.route)
                    }
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
                AdminCheckUsersOrLoans(
                    painter = painterResource(R.drawable.give_loans_icon),
                    content = "Check All Loan Applications",
                    onClick = {
                        navController.navigate(MyNavGraphRoutes.AdminScreenCheckAllLoanApplications.route)
                    }
                )
            }

        }
    }
}
