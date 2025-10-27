package com.example.lastipicas_grupo6.viewmodel

import androidx.lifecycle.ViewModel
import com.example.lastipicas_grupo6.model.PedidoUiState
import com.example.lastipicas_grupo6.model.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PedidoVM : ViewModel() {


    val listaProductos: List<Producto> = listOf(
        Producto("1", "Empanada de Pino", 2500),
        Producto("2", "Empanada de Queso", 2000),
        Producto("3", "Empanada Napolitana", 2800),
        Producto("4", "Empanada Champiñón", 2800),
        Producto("5", "Bebida Lata", 1500),
        Producto("6", "Café", 1000),
        Producto("7", "Té", 1000),
        Producto("8", "Agua", 800)
    )

    private val _uiState = MutableStateFlow(PedidoUiState())
    val uiState: StateFlow<PedidoUiState> = _uiState.asStateFlow()


    fun agregarAlCarrito(producto: Producto) {
        _uiState.update { currentState ->
            val nuevoCarrito = currentState.productosEnCarrito.toMutableMap()

            val cantidadActual = nuevoCarrito.getOrDefault(producto, 0)

            nuevoCarrito[producto] = cantidadActual + 1

            val nuevoTotal = nuevoCarrito.entries.sumOf { (producto, cantidad) ->
                producto.precio * cantidad
            }

            currentState.copy(
                productosEnCarrito = nuevoCarrito.toMap(),
                totalPedido = nuevoTotal
            )
        }
    }

    fun reiniciarPedido() {
        _uiState.update {
            PedidoUiState() // Resetea al estado inicial (vacío)
        }
    }

    // (Más adelante podemos añadir 'quitarDelCarrito')
}