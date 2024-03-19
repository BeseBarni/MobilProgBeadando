package com.example.mobilprogbeadando.data.remote

import com.squareup.moshi.Json

data class TextRequestBody(

    @field:Json(name = "context")
    val context: String = "A witty description about a plant you own",

    @field:Json(name = "formality")
    val formality : String = "prefer_less",

    @field:Json(name = "keywords")
    val keywords : List<String>,

    @field:Json(name = "max_tokens")
    val maxTokens : Int = 2048,

    @field:Json(name = "mode")
    val mode : String = "twitter",

    @field:Json(name = "model")
    val model : String = "chat-sophos-1",

    @field:Json(name = "n")
    val n : Int = 1,

    @field:Json(name = "source_lang")
    val sourceLang : String = "en",

    @field:Json(name = "target_lang")
    val targetLang : String = "en",

    @field:Json(name = "temperature")
    val tempetature : Float = 0.65f

)
