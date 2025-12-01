package com.example.lastipicas_grupo6.repository

import com.example.lastipicas_grupo6.model.LoginRequest
import com.example.lastipicas_grupo6.model.LoginResponse
import com.example.lastipicas_grupo6.model.UsuarioRegistro
import com.example.lastipicas_grupo6.network.RetrofitClient
import retrofit2.Response

class AuthRepository {

    private val api = RetrofitClient.instance

    // Función para conectar con /signUp
    suspend fun registrarUsuario(usuario: UsuarioRegistro): Response<Void> {
        return api.registrarUsuario(usuario)
    }

    // Función para conectar con /login
    suspend fun loginUsuario(email: String, pass: String): Response<LoginResponse> {
        val request = LoginRequest(email, pass)
        return api.loginUsuario(request)
    }
}