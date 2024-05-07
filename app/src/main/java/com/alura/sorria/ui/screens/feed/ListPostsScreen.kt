package com.alura.sorria.ui.screens.feed

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Reply
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.ModeComment
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alura.sorria.R
import com.alura.sorria.data.model.BasePost
import com.alura.sorria.domain.model.Post
import com.alura.sorria.extensions.noRippleClickable
import com.alura.sorria.ui.components.AsyncImageWithShimmer
import com.alura.sorria.ui.components.ItemCarouselView
import com.alura.sorria.ui.components.ItemSingleImage
import com.alura.sorria.ui.components.StoryContainer
import kotlinx.coroutines.delay


@Composable
fun ListPosts(
    posts: List<Post>,
    pulseBigHeart: Boolean = false,
    onPulseBigHeart: (Boolean) -> Unit = {},
    slideToTopState: Boolean = false,
    slideToBottomState: Boolean = false
) {
    val state = rememberLazyListState()
    val index by remember { derivedStateOf { state.firstVisibleItemIndex } }

    LaunchedEffect(slideToTopState) {
        if (slideToTopState && index > 0) {
            state.animateScrollToItem(index - 1)
        }
    }

    LaunchedEffect(slideToBottomState) {
        if (slideToBottomState && index < posts.size) {
            state.animateScrollToItem(index + 1)
        }
    }

    LazyColumn(
        state = state,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize(),
    ) {
        item {
            StoryContainer()
        }
        items(posts) { post ->
            ItemPost(
                post = post,
                pulseBigHeart = pulseBigHeart,
                onPulseBigHeart = onPulseBigHeart,
            )
        }
    }
}

@Composable
private fun ItemPost(
    post: Post,
    pulseBigHeart: Boolean = false,
    onPulseBigHeart: (Boolean) -> Unit = {},
) {
    Spacer(modifier = Modifier.height(8.dp))

    Column {
        var pulseSmallHeart by remember { mutableStateOf(post.basePost.isLiked) }

        PostHeader(post)

        PostMedias(
            medias = post.images,
            pulseBigHeart = pulseBigHeart,
            onPulseBigHeart = onPulseBigHeart,
            onLikeClick = {
                pulseSmallHeart = true
            }
        )

        PostMetadata(
            post = post.basePost,
            pulse = pulseSmallHeart,
            onFavoriteClick = {
                pulseSmallHeart = !pulseSmallHeart
            }
        )
    }
}

@Composable
private fun PostHeader(post: Post) {
    ListItem(
        headlineContent = {
            Row(
                Modifier.offset(x = (-8).dp)
            ) {
                Text(
                    text = post.basePost.user.name,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
                    fontWeight = FontWeight.Medium,
                )
                Text(
                    text = "@${post.basePost.user.userName}",
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f),
                    fontSize = 14.sp,
                )
            }
        },
        leadingContent = {
            AsyncImageWithShimmer(
                post.basePost.user.avatar, "foto de perfil ${post.basePost.user.name}",
                modifier = Modifier
                    .size(40.dp)
                    .border(1.dp, MaterialTheme.colorScheme.onSurface.copy(0.2f), CircleShape)
                    .clip(CircleShape),
            )
        },
        trailingContent = {
            Icon(
                imageVector = Icons.Outlined.MoreVert,
                contentDescription = "Mais opções",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
            )
        },
        colors = ListItemDefaults.colors(
            containerColor = MaterialTheme.colorScheme.background,
        )
    )
}

@Composable
private fun PostMedias(
    medias: List<Int>,
    pulseBigHeart: Boolean = false,
    onPulseBigHeart: (Boolean) -> Unit = {},
    onLikeClick: () -> Unit
) {
    var firstClickTime by remember { mutableLongStateOf(0L) }
    var secondClickTime by remember { mutableLongStateOf(0L) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .noRippleClickable {
                val nowMillis = System.currentTimeMillis()
                if (firstClickTime == 0L) {
                    firstClickTime = nowMillis
                } else {
                    secondClickTime = nowMillis

                    val timeDifference = secondClickTime - firstClickTime
                    if (timeDifference in 1..1000) {
                        onPulseBigHeart(true)
                        firstClickTime = 0L
                        secondClickTime = 0L
                    } else {
                        firstClickTime = nowMillis
                        secondClickTime = 0L
                    }
                }
            }
    ) {
        if (medias.size > 1) {
            ItemCarouselView(medias)
        } else {
            ItemSingleImage(
                medias.first()
            )
        }

        val sizeAnimation by animateDpAsState(
            targetValue = if (pulseBigHeart) 120.dp else 0.dp,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow,
            ),
            label = "sizeAnimation",
        )

        LaunchedEffect(pulseBigHeart) {
            if (pulseBigHeart) {
                onLikeClick()
                delay(800)
                onPulseBigHeart(false)
            }
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(sizeAnimation)
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier
                    .size(sizeAnimation.value.dp)
            )
        }

    }
}

@Composable
private fun PostMetadata(
    post: BasePost,
    pulse: Boolean,
    onFavoriteClick: () -> Unit = {}
) {
    Row(
        Modifier
            .padding(horizontal = 8.dp)
            .height(36.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            val animatedColor by animateColorAsState(
                targetValue = if (pulse) Color.Red else MaterialTheme.colorScheme.primary,
                label = "color animation",
            )

            val sizeAnimation by animateDpAsState(
                targetValue = if (pulse) 32.dp else 26.dp,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioHighBouncy,
                    stiffness = Spring.StiffnessLow,
                ),
                label = "size animation",
            )

            Box(
                modifier = Modifier
                    .width(32.dp)
                    .noRippleClickable {
                        onFavoriteClick()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Favorite,
                    contentDescription = "Like",
                    modifier = Modifier
                        .size(sizeAnimation),
                    tint = animatedColor
                )
            }

            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                imageVector = Icons.Outlined.ModeComment,
                contentDescription = "Comment",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Spacer(modifier = Modifier.width(16.dp))

            Icon(
                imageVector = Icons.AutoMirrored.Outlined.Reply,
                contentDescription = "Share",
                modifier = Modifier
                    .size(24.dp)
                    .graphicsLayer {
                        rotationY = 180f
                    },
                tint = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
        Icon(
            imageVector = Icons.Outlined.BookmarkBorder,
            contentDescription = "Save",
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
    Text(
        text = pluralStringResource(
            id = R.plurals.likes_count,
            count = post.likes,
            post.likes
        ),
        modifier = Modifier.padding(start = 16.dp),
        color = MaterialTheme.colorScheme.onSurface,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    )
    Row {

        val nameWithDescription = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(post.user.name)
            }
            append(" ")
            append(post.description)
        }

        Text(
            text = nameWithDescription,
            modifier = Modifier.padding(start = 16.dp),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 14.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
        )
    }

    Text(
        text = "Há 6 horas",
        modifier = Modifier.padding(start = 16.dp),
        color = MaterialTheme.colorScheme.onSurface,
        fontSize = 12.sp,
    )
    Spacer(modifier = Modifier.height(8.dp))
}
