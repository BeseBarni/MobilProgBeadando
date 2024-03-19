package com.example.mobilprogbeadando.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface TextApi {

    @POST("texts/social-media-posts")
    @Headers(
        "Content-Type: application/json",
        "Authorization: Bearer gAAAAABl-gkdTGdJZE72Z7psSRd7vIPBntYe3B1ywDeLbRbUgImg8Etj5bCyFcahinAWX0qan0_uR3p6r0zmTY4XtjMRvx3P1T-rLd9vPShk2CtIKtHZgicvqnZdmNyGVkFCjm5QGZ_-"
    )
    suspend fun getDescription(@Body textRequestBody: TextRequestBody) : TextApiResponse

}