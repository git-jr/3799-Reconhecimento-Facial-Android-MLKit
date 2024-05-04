package com.alura.sorria.ui.screens.home

import com.alura.sorria.domain.model.Post

data class HomeUiState(
    val posts: List<Post>,
    val pulseBigHeart: Boolean = false,
    val listenedText: String = "",
    val isListening: Boolean = false,
    val errorOnListening: Boolean = false,
    val navigateTo: String? = null,
    val currentRouteIndex: Int = 0,
    val showCameraPreview: Boolean = false
)