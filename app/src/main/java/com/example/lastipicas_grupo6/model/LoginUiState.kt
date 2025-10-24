package com.example.lastipicas_grupo6.model

data class LoginUiState(
    val email: String = "",
    val pass: String = ""
    //Faltan agregar Errores por ejemplo: "El correo debe tener @."
)