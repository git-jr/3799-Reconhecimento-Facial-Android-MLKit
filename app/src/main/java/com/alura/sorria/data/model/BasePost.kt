package com.alura.sorria.data.model


data class BasePost(
    val description: String,
    val likes: Int,
    val comments: Int,
    val time: String,
    val user: User,
    val isLiked: Boolean
)

