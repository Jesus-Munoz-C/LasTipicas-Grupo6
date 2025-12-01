package com.example.lastipicas_grupo6.repository

import com.example.lastipicas_grupo6.model.PedidoRequest
import com.example.lastipicas_grupo6.model.PedidoResponse
import com.example.lastipicas_grupo6.model.Producto
import com.example.lastipicas_grupo6.network.RetrofitClient
import retrofit2.Response

class ProductoRepository {

    private val api = RetrofitClient.instance


    fun obtenerProductosLocales(): List<Producto> {
        return listOf(
            Producto(
                "1",
                "Empanada de Pino",
                2500,
                50,
                true,
                "https://www.gourmet.cl/wp-content/uploads/2016/09/Empanada-de-Pino-web.jpg"
            ),
            Producto(
                "2",
                "Empanada de Queso",
                2000,
                50,
                true,
                "https://assets.unileversolutions.com/recipes-v2/243763.jpg"
            ),
            Producto(
                "3",
                "Empanada Napolitana",
                2800,
                30,
                true,
                "https://www.gourmet.cl/wp-content/uploads/2018/01/Foto-editada.jpg"
            ),
            Producto(
                "4",
                "Bebida Lata",
                1500,
                100,
                true,
                "https://dojiw2m9tvv09.cloudfront.net/11132/product/X_cocacola-lata-350cc4526.jpg"
            )
        )
    }

    suspend fun enviarPedido(token: String, pedido: PedidoRequest): Response<Void> {
        return api.registrarPedido("Bearer $token", pedido)
    }

    suspend fun obtenerHistorialPedidos(token: String): List<PedidoResponse> {
        return try {
            val response = api.obtenerHistorial("Bearer $token")
            if (response.isSuccessful) {
                response.body() ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}