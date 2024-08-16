package com.example.loanconnect.presentation.features.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.loanconnect.presentation.features.utils.ValidationUtils
import com.example.loanconnect.presentation.states.AppStates
import com.example.loanconnect.ui.theme.components.OutlinedInputField
import com.example.loanconnect.ui.theme.spacing

@Composable
fun ApplyLoanDialog(
    onApply: (amount: String, duration: String) -> Unit,
    onCancel: (Boolean) -> Unit,
    states: AppStates,
) {
    var amount : String by remember { mutableStateOf("") }
    var duration : String by remember { mutableStateOf("") }
    var amountError: String? by remember { mutableStateOf(null) }
    var durationError: String? by remember { mutableStateOf(null) }

    AlertDialog(
        onDismissRequest = { onCancel(false) },
        title = { Text("Apply For Loan") },
        text = {
            Column {
                OutlinedInputField(
                    value = amount,
                    onChange = {
                        amount = it
                        amountError =
                            if (ValidationUtils.isValidLoanAmount(it)) null else "ⓘ Amount least 10000 Rupees"
                    },
                    label = "Amount",
                    placeholder = {
                        Text(
                            text = "Enter Your Amount",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.AttachMoney,
                            contentDescription = "Amount Icon",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(18.dp)
                        )
                    },
                    error = if (amountError != null) amountError else null,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                )


                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

                OutlinedInputField(
                    value = duration,
                    onChange = {
                        duration = it
                        durationError =
                            if (ValidationUtils.isValidLoanDuration(it)) null else "ⓘ Duration least 12, Max 120 Months"
                    },
                    label = "Duration",
                    placeholder = {
                        Text(
                            text = "Enter Your Duration",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.AccessTime,
                            contentDescription = "Duration Icon",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(18.dp)
                        )
                    },
                    error = if (durationError != null) durationError else null,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                )
            }
        },
        confirmButton = {
            if (states.applyingForLoan) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            } else {
                Button(
                    onClick = {
                        if (amountError == null && durationError == null && amount.isNotEmpty() && duration.isNotEmpty()) {
                            onApply(amount, duration)
                        }
                    },
                    enabled = amountError == null && durationError == null && amount.isNotEmpty() && duration.isNotEmpty()
                ) {
                    Text("Apply")
                }
            }
        },

        dismissButton = {
            if (!states.applyingForLoan) {
                Button(onClick = {
                    onCancel(false)
                }) {
                    Text("Cancel")
                }
            }
        }
    )

}