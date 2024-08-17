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
import com.example.loanconnect.presentation.features.utils.ValidationUtils
import com.example.loanconnect.presentation.states.AppStates
import com.example.loanconnect.ui.theme.components.OutlinedInputField
import com.example.loanconnect.R

@Composable
fun UpdateUsernameDialog(
    onUpdate: (newUsername: String) -> Unit,
    onCancel: (Boolean) -> Unit,
    states: AppStates,
) {
    var newUsername: String by remember { mutableStateOf("") }
    var newUsernameError: String? by remember { mutableStateOf(null) }

    AlertDialog(
        onDismissRequest = { },
        title = { Text("Update Username") },
        text = {
            OutlinedInputField(
                value = newUsername,
                onChange = {
                    newUsername = it
                    newUsernameError =
                        if (ValidationUtils.isValidUserName(it)) null else "ⓘ Username least 3 characters"
                },
                label = "New Username",
                placeholder = {
                    Text(
                        text = "Enter Your New Username",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.user_icon),
                        contentDescription = "User Icon",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(18.dp)
                    )

                },
                error = if (newUsernameError != null) newUsernameError else null,
            )


        },
        confirmButton = {
            if (states.updatingNewUsername) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            } else {
                Button(
                    onClick = {
                        if (newUsernameError == null  && newUsername.isNotEmpty()) {
                            onUpdate(newUsername)
                        }
                    },
                    enabled = newUsernameError == null  && newUsername.isNotEmpty()
                ) {
                    Text("Update")
                }
            }
        },

        dismissButton = {
            if (!states.updatingNewUsername) {
                Button(onClick = {
                    onCancel(false)
                }) {
                    Text("Cancel")
                }
            }
        }
    )

}
