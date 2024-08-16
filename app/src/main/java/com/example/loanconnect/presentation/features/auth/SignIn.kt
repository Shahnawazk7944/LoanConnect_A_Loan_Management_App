import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.loanconnect.R
import com.example.loanconnect.presentation.features.auth.components.AuthScreensHeading
import com.example.loanconnect.presentation.features.utils.ValidationUtils
import com.example.loanconnect.presentation.navigation.MyNavGraphRoutes
import com.example.loanconnect.presentation.states.AppEvents
import com.example.loanconnect.presentation.states.AuthEvents
import com.example.loanconnect.presentation.states.AuthStates
import com.example.loanconnect.presentation.viewModels.AuthViewModel
import com.example.loanconnect.ui.theme.LoanConnectTheme
import com.example.loanconnect.ui.theme.components.CustomTopAppBar
import com.example.loanconnect.ui.theme.components.LoadingDialog
import com.example.loanconnect.ui.theme.components.OutlinedInputField
import com.example.loanconnect.ui.theme.components.PrimaryButton
import com.example.loanconnect.ui.theme.spacing
import kotlinx.coroutines.launch


@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    navController: NavHostController,
    viewModel: AuthViewModel,
    state: AuthStates
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var usernameError: String? by remember { mutableStateOf(null) }
    var passwordError: String? by remember { mutableStateOf(null) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current


    Scaffold(
        topBar = {
            // CustomTopAppBar
            CustomTopAppBar(
                onClick = {
                    navController.navigateUp()
                },
                actions = {})
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
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
            LoadingDialog(isLoading = state.loading)

            // Welcome Text OR Headings
            AuthScreensHeading(
                "Welcome back! to LoanConnect",
                subHeading = "Access funds quickly and easily "
            )


            // User Details Text Fields -- Email, Password
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge * 2))
            OutlinedInputField(
                value = username,
                onChange = {
                    username = it
                    usernameError =
                        if (ValidationUtils.isValidUserName(it)) null else "ⓘ Username least 3 characters"
                },
                label = "Username",
                placeholder = {
                    Text(
                        text = "Enter Your Username",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.user_icon),
                        contentDescription = "User icon",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(18.dp)
                    )
                },
                error = if (usernameError != null) usernameError else null

            )


            // Password Text Field
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
            OutlinedInputField(
                value = password,
                onChange = {
                    password = it
                    passwordError =
                        if (ValidationUtils.isValidPassword(it)) null else "ⓘ Password least 12 characters"
                },
                label = "Password",
                placeholder = {
                    Text(
                        text = "Enter Your Password",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.password_lock_icon),
                        contentDescription = "password icon",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(18.dp)
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(
                            painter = painterResource(R.drawable.eye_icon_password),
                            contentDescription = "password eye icon",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                },
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                error = if (passwordError != null) passwordError else null
            )


            // ... Row layout ... for Terms and Conditions Checkbox and Forgot Password Text
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.End
            ) {
                // Forgot Password Text
                Text(
                    text = "Forgot Password",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable { /* Handle text click */ }
                )
            }


            // Sign In Button
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge * 2))
            PrimaryButton(
                label = "Sign In",
                onClick = {
                    if ((usernameError == null && passwordError == null && username.isNotEmpty() && password.isNotEmpty())
                    ) {
                        viewModel.onEvent(AuthEvents.SignIn(username, password))
                    } else {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Please Fill All Fields",
                                actionLabel = "Dismiss",
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
            )
            LaunchedEffect(state) {
                if (state.isLoggedIn) {
                    Toast.makeText(context, "SignIn successful!", Toast.LENGTH_SHORT).show()
                    navController.navigate(MyNavGraphRoutes.HomeScreen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = false
                        }
                    }
                } else if (state.showError) {
                    Log.d("AuthViewModel", "Error block in SignInScreen")
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = state.error.toString(),
                            actionLabel = "Dismiss",
                            duration = SnackbarDuration.Short
                        )
                    }
                    viewModel.onEvent(AuthEvents.ErrorShown(false, null))
                }
            }

            // "Don’t have account?" text with "Sign In" link
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            val annotatedText = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                        fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                    )
                ) {
                    append("Don’t have account? ")
                }
                withStyle(
                    style = SpanStyle(
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary,
                        fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                        fontWeight = MaterialTheme.typography.titleMedium.fontWeight
                    )
                ) {
                    append("Sign Up")
                }
            }
            Text(
                annotatedText,
                Modifier.clickable {
                    navController.navigate(MyNavGraphRoutes.SignUpScreen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                },
            )

        }

    }

}


@Preview
@Composable
fun SignInScreenPreview() {
    LoanConnectTheme {
    }
}