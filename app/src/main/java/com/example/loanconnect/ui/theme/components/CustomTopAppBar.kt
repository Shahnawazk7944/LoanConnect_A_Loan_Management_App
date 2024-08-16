package com.example.loanconnect.ui.theme.components


import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.loanconnect.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String = "",
    onClick: () -> Unit,
    actions: @Composable () -> Unit,
    titleModifier: Modifier = Modifier,
    backIconModifier: Modifier = Modifier,
    backIcon: Painter = painterResource(R.drawable.arrow_back),
) {
    TopAppBar(
        title = {
            Text(
                title,
                style = MaterialTheme.typography.titleMedium,
                modifier = titleModifier,
            )
        },
        navigationIcon = {
            IconButton(onClick = { onClick() }) {
                Icon(
                    painter = backIcon,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = backIconModifier.size(24.dp)
                )
            }
        },
        actions = {
            actions()
        }
    )
}