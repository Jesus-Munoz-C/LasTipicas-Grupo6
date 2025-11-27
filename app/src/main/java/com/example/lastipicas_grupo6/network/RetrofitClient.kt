package com.example.lastipicas_grupo6.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://6927d06fb35b4ffc50132c4e.mockapi.io/"

    val instance: ServicioApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        retrofit.create(ServicioApi::class.java)
    }
}