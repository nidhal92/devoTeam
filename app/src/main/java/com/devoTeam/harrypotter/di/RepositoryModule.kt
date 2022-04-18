package com.devoTeam.harrypotter.di

import com.devoTeam.harrypotter.data.local.CartDao
import com.devoTeam.harrypotter.data.remote.BookRemoteDataSource
import com.devoTeam.harrypotter.data.repository.BookRepositoryImpl
import com.devoTeam.harrypotter.data.repository.CartBookRepositoryImpl
import com.devoTeam.harrypotter.domain.repository.BookRepository
import com.devoTeam.harrypotter.domain.repository.CartBookRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideBookRepository(
        remoteDataSource: BookRemoteDataSource
    ): BookRepository {
        return BookRepositoryImpl(remoteDataSource)
    }
    @Provides
    fun provideChartBookRepository(
        dao: CartDao
    ): CartBookRepository {
        return CartBookRepositoryImpl(dao)
    }
}
