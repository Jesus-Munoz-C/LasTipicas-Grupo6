package com.example.lastipicas_grupo6.network

import com.example.lastipicas_grupo6.model.LoginRequest
import com.example.lastipicas_grupo6.model.LoginResponse
import com.example.lastipicas_grupo6.model.PedidoRequest
import com.example.lastipicas_grupo6.model.PedidoResponse
import com.example.lastipicas_grupo6.model.UsuarioRegistro
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ServicioApi {


    @POST("signUp")
    suspend fun registrarUsuario(@Body usuario: UsuarioRegistro): Response<Void>

    @POST("login")
    suspend fun loginUsuario(@Body request: LoginRequest): Response<LoginResponse>

    // NUEVO ENDPOINT PARA PEDIDOS (Uno por uno)
    @POST("pedido")
    suspend fun registrarPedido(
        @Header("Authorization") token: String,
        @Body pedido: PedidoRequest
    ): Response<Void>

    @GET("pedido")
    suspend fun obtenerHistorial(
        @Header("Authorization") token: String
    ): Response<List<PedidoResponse>>
}