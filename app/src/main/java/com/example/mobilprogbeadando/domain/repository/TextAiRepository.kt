package com.example.mobilprogbeadando.domain.repository

import com.example.mobilprogbeadando.data.remote.TextApiResponse

interface TextAiRepository {

    suspend fun getDescription(name : String, traits : List<String>) : TextApiResponse

}