package com.alura.sorria.domain.model

import com.alura.sorria.data.model.BasePost

data class Post(
    val id: String,
    val basePost: BasePost,
    val images: List<Int>,
)

