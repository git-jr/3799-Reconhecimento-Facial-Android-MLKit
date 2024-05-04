package com.alura.sorria.ui.screens.video

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import com.alura.sorria.R
import com.alura.sorria.data.local.PostLocalDataSource
import com.alura.sorria.ui.components.AsyncImageWithShimmer
import com.alura.sorria.ui.components.VideoHud

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun VideosScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        VideoPlayer()
        Column(Modifier.fillMaxSize()) {
            Box(Modifier.fillMaxSize()) {
                TopAppBar(
                    title = {
                        Text(
                            text = "Vídeos",
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                            color = Color.White
                        )
                    },
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Outlined.Search,
                                contentDescription = "Buscar",
                                tint = Color.White
                            )
                        }


                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Outlined.CameraAlt,
                                contentDescription = "Camêra",
                                tint = Color.White
                            )
                        }

                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Outlined.MoreVert,
                                contentDescription = "Mais opções",
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    ),
                )

                VideoHud(PostLocalDataSource().basePosts[0])
            }

        }
    }
}


@Composable
private fun VideoPlayer() {
    Box(Modifier.fillMaxSize()) {
        AsyncImageWithShimmer(
            data = R.drawable.video_placeholder,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            contentDescription = "video"
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
        )
    }
}
