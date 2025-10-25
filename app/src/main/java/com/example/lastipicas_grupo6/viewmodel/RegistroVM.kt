package com.example.lastipicas_grupo6.viewmodel

import androidx.lifecycle.ViewModel
import com.example.lastipicas_grupo6.model.RegistroUsuarioErrores
import com.example.lastipicas_grupo6.model.UsuarioUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegistroVM : ViewModel() {


    private val _uiState = MutableStateFlow(UsuarioUiState())
    val uiState: StateFlow<UsuarioUiState> = _uiState.asStateFlow()

    fun onNombreChange(valor: String){
        _uiState.update{ it.copy(nombre = valor, errores = it.errores.copy(nombre = null)) }
    }
    fun onEmailChange(valor: String) {
        _uiState.update { it.copy(email = valor, errores = it.errores.copy(email = null)) }
    }

    fun onPassChange(valor: String) {
        _uiState.update { it.copy(pass = valor, errores = it.errores.copy(pass = null)) }
    }

    fun onDirecccionChange(valor: String){
        _uiState.update { it.copy(direccion = valor, errores = it.errores.copy(direccion = null)) }
    }

    fun onTelefonoChange(valor: String){
        _uiState.update { it.copy(telefono = valor, errores = it.errores.copy(telefono = null)) }
    }

    fun onAceptaTerminos(valor: Boolean){
        _uiState.update { it.copy(aceptaTerminos = valor) }
    }

    fun validarUsuario() : Boolean {
        val estadoActual = _uiState.value
        val errores = RegistroUsuarioErrores (
            nombre = if (estadoActual.nombre.isBlank())"Campo obligatorio" else null,
            email = if (!estadoActual.email.contains(other = "@")) "Correo invalido" else null,
            pass = if (estadoActual.pass.length < 6) "Debe tener al menos 6 caracteres" else null,
            direccion = if (estadoActual.direccion.isBlank()) "Campo obligatorio" else null,
            telefono = if (estadoActual.telefono.length != 8) "Debe tener 8 numeros" else null
        )

        val hayErrores = listOfNotNull(
            errores.nombre,
            errores.email,
            errores.pass,
            errores.direccion,
            errores.telefono
        ).isNotEmpty()
        _uiState.update{it.copy(errores = errores)}
        return !hayErrores
    }
}