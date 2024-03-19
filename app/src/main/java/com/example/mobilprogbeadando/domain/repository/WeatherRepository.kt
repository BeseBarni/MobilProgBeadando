package com.example.mobilprogbeadando.domain.repository

import com.example.mobilprogbeadando.domain.util.Resource
import com.example.mobilprogbeadando.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double) : Resource<WeatherInfo>
}