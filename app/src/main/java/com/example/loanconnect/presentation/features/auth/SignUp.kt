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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.loanconnect.R
import com.example.loanconnect.presentation.features.auth.components.AuthScreensHeading
import com.example.loanconnect.presentation.features.auth.components.CustomTopAppBar
import com.example.loanconnect.ui.theme.LoanConnectTheme
import com.example.loanconnect.ui.theme.components.OutlinedInputField
import com.example.loanconnect.ui.theme.components.PrimaryButton
import com.example.loanconnect.ui.theme.spacing


@Composable
fun SignUpScreen() {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var fullNameError: String? by remember { mutableStateOf(null) }
    var emailError: String? by remember { mutableStateOf(null) }
    var passwordError: String? by remember { mutableStateOf(null) }

    // Function to validate email format
    fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}")
        return emailRegex.matches(email)
    }

    // Function to validate password length (currently only checks for minimum length)
    fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }

    // Function to validate full name length
    fun isValidFullName(fullName: String): Boolean {
        return fullName.length >= 3
    }


    Scaffold(
        topBar = {
            // CustomTopAppBar
            CustomTopAppBar(onBackClick = { /* Handle back click */ }, actions = {})

        }) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Welcome Text OR Headings
            AuthScreensHeading("Welcome to CrakCode Online Learning Platform")


            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            OutlinedInputField(
                value = fullName,
                onChange = {
                    fullName = it
                    fullNameError =
                        if (isValidFullName(it)) null else "ⓘ Full Name least 3 characters"
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
                error = if (fullNameError != null) fullNameError else null
            )



            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            OutlinedInputField(
                value = email,
                onChange = {
                    email = it
                    emailError = if (isValidEmail(it)) null else "ⓘ Invalid Email"
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
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            OutlinedInputField(
                value = password,
                onChange = {
                    password = it
                    passwordError =
                        if (isValidPassword(it)) null else "ⓘ Password least 6 characters"
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
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                enabled = fullNameError == null && emailError == null && passwordError == null && fullName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty(),
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
                Modifier.clickable { /* Handle text click */ },
            )

        }
    }

}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    LoanConnectTheme{
        SignUpScreen()
    }
}


