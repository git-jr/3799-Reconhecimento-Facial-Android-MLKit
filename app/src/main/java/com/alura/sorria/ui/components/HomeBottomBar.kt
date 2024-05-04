package com.alura.sorria.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person3
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.VideoLibrary
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.alura.sorria.sampleData.profilesPics


@Composable
fun HomeBottomBar(
    onItemSelected: (String) -> Unit,
    externalIndex: Int
) {
    NavigationBar {
        screenItems.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    if (index != screenItems.size - 1) {
                        Icon(
                            imageVector = if (externalIndex == index) item.second.first else item.second.second,
                            contentDescription = item.first,
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        AsyncImageWithShimmer(
                            data = profilesPics.last(),
                            contentDescription = "foto de perfil da conta atual",
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(32.dp)
                                .border(
                                    1.dp,
                                    MaterialTheme.colorScheme.onSurface.copy(0.2f),
                                    CircleShape
                                )
                        )
                    }
                },
                label = { Text(item.first) },
                selected = externalIndex == index,
                onClick = {
                    onItemSelected(item.first)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                    selectedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                )
            )
        }
    }
}

val screenItems = listOf(
    Pair("Início", Pair(Icons.Filled.Home, Icons.Outlined.Home)),
    Pair("Buscar", Pair(Icons.Filled.Search, Icons.Outlined.Search)),
    Pair("Vídeos", Pair(Icons.Filled.VideoLibrary, Icons.Outlined.VideoLibrary)),
    Pair("Perfil", Pair(Icons.Filled.Email, Icons.Outlined.Person3)),
)