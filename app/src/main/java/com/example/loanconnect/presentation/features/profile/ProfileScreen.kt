package com.example.loanconnect.presentation.features.profile


import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Upload
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
import com.example.loanconnect.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.loanconnect.presentation.features.components.UpdateMobileNumberDialog
import com.example.loanconnect.presentation.features.components.UpdateUsernameDialog
import com.example.loanconnect.presentation.states.AppEvents
import com.example.loanconnect.presentation.states.AppStates
import com.example.loanconnect.presentation.states.AuthStates
import com.example.loanconnect.presentation.viewModels.AppViewModel
import com.example.loanconnect.presentation.viewModels.AuthViewModel
import com.example.loanconnect.ui.theme.components.CustomTopAppBar
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

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    var bitmap by remember {
        mutableStateOf<Bitmap?>(null)
    }
    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { it ->
            imageUri = it
        }
    bitmap = getImage(uri = imageUri.toString())

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
            Spacer(modifier = Modifier.height(10.dp))
            if (imageUri != null) {
                bitmap?.let {
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
                text = "Shahnawaz Khan",
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
                onClick = { },
                modifier = Modifier
                    .height(80.dp)
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
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

            ProfileDetailsRowComp(
                onClick = {
                    showUpdateUsernameDialog = true
                },
                rowTitle = "Update Username",
                leadingIcon = R.drawable.user_icon
            )
            LaunchedEffect(appState.updatingNewUsernameSuccessMessage) {
                if (appState.appliedForLoanSuccessMessage != null) {
                    showUpdateUsernameDialog = false
                    Toast.makeText(
                        context,
                        appState.updatingNewUsernameSuccessMessage,
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
            LaunchedEffect(appState.updatingNewMobileNumberSuccessMessage) {
                if (appState.updatingNewMobileNumberSuccessMessage != null) {
                    showUpdateMobileNumberDialog = false
                    Toast.makeText(
                        context,
                        appState.updatingNewMobileNumberSuccessMessage,
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

@Composable
fun getImage(
    uri: String
): Bitmap? {
    val imageState: AsyncImagePainter.State = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(uri)
            .size(Size.ORIGINAL)
            .build()
    ).state

    if (imageState is AsyncImagePainter.State.Success) {
        return imageState.result.drawable.toBitmap()
    }

    return null
}