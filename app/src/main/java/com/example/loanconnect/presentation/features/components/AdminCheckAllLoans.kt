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
import com.example.loanconnect.ui.theme.spacing


@Composable
fun AdminCheckAllLoans(
    profilePhoto: Painter,
    loanId: String,
    loanAmount: String,
    loanDuration: String,
    loanStatus: String,
    isCardExpanded: Boolean,
    userName: String,
    contactNumber: String,
    expandCard: () -> Unit,
    onProfileClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .padding(horizontal = 0.dp)
            .height(if (isCardExpanded) 160.dp else 80.dp)
            .fillMaxWidth()
            .clickable { expandCard() },
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
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    text = "Loan Id :",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = loanId,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onTertiary,
                    maxLines = 2,
                    modifier = Modifier.width(70.dp)
                )

                Spacer(modifier = Modifier.height(if(isCardExpanded) MaterialTheme.spacing.large else MaterialTheme.spacing.extraLarge))
                Text(
                    text = "Username:",
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
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    text = "Loan Amount :",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = loanAmount,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onTertiary,
                    maxLines = 2,
                    modifier = Modifier.width(70.dp)
                )


                Spacer(modifier = Modifier.height(if(isCardExpanded) MaterialTheme.spacing.large else MaterialTheme.spacing.extraLarge))
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
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    text = "Loan Duration :",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = loanDuration,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onTertiary,
                    maxLines = 2,
                    modifier = Modifier.width(70.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    text = "Loan Status :",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = loanStatus,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onTertiary,
                    maxLines = 2,
                    modifier = Modifier.width(70.dp)
                )
            }

        }
    }
}