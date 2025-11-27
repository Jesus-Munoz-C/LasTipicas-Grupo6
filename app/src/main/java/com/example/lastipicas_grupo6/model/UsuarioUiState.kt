package com.example.lastipicas_grupo6.model

data class UsuarioUiState(
    val nombre: String = "",
    val email: String = "",
    val pass: String = "",
    val direccion: String = "",
    val telefono: String = "",
    val aceptaTerminos: Boolean = false,
    val errores: RegistroUsuarioErrores = RegistroUsuarioErrores()
)
data class RegistroUsuarioErrores(
    val nombre: String? = null,
    val email: String? = null,
    val pass: String? = null,
    val direccion: String? = null,
    val telefono: String? = null
)