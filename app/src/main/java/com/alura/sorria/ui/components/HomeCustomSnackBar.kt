package com.alura.sorria.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.alura.sorria.ui.screens.home.HomeUiState

@Composable
fun HomeCustomSnackBar(state: HomeUiState) {
    AnimatedVisibility(
        state.isListening,
        enter = slideInVertically { it },
        exit = slideOutVertically { it },
        content = {
            Row(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    "Ouvindo: ",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    state.listenedText,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    )
}