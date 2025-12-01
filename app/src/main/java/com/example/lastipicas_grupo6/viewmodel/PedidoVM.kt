package com.example.lastipicas_grupo6.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.lastipicas_grupo6.data.UsuarioDataStore
import com.example.lastipicas_grupo6.model.PedidoRequest
import com.example.lastipicas_grupo6.model.PedidoResponse
import com.example.lastipicas_grupo6.model.PedidoUiState
import com.example.lastipicas_grupo6.model.Producto
// IMPORTANTE: Agregamos este import que faltaba para crear la lista de productos
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

    // --- FUNCIÓN PRINCIPAL DE COMPRA (Agrupada) ---
    fun confirmarCompra(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val token = dataStore.obtenerToken.first()
            val credenciales = dataStore.obtenerCredenciales().first()

            val emailUsuario = credenciales.first ?: "anonimo@lastipicas.cl"
            // Podrías guardar el teléfono en DataStore también, por ahora usamos uno fijo o lo sacamos del perfil
            val telefonoUsuario = 912345678L

            if (!token.isNullOrBlank() && _uiState.value.productosEnCarrito.isNotEmpty()) {

                // 1. Transformamos el carrito en la lista de items (ProductoPedido)
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

                // 2. Armamos el "Paquete Completo" (PedidoRequest)
                val pedidoCompleto = PedidoRequest(
                    usuario = emailUsuario,
                    telefono = telefonoUsuario,
                    total = _uiState.value.totalPedido, // Enviamos el total calculado
                    productos = listaParaEnviar
                )

                // 3. Enviamos UNA sola vez el objeto completo
                println("PEDIDO_DEBUG: Enviando pedido completo -> $pedidoCompleto")

                try {
                    val respuesta = repositorio.enviarPedido(token, pedidoCompleto)

                    if (respuesta.isSuccessful) {
                        println("PEDIDO_EXITO: Pedido agrupado guardado!")
                        reiniciarPedido() // Limpiamos el carrito local
                        onSuccess() // Navegamos
                    } else {
                        val errorMsg = respuesta.errorBody()?.string()
                        println("PEDIDO_ERROR: ${respuesta.code()} - $errorMsg")
                    }
                } catch (e: Exception) {
                    println("PEDIDO_CRASH: ${e.message}")
                    e.printStackTrace()
                }
            } else {
                println("PEDIDO_ERROR: Token vacío o carrito vacío")
            }
        }
    }

    // Función auxiliar para calcular datos requeridos por la API
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

    // --- CARGA DE HISTORIAL ---
    fun cargarHistorial() {
        viewModelScope.launch {
            val token = dataStore.obtenerToken.first()
            val credenciales = dataStore.obtenerCredenciales().first()
            val emailActual = credenciales.first

            if (!token.isNullOrBlank() && emailActual != null) {
                try {
                    val listaCompleta = repositorio.obtenerHistorialPedidos(token)

                    // Filtramos para mostrar solo los pedidos de este usuario
                    _historial.value = listaCompleta.filter { pedido ->
                        pedido.usuario == emailActual
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    _historial.value = emptyList()
                }
            }
        }
    }
}