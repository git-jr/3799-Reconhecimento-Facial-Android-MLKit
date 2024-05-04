package com.alura.sorria.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.alura.sorria.data.local.PostLocalDataSource
import com.alura.sorria.data.model.User
import com.alura.sorria.sampleData.profilesPics
import com.alura.sorria.ui.components.AsyncImageWithShimmer

@Composable
fun ProfileScreen(user: User = PostLocalDataSource().users[4]) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Spacer(modifier = Modifier.size(72.dp))

        AsyncImageWithShimmer(
            data = profilesPics.last(),
            contentDescription = "Foto de perfil",
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape),
        )

        Spacer(modifier = Modifier.size(16.dp))

        Text(
            text = user.name,
            fontSize = MaterialTheme.typography.displaySmall.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface.copy(0.6f),
        )

        Text(
            text = "@${user.userName}",
            fontWeight = MaterialTheme.typography.displayLarge.fontWeight,
            color = MaterialTheme.colorScheme.onSurface.copy(0.6f),
        )

        Row {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = user.followers.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Text(
                    text = "seguidores",
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }

            Spacer(modifier = Modifier.size(16.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = user.following.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Text(
                    text = "seguindo",
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}