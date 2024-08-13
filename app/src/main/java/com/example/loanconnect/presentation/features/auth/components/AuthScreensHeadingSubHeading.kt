package com.example.loanconnect.presentation.features.auth.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun AuthScreensHeading(heading: String, subHeading: String? = null) {
    Text(
        text = heading,
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.secondary,
        lineHeight = 35.sp,
    )
    subHeading?.let {
        Text(
            text = it,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            lineHeight = 30.sp,
        )
    }
}