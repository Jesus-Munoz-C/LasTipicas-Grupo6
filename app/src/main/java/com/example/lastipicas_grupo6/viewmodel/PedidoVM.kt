package com.example.lastipicas_grupo6.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.lastipicas_grupo6.data.UsuarioDataStore
import com.example.lastipicas_grupo6.model.PedidoRequest
import com.example.lastipicas_grupo6.model.PedidoResponse
import com.example.lastipicas_grupo6.model.PedidoUiState
import com.example.lastipicas_grupo6.model.Producto
import com.example.lastipicas_grupo6.model.ProductoPedido
import com.example.lastipicas_grupo6.repository.ProductoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PedidoVM(application: Application) : AndroidViewModel(application) {

    private val repositorio = ProductoRepository()
    private val dataStore = UsuarioDataStore(getApplication())

    private val _listaProductos = MutableStateFlow<List<Producto>>(emptyList())
    val listaProductos: StateFlow<List<Producto>> = _listaProductos.asStateFlow()

    private val _uiState = MutableStateFlow(PedidoUiState())
    val uiState: StateFlow<PedidoUiState> = _uiState.asStateFlow()

    private val _historial = MutableStateFlow<List<PedidoResponse>>(emptyList())
    val historial: StateFlow<List<PedidoResponse>> = _historial.asStateFlow()

    init {
        cargarProductos()
    }

    fun cargarProductos() {
        _listaProductos.value = repositorio.obtenerProductosLocales()
    }

    fun confirmarCompra(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val token = dataStore.obtenerToken.first()
            val credenciales = dataStore.obtenerCredenciales().first()

            val emailUsuario = credenciales.first ?: "anonimo@lastipicas.cl"
            val telefonoUsuario = 912345678L

            if (!token.isNullOrBlank() && _uiState.value.productosEnCarrito.isNotEmpty()) {

                val listaParaEnviar = _uiState.value.productosEnCarrito.map { (producto, cantidad) ->
                    val (masa, coccion) = calcularDetalles(producto.nombre)

                    ProductoPedido(
                        nombre = producto.nombre,
                        cantidad = cantidad,
                        precio = producto.precio,
                        masa = masa,
                        coccion = coccion
                    )
                }

                val pedidoCompleto = PedidoRequest(
                    usuario = emailUsuario,
                    telefono = telefonoUsuario,
                    total = _uiState.value.totalPedido,
                    productos = listaParaEnviar
                )

                try {
                    val respuesta = repositorio.enviarPedido(token, pedidoCompleto)

                    if (respuesta.isSuccessful) {
                        reiniciarPedido()
                        onSuccess()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun calcularDetalles(nombre: String): Pair<String, String> {
        return when {
            nombre.contains("Pino", ignoreCase = true) -> Pair("Tradicional", "Horno")
            nombre.contains("Queso", ignoreCase = true) -> Pair("Milhoja", "Frita")
            nombre.contains("Bebida", ignoreCase = true) -> Pair("N/A", "Frio")
            else -> Pair("Tradicional", "Horno")
        }
    }

    fun agregarAlCarrito(producto: Producto) {
        _uiState.update { currentState ->
            val nuevoCarrito = currentState.productosEnCarrito.toMutableMap()
            val cantidadActual = nuevoCarrito.getOrDefault(producto, 0)
            nuevoCarrito[producto] = cantidadActual + 1
            val nuevoTotal = nuevoCarrito.entries.sumOf { (prod, cant) -> prod.precio * cant }
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
            val nuevoTotal = nuevoCarrito.entries.sumOf { (prod, cant) -> prod.precio * cant }
            currentState.copy(productosEnCarrito = nuevoCarrito.toMap(), totalPedido = nuevoTotal)
        }
    }

    fun reiniciarPedido() {
        _uiState.update { PedidoUiState() }
    }

    fun cargarHistorial() {
        viewModelScope.launch {
            val token = dataStore.obtenerToken.first()
            val credenciales = dataStore.obtenerCredenciales().first()

            val emailActual = (credenciales.first)
            val nombreActual = (dataStore.obtenerNombre.first())

            if (!token.isNullOrBlank()) {
                try {
                    val listaCompleta = repositorio.obtenerHistorialPedidos(token)

                    _historial.value = listaCompleta.filter { pedido ->
                        val usuarioDelPedido = pedido.usuario
                        val esMiCorreo = usuarioDelPedido.equals(emailActual, ignoreCase = true)
                        val esMiNombre = usuarioDelPedido.equals(nombreActual, ignoreCase = true)

                        esMiCorreo || esMiNombre
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                    _historial.value = emptyList()
                }
            }
        }
    }
}