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
import com.example.loanconnect.presentation.features.auth.components.AuthScreensHeading
import com.example.loanconnect.presentation.features.components.AdminCheckUsersOrLoans
import com.example.loanconnect.presentation.states.AdminEvents
import com.example.loanconnect.presentation.states.AdminStates
import com.example.loanconnect.presentation.viewModels.AdminViewModel
import com.example.loanconnect.ui.theme.components.CustomTopAppBar
import com.example.loanconnect.ui.theme.components.LoadingDialog
import com.example.loanconnect.ui.theme.spacing
import com.example.loanconnect.R
import com.example.loanconnect.presentation.features.components.AdminCheckAllUsersComponents


@Composable
fun AdminScreenCheckAllUsers(
    navController: NavHostController,
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
            // Welcome Text OR Headings
            AuthScreensHeading(
                "All Users",
                subHeading = "Access funds quickly and easily "
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            AdminCheckAllUsersComponents(
                profilePhoto = painterResource(R.drawable.all_users_icon),
                userName = "Userdsffjdskfsjffsdfj",
                contactNumber = "18595654564454454",
                onProfileClick = {},
                checkAppliedLoans = {}
            )


        }
    }
}
