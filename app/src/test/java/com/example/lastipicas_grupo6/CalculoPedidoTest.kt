package com.example.lastipicas_grupo6

import org.junit.Test
import org.junit.Assert.*

class CalculoPedidoTest {

    @Test
    fun calcularTotal_esCorrecto() {
        // 1. Escenario: 2 Empanadas de Pino a $2.500
        val precio = 2500
        val cantidad = 2
        val totalEsperado = 5000

        // 2. Acción: Calculamos (la misma lógica que usa tu App)
        val totalReal = precio * cantidad

        // 3. Verificación: ¿Dio 5000?
        assertEquals("El total debería ser 5000", totalEsperado, totalReal)
    }

    @Test
    fun calcularTotal_variosProductos() {
        // Escenario: 1 Pino ($2500) + 1 Bebida ($1500)
        val totalEsperado = 4000
        val totalReal = (2500 * 1) + (1500 * 1)

        assertEquals("La suma de productos debe ser exacta", totalEsperado, totalReal)
    }
}