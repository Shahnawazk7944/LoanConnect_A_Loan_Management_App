import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.loanconnect.R
import com.example.loanconnect.presentation.features.auth.components.AuthScreensHeading
import com.example.loanconnect.presentation.features.auth.components.CustomTopAppBar
import com.example.loanconnect.presentation.features.utils.ValidationUtils
import com.example.loanconnect.presentation.navigation.MyNavGraphRoutes
import com.example.loanconnect.presentation.viewModels.AuthViewModel
import com.example.loanconnect.ui.theme.LoanConnectTheme
import com.example.loanconnect.ui.theme.components.OutlinedInputField
import com.example.loanconnect.ui.theme.components.PrimaryButton
import com.example.loanconnect.ui.theme.spacing
import kotlinx.coroutines.launch


@Composable
fun SignUpScreen(navController: NavHostController, viewModel: AuthViewModel) {
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var userNameError: String? by remember { mutableStateOf(null) }
    var emailError: String? by remember { mutableStateOf(null) }
    var passwordError: String? by remember { mutableStateOf(null) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()


    Scaffold(
        topBar = {
            // CustomTopAppBar
            CustomTopAppBar(onBackClick = { /* Handle back click */ }, actions = {})

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
            // Welcome Text OR Headings
            AuthScreensHeading("Welcome to LoanConnect ", subHeading = "Online Loan Platform")


            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge * 2))
            OutlinedInputField(
                value = userName,
                onChange = {
                    userName = it
                    userNameError =
                        if (ValidationUtils.isValidUserName(it)) null else "ⓘ Full Name least 3 characters"
                },
                label = "Full Name",
                placeholder = {
                    Text(
                        text = "Enter Your Full Name",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.user_icon),
                        contentDescription = "user icon",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(18.dp)
                    )
                },
                error = if (userNameError != null) userNameError else null
            )



            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            OutlinedInputField(
                value = email,
                onChange = {
                    email = it
                    emailError = if (ValidationUtils.isValidEmail(it)) null else "ⓘ Invalid Email"
                },
                label = "Email",
                placeholder = {
                    Text(
                        text = "Enter Your Email",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.email_icon),
                        contentDescription = "email icon",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(18.dp)
                    )
                },
                error = if (emailError != null) emailError else null

            )


            // Password Text Field
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
            OutlinedInputField(
                value = password,
                onChange = {
                    password = it
                    passwordError =
                        if (ValidationUtils.isValidPassword(it)) null else "ⓘ Password least 6 characters"
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


            // Sign Up Button
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge * 2))
            PrimaryButton(
                label = "Sign Up",
                onClick = {
                    if (userNameError == null && emailError == null && passwordError == null && userName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()
                    ) {
                        navController.navigate(MyNavGraphRoutes.SignUpScreen.route)
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
                    append("Already have account? ")
                }
                withStyle(
                    style = SpanStyle(
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary,
                        fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                        fontWeight = MaterialTheme.typography.titleMedium.fontWeight
                    )
                ) {
                    append("Sign In")
                }
            }
            Text(
                annotatedText,
                Modifier.clickable {
                    navController.navigate(MyNavGraphRoutes.SignInScreen.route) {
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

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    LoanConnectTheme {

    }
}


