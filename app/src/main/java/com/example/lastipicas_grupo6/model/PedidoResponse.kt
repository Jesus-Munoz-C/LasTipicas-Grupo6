package com.example.lastipicas_grupo6.model

import com.google.gson.annotations.SerializedName


data class PedidoResponse(
    @SerializedName("_id") val id: String,
    @SerializedName("usuario") val usuario: String,
    @SerializedName("total") val total: Int,
    @SerializedName("fecha") val fecha: String,
    @SerializedName("productos") val productos: List<ItemHistorial>
)

data class ItemHistorial(
    @SerializedName("nombre") val nombre: String,
    @SerializedName("cantidad") val cantidad: Int,
    @SerializedName("precio") val precio: Int,
    @SerializedName("masa") val masa: String,
    @SerializedName("coccion") val coccion: String
)