package com.alura.sorria.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Badge
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.alura.sorria.data.local.PostLocalDataSource
import com.alura.sorria.sampleData.profilesPics

@Composable

fun StoryContainer() {
    Column {
        LazyRow(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp),
            content = {
                item {
                    ItemStory(
                        profilePic = profilesPics.last(),
                        profilePicDescription = "foto de perfil da conta atual",
                        name = "Seu story",
                        ringOpacity = 0f,
                        badge = {
                            Badge(
                                Modifier
                                    .size(22.dp)
                                    .padding(bottom = 2.dp, end = 2.dp)
                                    .border(
                                        2.dp,
                                        MaterialTheme.colorScheme.background,
                                        CircleShape
                                    ),
                                containerColor = MaterialTheme.colorScheme.primary,
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "add",
                                    modifier = Modifier
                                        .size(20.dp),
                                )
                            }
                        }
                    )
                }

                items(PostLocalDataSource().users) { user ->
                    Spacer(modifier = Modifier.width(8.dp))
                    val ringOpacity =
                        if (PostLocalDataSource().users.indexOf(user) % 2 == 1) 0.2f else 1f
                    ItemStory(
                        profilePic = user.avatar,
                        profilePicDescription = "foto de perfil de ${user.name}",
                        name = user.name,
                        ringOpacity = ringOpacity
                    )
                }
            }
        )
        HorizontalDivider(
            color = MaterialTheme.colorScheme.onSurface.copy(0.2f),
            modifier = Modifier
                .padding(top = 16.dp, bottom = 4.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
private fun ItemStory(
    profilePic: Int,
    profilePicDescription: String,
    name: String,
    ringOpacity: Float,
    badge: @Composable () -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            contentAlignment = Alignment.BottomEnd,
        ) {
            Box(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.primary.copy(ringOpacity), CircleShape
                    )
                    .padding(2.dp)
                    .clip(CircleShape)
            ) {
                AsyncImageWithShimmer(
                    data = profilePic,
                    contentDescription = profilePicDescription,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(62.dp)
                        .border(
                            3.dp, MaterialTheme.colorScheme.background, CircleShape
                        )
                )
            }
            badge()
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = name,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}