package com.example.lastipicas_grupo6.model

data class LoginUiState(
    val email: String = "",
    val pass: String = "",
    val erroresusuario: UsuarioErrores = UsuarioErrores()
)
data class UsuarioErrores(
    val email: String? = null,
    val pass: String? = null,
)