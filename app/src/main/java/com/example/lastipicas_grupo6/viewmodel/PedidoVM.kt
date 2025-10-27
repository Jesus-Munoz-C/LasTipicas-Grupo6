package com.example.lastipicas_grupo6.viewmodel

import androidx.lifecycle.ViewModel
import com.example.lastipicas_grupo6.model.PedidoUiState
import com.example.lastipicas_grupo6.model.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.example.lastipicas_grupo6.R

class PedidoVM : ViewModel() {


    val listaProductos: List<Producto> = listOf(
        Producto("1", "Empanada de Pino", 2500, R.drawable.pino),
        Producto("2", "Empanada de Queso", 2000, R.drawable.queso),
        Producto("3", "Empanada Napolitana", 2800, R.drawable.napo),
        Producto("4", "Empanada Champiñón y Queso", 2800, R.drawable.chamqueso),
        Producto("5", "Bebida Lata", 1500, R.drawable.bebida),
        Producto("6", "Café", 1000, R.drawable.cafe),
        Producto("7", "Té", 1000, R.drawable.te),
        Producto("8", "Agua", 800, R.drawable.agua)
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
            PedidoUiState()
        }
    }

    fun quitarDelCarrito(producto: Producto) {
        _uiState.update { currentState ->

            val nuevoCarrito = currentState.productosEnCarrito.toMutableMap()

            val cantidadActual = nuevoCarrito.getOrDefault(producto, 0)

            if (cantidadActual == 1) {
                nuevoCarrito.remove(producto)
            }
            else if (cantidadActual > 1) {
                nuevoCarrito[producto] = cantidadActual - 1
            }

            val nuevoTotal = nuevoCarrito.entries.sumOf { (producto, cantidad) ->
                producto.precio * cantidad
            }

            currentState.copy(
                productosEnCarrito = nuevoCarrito.toMap(),
                totalPedido = nuevoTotal
            )
        }
    }
}