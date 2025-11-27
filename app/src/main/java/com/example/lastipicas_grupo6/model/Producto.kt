package com.example.lastipicas_grupo6.model

import com.google.gson.annotations.SerializedName

data class Producto(

    @SerializedName("id") val id: String,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("precio") val precio: Int,


    @SerializedName("imagen") val imagen: String
)