package com.example.lastipicas_grupo6.model

import com.google.gson.annotations.SerializedName

// El "Sobre Grande" que contiene todo el pedido
data class PedidoRequest(
    @SerializedName("usuario") val usuario: String,
    @SerializedName("telefono") val telefono: Long,
    @SerializedName("total") val total: Int,
    @SerializedName("productos") val productos: List<ProductoPedido>
)

// Cada item individual dentro de la lista
data class ProductoPedido(
    @SerializedName("nombre") val nombre: String,
    @SerializedName("cantidad") val cantidad: Int,
    @SerializedName("precio") val precio: Int,
    @SerializedName("masa") val masa: String,
    @SerializedName("coccion") val coccion: String
)