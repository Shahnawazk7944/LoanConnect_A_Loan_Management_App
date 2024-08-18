package com.example.loanconnect.presentation.features.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun AdminCheckUsersOrLoans(painter: Painter, content: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .padding(horizontal = 0.dp)
            .height(80.dp)
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            // contentColor = Color(0xFF5f0f40)
        )
    ) {
        Row(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.onPrimary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter,
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(3.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = content,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier
            )
        }
    }
}