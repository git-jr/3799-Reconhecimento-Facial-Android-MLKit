package com.alura.sorria.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.alura.sorria.ui.components.AsyncImageWithShimmer

@Composable
fun ItemSingleImage(imageUrl: Int, description: String = "") {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImageWithShimmer(
            imageUrl,
            description,
            modifier = Modifier
                .aspectRatio(3 / 3.8f)
        )
    }
}

