package com.example.lastipicas_grupo6

import org.junit.Test
import org.junit.Assert.*

class LoginLogicTest {

    // Lógica simulada del ViewModel
    fun validarLogin(email: String, pass: String): String? {
        if (email.isBlank()) return "El correo no puede estar vacío"
        if (pass.isBlank()) return "La contraseña no puede estar vacía"
        return null // Null significa que NO hay errores (Éxito)
    }

    //Prueba Feliz.

    @Test
    fun login_correcto() {
        val error = validarLogin("juan@duoc.cl", "12345678")

        assertNull("Con datos correctos, el error debería ser null", error)
    }

    //Alternativo
    @Test
    fun Correo_Login() {
        val error = validarLogin("", "123456")

        assertEquals("El correo no puede estar vacío", error)
    }

    @Test
    fun Clave_login() {
        val error = validarLogin("juan@duoc.cl", "")

        assertEquals("La contraseña no puede estar vacía", error)
    }
}