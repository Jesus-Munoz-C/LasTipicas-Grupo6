package com.example.lastipicas_grupo6.model

import androidx.annotation.DrawableRes

data class Producto(
    val id: String,
    val nombre: String,
    val precio: Int,
    @DrawableRes val imagen: Int
)