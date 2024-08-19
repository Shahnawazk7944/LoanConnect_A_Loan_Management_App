package com.example.loanconnect.presentation.features.admin


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.loanconnect.R
import com.example.loanconnect.presentation.features.auth.components.AuthScreensHeading
import com.example.loanconnect.presentation.features.components.AdminCheckUserAllLoans
import com.example.loanconnect.presentation.states.AdminEvents
import com.example.loanconnect.presentation.states.AdminStates
import com.example.loanconnect.presentation.states.AuthStates
import com.example.loanconnect.presentation.viewModels.AdminViewModel
import com.example.loanconnect.ui.theme.components.CustomTopAppBar
import com.example.loanconnect.ui.theme.components.LoadingDialog
import com.example.loanconnect.ui.theme.spacing


@Composable
fun LoanHistoryScreen(
    navController: NavHostController,
    adminState: AdminStates,
    authStates: AuthStates,
    //this is just for testing just test the loan history of user
    adminViewModel: AdminViewModel
) {
    Scaffold(
        topBar = {
            // CustomTopAppBar
            CustomTopAppBar(onClick = { navController.navigateUp() }, title = {}, actions = {})
        },
    ) { innerPadding ->
        LaunchedEffect(true) {
            adminViewModel.onEvent(AdminEvents.GetUserData(true))
        }
        LoadingDialog(adminState.loadingAdminData)
        val user = adminState.usersData.find { it.mobile == authStates.contactNumber }
        if (user != null && user.loans.isNotEmpty()) {
            val loans = user.loans
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(0.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                item {
                    AuthScreensHeading(
                        "Loans History",
                        subHeading = "Access funds quickly and easily "
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
                }

                items(loans) { loan ->
                    AdminCheckUserAllLoans(
                        loanIcon = painterResource(R.drawable.give_loans_icon),
                        loanId = loan.loanId.toString(),
                        loanAmount = loan.loanAmount.toString(),
                        loanDuration = loan.duration,
                        loanStatus = loan.loanStatus
                    )
                }


            }


        } else {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AuthScreensHeading(
                    "Not Applied For Any Loan Yet",
                )
            }
        }
    }
}
