package com.example.loanconnect.presentation.features.profile


import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.loanconnect.R
import com.example.loanconnect.presentation.features.components.UpdateMobileNumberDialog
import com.example.loanconnect.presentation.features.components.UpdateUsernameDialog
import com.example.loanconnect.presentation.features.utils.bitmapToBase64
import com.example.loanconnect.presentation.features.utils.getImage
import com.example.loanconnect.presentation.states.AppEvents
import com.example.loanconnect.presentation.states.AppStates
import com.example.loanconnect.presentation.states.AuthStates
import com.example.loanconnect.presentation.viewModels.AppViewModel
import com.example.loanconnect.ui.theme.components.CustomTopAppBar
import com.example.loanconnect.ui.theme.components.LoadingDialog
import com.example.loanconnect.ui.theme.spacing

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun StoreProfileScreen(
    navController: NavHostController,
    appState: AppStates,
    authState: AuthStates,
    appViewModel: AppViewModel,
) {
    var showUpdateUsernameDialog by remember { mutableStateOf(false) }
    var showUpdateMobileNumberDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { it ->
            if (it != null) {
                appViewModel.onEvent(AppEvents.UpdateImageUri(it.toString()))
            } else {
                appViewModel.onEvent(AppEvents.UpdateImageUri(it))
            }
        }

    appViewModel.onEvent(AppEvents.SaveBitmap(getImage(uri = appState.imageUri.toString())))

    Scaffold(
        topBar = {
            CustomTopAppBar(onClick = {
                navController.navigateUp()
            }, actions = {})
        },

        ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            LoadingDialog(isLoading = appState.uploadingPhoto)

            Spacer(modifier = Modifier.height(10.dp))
            if (appState.imageUri != null) {
                appState.bitmap?.let {
                    Image(
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape)
                            .clickable {
                                launcher.launch("image/*")
                            },
                        contentDescription = "null",
                        contentScale = ContentScale.Crop,
                        bitmap = it.asImageBitmap()
                    )
                }
            } else {
                Icon(
                    painter = painterResource(R.drawable.no_profile),
                    contentDescription = "",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.LightGray, CircleShape)

                        .clickable {
                            launcher.launch("image/*")
//
                        }
                )

            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

            Text(
                text = if (authState.username != null) authState.username else "No Username",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary,
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "shahnawazkhan238200@gmail.com",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.secondary,
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))


            Button(
                onClick = {
                    if (appState.imageUri != null) {
                        appState.bitmap?.let { it ->
                            val base64String = bitmapToBase64(bitmap = it)
                            appViewModel.onEvent(
                                AppEvents.UploadPhoto(
                                    base64String = base64String,
                                    mobileNumberAsId = authState.contactNumber
                                )
                            )
                        }
                    } else {
                        Toast.makeText(context, "Please Add Profile Image", Toast.LENGTH_SHORT)
                            .show()
                    }
                },
                modifier = Modifier
                    .height(40.dp)
                    .width(200.dp)
                    .padding(0.dp),
                shape = RoundedCornerShape(30.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 2.dp,
                    pressedElevation = 0.dp
                ),
                border = BorderStroke(1.dp, color = Color.LightGray),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                )
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Upload Profile Image",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }
            LaunchedEffect(appState.photoUploadedSuccessMessage) {
                if (appState.photoUploadedSuccessMessage != null) {
                    Toast.makeText(
                        context,
                        appState.photoUploadedSuccessMessage,
                        Toast.LENGTH_SHORT
                    ).show()

                } else if (appState.showError) {
                    Toast.makeText(context, appState.error, Toast.LENGTH_SHORT).show()
                    appViewModel.onEvent(AppEvents.ErrorShown(false, null))
                }
            }


            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            ProfileDetailsRowComp(
                onClick = {
                    showUpdateUsernameDialog = true
                },
                rowTitle = "Update Username",
                leadingIcon = R.drawable.user_icon
            )
            LaunchedEffect(appState.updatedNewUsernameSuccessMessage) {
                if (appState.appliedForLoanSuccessMessage != null) {
                    showUpdateUsernameDialog = false
                    Toast.makeText(
                        context,
                        appState.updatedNewUsernameSuccessMessage,
                        Toast.LENGTH_SHORT
                    ).show()

                } else if (appState.showError) {
                    Toast.makeText(context, appState.error, Toast.LENGTH_SHORT).show()
                    appViewModel.onEvent(AppEvents.ErrorShown(false, null))
                }
            }

            ProfileDetailsRowComp(
                onClick = {
                    showUpdateMobileNumberDialog = true
                },
                rowTitle = "Update Mobile Number",
                leadingIcon = R.drawable.phone_icon
            )
            LaunchedEffect(appState.updatedNewMobileNumberSuccessMessage) {
                if (appState.updatedNewMobileNumberSuccessMessage != null) {
                    showUpdateMobileNumberDialog = false
                    Toast.makeText(
                        context,
                        appState.updatedNewMobileNumberSuccessMessage,
                        Toast.LENGTH_SHORT
                    ).show()

                } else if (appState.showError) {
                    Toast.makeText(context, appState.error, Toast.LENGTH_SHORT).show()
                    appViewModel.onEvent(AppEvents.ErrorShown(false, null))
                }
            }



            ProfileDetailsRowComp(
                onClick = {},
                rowTitle = "Upload User Data",
                leadingIcon = R.drawable.upload

            )



            if (showUpdateUsernameDialog) {
                UpdateUsernameDialog(
                    onUpdate = { newUsername ->
                        appViewModel.onEvent(
                            AppEvents.UpdateNewUsername(
                                newData = newUsername,
                                mobileNumberAsId = authState.contactNumber
                            )
                        )

                    },
                    onCancel = { canceled ->
                        showUpdateUsernameDialog = canceled
                    },
                    states = appState
                )
            }



            if (showUpdateMobileNumberDialog) {
                UpdateMobileNumberDialog(
                    onUpdate = { newMobileNumber ->
                        appViewModel.onEvent(
                            AppEvents.UpdateNewMobileNumber(
                                newData = newMobileNumber,
                                mobileNumberAsId = authState.contactNumber
                            )
                        )
                    },
                    onCancel = { canceled ->
                        showUpdateMobileNumberDialog = canceled
                    },
                    states = appState
                )
            }

        }
    }
}
