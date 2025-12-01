package com.example.lastipicas_grupo6

import org.junit.Test
import org.junit.Assert.*

class PedidoLogicTest {

    //FELIZ
    @Test
    fun pedido_calculoTotal() {
        val totalEsperado = 6500
        val totalCalculado = (2500 * 2) + (1500 * 1)

        assertEquals(totalEsperado, totalCalculado)
    }

    @Test
    fun pedido_Masa_correcta() {
        val nombre = "Empanada de Pino"
        val (masa, coccion) = when {
            nombre.contains("Pino") -> Pair("Tradicional", "Horno")
            else -> Pair("Otra", "Otra")
        }

        assertEquals("Tradicional", masa)
        assertEquals("Horno", coccion)
    }

    //Alternativo
    @Test
    fun pedido_Vacio() {
        val cantidad = 0
        val precio = 2500
        val total = cantidad * precio

        assertEquals(0, total)
    }

    @Test
    fun historial_Usuario() {
        val listaServidor = listOf("pedro@mail.com", "juan@mail.com")
        val miUsuario = "yo@mail.com"

        val misPedidos = listaServidor.filter { it == miUsuario }

        assertTrue("La lista debería estar vacía si no hay coincidencias", misPedidos.isEmpty())
    }
}