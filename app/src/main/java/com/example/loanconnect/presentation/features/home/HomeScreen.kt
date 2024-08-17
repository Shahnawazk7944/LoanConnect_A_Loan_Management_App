import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.loanconnect.presentation.features.auth.components.AuthScreensHeading
import com.example.loanconnect.presentation.features.components.ApplyLoanDialog
import com.example.loanconnect.presentation.features.home.RequestMultiplePermissions
import com.example.loanconnect.presentation.states.AppEvents
import com.example.loanconnect.presentation.states.AppStates
import com.example.loanconnect.presentation.states.AuthEvents
import com.example.loanconnect.presentation.states.AuthStates
import com.example.loanconnect.presentation.viewModels.AppViewModel
import com.example.loanconnect.presentation.viewModels.AuthViewModel
import com.example.loanconnect.ui.theme.LoanConnectTheme
import com.example.loanconnect.ui.theme.components.CustomTopAppBar
import com.example.loanconnect.ui.theme.components.PrimaryButton
import com.example.loanconnect.ui.theme.spacing
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    appViewModel: AppViewModel,
    authViewModel: AuthViewModel,
    appState: AppStates,
    authState: AuthStates
) {
    var showApplyLoanDialog by remember { mutableStateOf(false) }

    val permissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.READ_CONTACTS,
            android.Manifest.permission.READ_CALL_LOG,
            android.Manifest.permission.READ_SMS
        )
    )

    val context = LocalContext.current


    Scaffold(
        topBar = {
            // CustomTopAppBar
            CustomTopAppBar(onClick = {
                navController.navigateUp()
                authViewModel.onEvent(AuthEvents.Logout(!true))
            }, actions = {})
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

            RequestMultiplePermissions(permissionsState = permissionsState,)

            // Welcome Text OR Headings
            AuthScreensHeading(
                "Welcome to LoanConnect",
                subHeading = "Access funds quickly and easily "
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {

                PrimaryButton(
                    label = "Apply For Loan",
                    onClick = {
                        showApplyLoanDialog = true
                    },
                    modifier = Modifier.fillMaxWidth(),
                )


            }
            LaunchedEffect(appState) {
                if (appState.appliedForLoanSuccessMessage != null) {
                    showApplyLoanDialog = false
                    Toast.makeText(
                        context,
                        appState.appliedForLoanSuccessMessage,
                        Toast.LENGTH_SHORT
                    ).show()

                } else if (appState.showError) {
                    Toast.makeText(context, appState.error, Toast.LENGTH_SHORT).show()
                    appViewModel.onEvent(AppEvents.ErrorShown(false, null))
                }
            }

            if (showApplyLoanDialog) {
                ApplyLoanDialog(
                    onApply = { amount, duration ->
                        appViewModel.onEvent(
                            AppEvents.ApplyForLoan(
                                contactNumber = authState.contactNumber,
                                amount = amount.toInt(),
                                duration = duration.toInt()
                            )
                        )
                    },
                    onCancel = { canceled ->
                        showApplyLoanDialog = canceled
                    },
                    states = appState
                )
            }


        }

    }

}


@Preview
@Composable
fun HomeScreenPreview() {
    LoanConnectTheme {

    }
}