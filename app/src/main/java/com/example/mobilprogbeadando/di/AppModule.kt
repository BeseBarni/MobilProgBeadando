package com.example.mobilprogbeadando.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.mobilprogbeadando.data.dao.PlantDao
import com.example.mobilprogbeadando.data.dao.PlantLocationDao
import com.example.mobilprogbeadando.data.database.PlantsDatabase
import com.example.mobilprogbeadando.data.remote.TextApi
import com.example.mobilprogbeadando.data.remote.WeatherApi
import com.example.mobilprogbeadando.data.repository.PlantLocationRepositoryImpl
import com.example.mobilprogbeadando.data.repository.PlantRepositoryImpl
import com.example.mobilprogbeadando.domain.repository.PlantLocationRepository
import com.example.mobilprogbeadando.domain.repository.PlantRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun proideWeatherApi() : WeatherApi {
        return Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideTextApi() : TextApi {

        val intercepter = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(intercepter)
                // time out setting
                .connectTimeout(3, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(25,TimeUnit.SECONDS)

        }.build()

        return Retrofit.Builder()
            .baseUrl("https://api.textcortex.com/v1/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }


    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(app : Application) : FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }

    @Provides
    @Singleton
    fun providePlantsDatabase(@ApplicationContext appContext: Context) : PlantsDatabase {
        return Room.databaseBuilder(
            appContext,
            PlantsDatabase::class.java,
            "plants_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePlantDao(plantsDatabase: PlantsDatabase) : PlantDao {
        return  plantsDatabase.plantDao()
    }

    @Provides
    @Singleton
    fun providePlantLocationDao(plantsDatabase: PlantsDatabase) : PlantLocationDao {
        return plantsDatabase.plantLocationDao()
    }

    @Provides
    @Singleton
    fun providePlantRepository(plantDao: PlantDao) : PlantRepository {
        return PlantRepositoryImpl(plantDao)
    }

    @Provides
    @Singleton
    fun providePlantLocationRepository(plantLocationDao: PlantLocationDao) : PlantLocationRepository {
        return PlantLocationRepositoryImpl(plantLocationDao)
    }


}