package com.example.loanconnect.presentation.features.admin


import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.loanconnect.R
import com.example.loanconnect.presentation.features.auth.components.AuthScreensHeading
import com.example.loanconnect.presentation.features.components.AdminCheckAllUsers
import com.example.loanconnect.presentation.navigation.MyNavGraphRoutes
import com.example.loanconnect.presentation.states.AdminStates
import com.example.loanconnect.ui.theme.components.CustomTopAppBar
import com.example.loanconnect.ui.theme.components.LoadingDialog
import com.example.loanconnect.ui.theme.spacing


@Composable
fun AdminScreenCheckAllUsers(
    navController: NavHostController,
    adminState: AdminStates,
) {
    var showDialog by remember { mutableStateOf(false) }
    var profileImageToShow by remember { mutableStateOf<Any?>(null) }
    Scaffold(
        topBar = {
            // CustomTopAppBar
            CustomTopAppBar(onClick = { navController.navigateUp() }, title = {}, actions = {})
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(0.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                AuthScreensHeading(
                    "All Users",
                    subHeading = "Access funds quickly and easily "
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))

            }

            items(adminState.usersData.size) { user ->
                AdminCheckAllUsers(
                    profilePhoto = painterResource(R.drawable.no_profile),
                    userName = adminState.usersData[user].username,
                    contactNumber = adminState.usersData[user].mobile,
                    onProfileClick = {
                        profileImageToShow = R.drawable.no_profile
                        showDialog = true
                    },
                    checkAppliedLoans = {
                        navController.navigate(
                            MyNavGraphRoutes.AdminScreenCheckUserLoans.passUserIndex(
                                user
                            )
                        )
                    }
                )
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Profile Photo") },
                text = {
                    when (profileImageToShow) {
                        is String -> {
                            AsyncImage(
                                model = profileImageToShow as String,
                                contentDescription = "Profile Photo",
                                modifier = Modifier.size(200.dp)
                            )
                        }
                        is Int -> {
                            Box(
                                modifier = Modifier
                                    .size(200.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.onPrimary),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(profileImageToShow as Int),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(200.dp)
                                        .padding(10.dp),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                        is Bitmap -> {
                            Image(
                                bitmap = (profileImageToShow as Bitmap).asImageBitmap(),
                                contentDescription = "Profile Photo",
                                modifier = Modifier.size(200.dp)
                            )
                        }

                        else -> {
                            Text("No profile photo available")
                        }
                    }
                },
                confirmButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("Close")
                    }
                }
            )
        }
    }
}
