package com.alura.sorria.data.model

data class User(
    val name: String,
    val userName: String,
    val avatar: Int,
    val followers: Int,
    val following: Int,
    val posts: Int,
    val isFollowing: Boolean,
    val isMe: Boolean,
)
