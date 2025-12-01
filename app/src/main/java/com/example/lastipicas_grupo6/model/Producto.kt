package com.example.lastipicas_grupo6.model

import com.google.gson.annotations.SerializedName

data class Producto(
    @SerializedName("_id") val id: String,


    @SerializedName("tipo") val nombre: String,

    @SerializedName("precio") val precio: Int,

    @SerializedName("stock") val stock: Int,
    @SerializedName("estado") val estado: Boolean,

    val imagen: String = "https://www.gourmet.cl/wp-content/uploads/2016/09/Empanada-de-Pino-web.jpg"
)