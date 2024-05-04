package com.alura.sorria.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemCarouselView(medias: List<Int>) {
    val pageCount = medias.size
    val pagerState = rememberPagerState(pageCount = {
        medias.size
    })

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
    ) { page ->
        Box {
            ItemSingleImage(medias[page])

            Row(
                modifier = Modifier
                    .offset(x = (-16).dp, y = 8.dp)
                    .background(
                        MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                        CircleShape
                    )
                    .padding(8.dp, 4.dp)
                    .align(Alignment.TopEnd),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${page + 1} / $pageCount",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.background
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CustomViewPreview() {
    Column(Modifier.fillMaxSize()) {
        ItemCarouselView(emptyList())
    }
}