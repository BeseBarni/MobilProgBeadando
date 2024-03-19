package com.example.mobilprogbeadando.data.remote

import com.squareup.moshi.Json

data class Outputs(
    @field:Json(name = "index")
    val index : Int,

    @field:Json(name = "text")
    val text : String,

    @field:Json(name = "id")
    val id : String
)

data class Data(
    @field:Json(name = "outputs")
    val outputs : List<Outputs>
)

data class TextApiResponse(
    @field:Json(name = "status")
    val status : String,

    @field:Json(name = "data")
    val data : Data
)
