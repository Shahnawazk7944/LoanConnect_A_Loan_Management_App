package com.example.loanconnect.presentation.features.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loanconnect.R
import com.example.loanconnect.ui.theme.LoanConnectTheme


@Composable
fun AdminCheckAllUsers(
    profilePhoto: Painter,
    userName: String,
    contactNumber: String,
    onProfileClick: () -> Unit,
    checkAppliedLoans: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .padding(horizontal = 0.dp)
            .height(80.dp)
            .fillMaxWidth(),
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
                    painter = profilePhoto,
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(3.dp)
                        .clickable { onProfileClick() },
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    text = "Username :",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = userName,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onTertiary,
                    maxLines = 2,
                    modifier = Modifier.width(70.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    text = "Number :",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = contactNumber,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onTertiary,
                    maxLines = 2,
                    modifier = Modifier.width(70.dp)
                )
            }
            Spacer(modifier = Modifier.width(45.dp))
            Button(
                modifier = Modifier.scale(1.2f).padding(2.dp),
                onClick = { checkAppliedLoans() },
                shape = RoundedCornerShape(10.dp)) {
                Text(
                    text = "Applied Loans",
                    fontSize = 5.sp,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onTertiary,
                    modifier = Modifier.scale(1.8f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AdminCheckAllUsersComponentsPreview() {
    LoanConnectTheme {
        AdminCheckAllUsers(
            profilePhoto = painterResource(R.drawable.all_users_icon),
            userName = "Users Data",
            contactNumber = "1859565456",
            onProfileClick = {},
            checkAppliedLoans = {}
        )
    }
}