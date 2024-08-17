package com.example.loanconnect.presentation.features.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.loanconnect.R
import com.example.loanconnect.presentation.features.utils.ValidationUtils
import com.example.loanconnect.presentation.states.AppStates
import com.example.loanconnect.ui.theme.components.OutlinedInputField

@Composable
fun UpdateMobileNumberDialog(
    onUpdate: (newMobileNumber: String) -> Unit,
    onCancel: (Boolean) -> Unit,
    states: AppStates,
) {
    var newMobileNumber: String by remember { mutableStateOf("") }
    var newMobileNumberError: String? by remember { mutableStateOf(null) }

    AlertDialog(
        onDismissRequest = { },
        title = { Text("Update Mobile Number") },
        text = {
            OutlinedInputField(
                value = newMobileNumber,
                onChange = {
                    if (it.length <= 10) {
                        newMobileNumber = it
                        newMobileNumberError =
                            if (ValidationUtils.isValidContactNumber(it)) null else "ⓘ Invalid Contact Number"
                    }
                },
                label = "New Mobile Number",
                placeholder = {
                    Text(
                        text = "Enter Your Mobile Number",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.phone_icon),
                        contentDescription = "Phone Icon",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(18.dp)
                    )

                },
                error = if (newMobileNumberError != null) newMobileNumberError else null,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )


        },
        confirmButton = {
            if (states.updatingNewMobileNumber) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            } else {
                Button(
                    onClick = {
                        if (newMobileNumberError == null && newMobileNumber.isNotEmpty()) {
                            onUpdate(newMobileNumber)
                        }
                    },
                    enabled = newMobileNumberError == null && newMobileNumber.isNotEmpty()
                ) {
                    Text("Update")
                }
            }
        },

        dismissButton = {
            if (!states.updatingNewMobileNumber) {
                Button(onClick = {
                    onCancel(false)
                }) {
                    Text("Cancel")
                }
            }
        }
    )
}