import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.loanconnect.presentation.features.auth.components.AuthScreensHeading
import com.example.loanconnect.presentation.features.components.ApplyLoanDialog
import com.example.loanconnect.presentation.states.AppStates
import com.example.loanconnect.presentation.states.AuthEvents
import com.example.loanconnect.presentation.viewModels.AuthViewModel
import com.example.loanconnect.ui.theme.LoanConnectTheme
import com.example.loanconnect.ui.theme.components.CustomTopAppBar
import com.example.loanconnect.ui.theme.components.LoadingDialog
import com.example.loanconnect.ui.theme.components.PrimaryButton
import com.example.loanconnect.ui.theme.spacing


@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    appState: AppStates
) {
    var showApplyLoanDialog by remember { mutableStateOf(false) }


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
            //loading dialog when signing in
            LoadingDialog(isLoading = false)

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
            if (showApplyLoanDialog) {
                ApplyLoanDialog(
                    onApply = { amount, duration ->

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