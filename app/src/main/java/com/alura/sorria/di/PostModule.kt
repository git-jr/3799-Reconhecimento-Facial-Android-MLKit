package com.alura.sorria.di

import com.alura.sorria.data.PostRepository
import com.alura.sorria.data.PostRepositoryImpl
import com.alura.sorria.data.local.PostLocalDataSource
import com.alura.sorria.domain.PostUseCase
import com.alura.sorria.domain.PostUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PostModule {

    @Provides
    fun providePostUseCase(postRepository: PostRepository): PostUseCase {
        return PostUseCaseImpl(postRepository)
    }

    @Provides
    fun providePostRepository(
        postLocalDataSource: PostLocalDataSource,
    ): PostRepository {
        return PostRepositoryImpl(postLocalDataSource)
    }

    @Provides
    fun providePostLocalDataSource(): PostLocalDataSource {
        return PostLocalDataSource()
    }
}