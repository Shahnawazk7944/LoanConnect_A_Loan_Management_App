package com.example.loanconnect.presentation.features.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestMultiplePermissions(
//    onContactsPermissionGranted: @Composable () -> Unit,
//    onCallLogPermissionGranted: @Composable () -> Unit,
//    onSmsPermissionGranted: @Composable () -> Unit,
    permissionsState: MultiplePermissionsState,

) {

    val showPermissionRequest =
        permissionsState.permissions.any { !it.status.isGranted && !it.status.shouldShowRationale }

    if (showPermissionRequest) {
        SideEffect {
            permissionsState.launchMultiplePermissionRequest()
        }
    }
//    if (permissionsState.permissions.first { it.permission == android.Manifest.permission.READ_CONTACTS }.status.isGranted) {
//        onContactsPermissionGranted()
//    }
//
//    if (permissionsState.permissions.first { it.permission == android.Manifest.permission.READ_CALL_LOG }.status.isGranted) {
//        onCallLogPermissionGranted()
//    }
//
//    if (permissionsState.permissions.first { it.permission == android.Manifest.permission.READ_SMS }.status.isGranted) {
//        onSmsPermissionGranted()
//    }

}