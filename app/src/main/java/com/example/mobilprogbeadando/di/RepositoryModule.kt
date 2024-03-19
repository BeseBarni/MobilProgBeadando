package com.example.mobilprogbeadando.di

import com.example.mobilprogbeadando.data.dao.PlantDao
import com.example.mobilprogbeadando.data.dao.PlantLocationDao
import com.example.mobilprogbeadando.data.location.DefaultLocationTracker
import com.example.mobilprogbeadando.data.repository.PlantLocationRepositoryImpl
import com.example.mobilprogbeadando.data.repository.PlantRepositoryImpl
import com.example.mobilprogbeadando.data.repository.TextAiRepositoryImpl
import com.example.mobilprogbeadando.data.repository.WeatherRepositoryImpl
import com.example.mobilprogbeadando.domain.location.LocationTracker
import com.example.mobilprogbeadando.domain.repository.PlantLocationRepository
import com.example.mobilprogbeadando.domain.repository.PlantRepository
import com.example.mobilprogbeadando.domain.repository.TextAiRepository
import com.example.mobilprogbeadando.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(weatherRepositoryImpl : WeatherRepositoryImpl) : WeatherRepository


    @Binds
    @Singleton
    abstract fun bindTextAiRepository(textAiRepositoryImpl: TextAiRepositoryImpl) : TextAiRepository
}