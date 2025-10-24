package com.example.lastipicas_grupo6.viewmodel

import androidx.lifecycle.ViewModel
import com.example.lastipicas_grupo6.model.LoginUiState
import com.example.lastipicas_grupo6.model.UsuarioErrores
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginVM : ViewModel() {


    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    //Actulizamos el campo Email y limpiamos su error
    fun onEmailChange(valor: String) {
        _uiState.update { it.copy(email = valor, errores = it.errores.copy(email = null)) }
    }


    fun onPassChange(valor: String) {
        _uiState.update { it.copy(pass = valor, errores = it.errores.copy(pass = null)) }
    }

    fun login() {
        // ALEX. Aquí debe ir la lógica de validación e inicio de sesión
       // println("LoginUiState: ${_uiState.value}")
    }

    fun validarUsuario() : Boolean {
        val estadoActual = _uiState.value
        val errores = UsuarioErrores (
            email = if (!estadoActual.email.contains(other = "@")) "Correo invalido" else null,
            pass = if (estadoActual.pass.length < 6) "Debe tener al menos 6 caracteres" else null
        )

        val hayErrores = listOfNotNull(
            errores.email,
            errores.pass,
        ).isNotEmpty()
        _uiState.update{it.copy(errores = errores)}
        return !hayErrores
    }
}