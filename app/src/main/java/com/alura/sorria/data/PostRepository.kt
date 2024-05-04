package com.alura.sorria.data

import com.alura.sorria.data.local.PostLocalDataSource
import com.alura.sorria.domain.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface PostRepository {
    suspend fun getPosts(): Flow<List<Post>>
}

class PostRepositoryImpl @Inject constructor(
    private val postLocalDataSource: PostLocalDataSource,
) : PostRepository {
    override suspend fun getPosts(): Flow<List<Post>> {

        return flow {
            val localPosts = postLocalDataSource.posts

            if (localPosts.isNotEmpty()) {
                emit(localPosts)
            }
        }
    }
}