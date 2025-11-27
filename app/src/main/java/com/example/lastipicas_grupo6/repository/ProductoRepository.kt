package com.example.lastipicas_grupo6.repository

import com.example.lastipicas_grupo6.model.Producto
import com.example.lastipicas_grupo6.network.RetrofitClient

class ProductoRepository {

    // Obtenemos la instancia de nuestro "Mesero" (Retrofit)
    private val api = RetrofitClient.instance

    // Esta función va a buscar los productos a internet
    suspend fun obtenerProductosDesdeApi(): List<Producto> {
        return try {
            val respuesta = api.obtenerProductos() // Llamamos a la API

            if (respuesta.isSuccessful) {
                // Si todo salió bien (código 200), devolvemos la lista o una lista vacía si es null
                respuesta.body() ?: emptyList()
            } else {
                // Si hubo un error (ej: 404, 500), devolvemos lista vacía
                emptyList()
            }
        } catch (e: Exception) {
            // Si falló el internet o la app explotó, devolvemos lista vacía para que no se cierre
            e.printStackTrace()
            emptyList()
        }
    }
}