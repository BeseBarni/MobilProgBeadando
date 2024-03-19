package com.example.mobilprogbeadando.data.repository

import android.util.Log
import com.example.mobilprogbeadando.data.mappers.toWeatherInfo
import com.example.mobilprogbeadando.data.remote.Data
import com.example.mobilprogbeadando.data.remote.Outputs
import com.example.mobilprogbeadando.data.remote.TextApi
import com.example.mobilprogbeadando.data.remote.TextApiResponse
import com.example.mobilprogbeadando.data.remote.TextRequestBody
import com.example.mobilprogbeadando.domain.repository.TextAiRepository
import com.example.mobilprogbeadando.domain.util.Resource
import javax.inject.Inject

class TextAiRepositoryImpl @Inject constructor(private val textApi : TextApi) : TextAiRepository {
    override suspend fun getDescription(name: String, traits: List<String>): TextApiResponse {
        return try {

            val data = textApi.getDescription(
                TextRequestBody(keywords = traits + name)
            )
            Log.i("API DATA", data.status)
            return data
        } catch(e: Exception) {
            e.printStackTrace()
            Log.e("AI ERROR", e.message!!)
            Log.e("AI ERROR", e.localizedMessage!!)

            return TextApiResponse("error", Data(listOf(Outputs(0,"",""))))
        }
    }
}