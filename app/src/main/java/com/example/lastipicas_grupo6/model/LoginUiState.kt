package com.example.lastipicas_grupo6.model

data class LoginUiState(
    val email: String = "",
    val pass: String = "",
    val errores: UsuarioErrores = UsuarioErrores()
    //Faltan agregar Errores por ejemplo: "El correo debe tener @."
)
data class UsuarioErrores(
    val email: String? = null,
    val pass: String? = null,
)