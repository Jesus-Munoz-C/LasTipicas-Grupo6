package com.example.lastipicas_grupo6.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lastipicas_grupo6.model.PedidoUiState
import com.example.lastipicas_grupo6.model.Producto
import com.example.lastipicas_grupo6.repository.ProductoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PedidoVM : ViewModel() {

    private val repositorio = ProductoRepository()

    private val _listaProductos = MutableStateFlow<List<Producto>>(emptyList())
    val listaProductos: StateFlow<List<Producto>> = _listaProductos.asStateFlow()

    private val _uiState = MutableStateFlow(PedidoUiState())
    val uiState: StateFlow<PedidoUiState> = _uiState.asStateFlow()


    init {
        cargarProductos()
    }


    fun cargarProductos() {
        viewModelScope.launch {
            val productosTraidos = repositorio.obtenerProductosDesdeApi()

            _listaProductos.value = productosTraidos
        }
    }

    fun agregarAlCarrito(producto: Producto) {
        _uiState.update { currentState ->
            val nuevoCarrito = currentState.productosEnCarrito.toMutableMap()
            val cantidadActual = nuevoCarrito.getOrDefault(producto, 0)
            nuevoCarrito[producto] = cantidadActual + 1

            val nuevoTotal = nuevoCarrito.entries.sumOf { (prod, cant) ->
                prod.precio * cant
            }
            currentState.copy(productosEnCarrito = nuevoCarrito.toMap(), totalPedido = nuevoTotal)
        }
    }

    fun quitarDelCarrito(producto: Producto) {
        _uiState.update { currentState ->
            val nuevoCarrito = currentState.productosEnCarrito.toMutableMap()
            val cantidadActual = nuevoCarrito.getOrDefault(producto, 0)

            if (cantidadActual == 1) {
                nuevoCarrito.remove(producto)
            } else if (cantidadActual > 1) {
                nuevoCarrito[producto] = cantidadActual - 1
            }

            val nuevoTotal = nuevoCarrito.entries.sumOf { (prod, cant) ->
                prod.precio * cant
            }
            currentState.copy(productosEnCarrito = nuevoCarrito.toMap(), totalPedido = nuevoTotal)
        }
    }

    fun reiniciarPedido() {
        _uiState.update { PedidoUiState() }
    }
}