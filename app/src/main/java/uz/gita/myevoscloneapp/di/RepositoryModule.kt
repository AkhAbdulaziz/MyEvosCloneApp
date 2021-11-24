package uz.gita.myevoscloneapp.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.myevoscloneapp.domain.repository.AppRepository
import uz.gita.myevoscloneapp.domain.repository.impl.AppRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun getAppRepository(repository: AppRepositoryImpl): AppRepository
}