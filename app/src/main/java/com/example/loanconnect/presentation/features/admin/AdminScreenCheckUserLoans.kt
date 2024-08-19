package com.example.loanconnect.presentation.features.admin


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.loanconnect.R
import com.example.loanconnect.presentation.features.auth.components.AuthScreensHeading
import com.example.loanconnect.presentation.features.components.AdminCheckUserAllLoans
import com.example.loanconnect.presentation.states.AdminStates
import com.example.loanconnect.ui.theme.components.CustomTopAppBar
import com.example.loanconnect.ui.theme.spacing


@Composable
fun AdminScreenCheckUserLoans(
    navController: NavHostController,
    adminState: AdminStates,
    userIndex: Int
) {
    Scaffold(
        topBar = {
            // CustomTopAppBar
            CustomTopAppBar(onClick = { navController.navigateUp() }, title = {}, actions = {})
        },
    ) { innerPadding ->
        if (adminState.usersData[userIndex].loans.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(0.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    AuthScreensHeading(
                        "User Loans",
                        subHeading = "Access funds quickly and easily "
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))

                }

                items(adminState.usersData[userIndex].loans.size) { loan ->
                    AdminCheckUserAllLoans(
                        loanIcon = painterResource(R.drawable.give_loans_icon),
                        loanId = adminState.usersData[userIndex].loans[loan].loanId.toString(),
                        loanAmount = adminState.usersData[userIndex].loans[loan].loanAmount.toString(),
                        loanDuration = adminState.usersData[userIndex].loans[loan].duration,
                        loanStatus = adminState.usersData[userIndex].loans[loan].loanStatus
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
