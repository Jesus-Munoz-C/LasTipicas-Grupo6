package com.example.lastipicas_grupo6

import org.junit.Test
import org.junit.Assert.*

class RegistroLogicTest {

    fun validarRegistro(nombre: String, email: String, pass: String, telefono: String): String? {
        if (nombre.isBlank()) return "Requerido"
        if (!email.contains("@")) return "Email inválido"
        if (pass.length < 8) return "Mínimo 8 caracteres"
        if (telefono.length != 9 || !telefono.all { it.isDigit() }) return "Teléfono inválido"
        return null
    }

    //Feliz
    @Test
    fun registro_Correcto() {
        val error = validarRegistro(
            nombre = "Maria",
            email = "maria@duoc.cl",
            pass = "12345678",
            telefono = "987654321"
        )
        assertNull("Debería pasar sin errores", error)
    }

    //Alternativo

    @Test
    fun email_sin_arroba() {
        val error = validarRegistro("Maria", "mariaduoc.cl", "12345678", "987654321")
        assertEquals("Email inválido", error)
    }

    @Test
    fun password_menor_a_8() {

        val error = validarRegistro("Maria", "maria@duoc.cl", "12345", "987654321")
        assertEquals("Mínimo 8 caracteres", error)
    }

    @Test
    fun nombre_vacio() {
        val error = validarRegistro("", "maria@duoc.cl", "12345678", "987654321")
        assertEquals("Requerido", error)
    }

    @Test
    fun telefono_invalido() {
        val error = validarRegistro("Maria", "maria@duoc.cl", "12345678", "123")
        assertEquals("Teléfono inválido", error)
    }
}