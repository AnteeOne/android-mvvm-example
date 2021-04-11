package com.anteeone.iconapp.di.modules

import com.anteeone.iconapp.data.network.IconApi
import com.anteeone.iconapp.data.repositories.ApiRepositoryImpl
import com.anteeone.iconapp.domain.repositories.ApiRepository
import com.anteeone.iconapp.domain.usecases.GetIconDetailUsecaseImpl
import com.anteeone.iconapp.domain.usecases.GetIconListUsecaseImpl
import com.anteeone.iconapp.domain.usecases.interfaces.GetIconDetailUsecase
import com.anteeone.iconapp.domain.usecases.interfaces.GetIconListUsecase
import com.anteeone.iconapp.ui.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    @Named("list")
    fun provideListViewModelFactory(usecase: GetIconListUsecase): ViewModelFactory {
        return ViewModelFactory(usecase)
    }

    @Provides
    @Singleton
    @Named("detail")
    fun provideDetailViewModelFactory(usecase: GetIconDetailUsecase): ViewModelFactory {
        return ViewModelFactory(usecase)
    }

    @Provides
    @Singleton
    fun provideGetIconListUsecase(
        apiRepository: ApiRepository
    ): GetIconListUsecase =
        GetIconListUsecaseImpl(apiRepository)

    @Provides
    @Singleton
    fun provideGetIconDetailUsecase(
        apiRepository: ApiRepository
    ): GetIconDetailUsecase =
        GetIconDetailUsecaseImpl(apiRepository)

    @Provides
    @Singleton
    fun provideApiRepository(api:IconApi): ApiRepository =
        ApiRepositoryImpl(api)


}