package com.example.lastipicas_grupo6.network

import com.example.lastipicas_grupo6.model.Producto
import retrofit2.Response
import retrofit2.http.GET

interface ServicioApi {
    @GET("Productos")
    suspend fun obtenerProductos(): Response<List<Producto>>
}