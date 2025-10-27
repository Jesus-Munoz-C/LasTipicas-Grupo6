package com.example.lastipicas_grupo6.model


data class PedidoUiState(
    val productosEnCarrito: Map<Producto, Int> = emptyMap(),
    val totalPedido: Int = 0
)