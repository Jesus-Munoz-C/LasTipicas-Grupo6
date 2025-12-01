package com.example.lastipicas_grupo6.model

import com.google.gson.annotations.SerializedName

data class UsuarioRegistro(
    @SerializedName("nombre") val nombre: String,

    @SerializedName("apellido") val apellido: String = "",

    @SerializedName("correo") val email: String,


    @SerializedName("telefono") val telefono: Long,


    @SerializedName("dirrecion") val direccion: String,

    @SerializedName("contrasena") val pass: String
)


data class LoginRequest(
    @SerializedName("correo") val email: String,
    @SerializedName("contrasena") val pass: String
)


data class LoginResponse(
    @SerializedName("token") val token: String,

    @SerializedName("user") val usuario: UsuarioRegistro?
)