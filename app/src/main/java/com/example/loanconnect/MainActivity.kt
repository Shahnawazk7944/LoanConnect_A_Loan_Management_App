package com.example.loanconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.loanconnect.presentation.navigation.MyNavGraph
import com.example.loanconnect.ui.theme.LoanConnectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoanConnectTheme {
                val navController = rememberNavController()
                MyNavGraph(navController = navController)
            }
        }
    }


}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LoanConnectTheme {

    }
}