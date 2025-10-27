package com.example.lastipicas_grupo6.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.lastipicas_grupo6.data.UsuarioDataStore
import com.example.lastipicas_grupo6.model.LoginUiState
import com.example.lastipicas_grupo6.model.UsuarioErrores
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.first
import kotlin.Pair

class LoginVM(application: Application) : AndroidViewModel(application) {

    private val dataStore = UsuarioDataStore(getApplication())

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChange(valor: String) {
        _uiState.update { it.copy(email = valor, erroresusuario = it.erroresusuario.copy(email = null)) }
    }

    fun onPassChange(valor: String) {
        _uiState.update { it.copy(pass = valor, erroresusuario = it.erroresusuario.copy(pass = null)) }
    }

    suspend fun validarUsuario(): Boolean {
        val estadoActual = _uiState.value


        val credencialesGuardadas = dataStore.obtenerCredenciales().first()

        val emailGuardado = credencialesGuardadas.first
        val passGuardada = credencialesGuardadas.second

        val errores = UsuarioErrores(
            email = if (estadoActual.email != emailGuardado)
                "Email no registrado"
            else null,

            pass = if (estadoActual.pass != passGuardada)
                "Contraseña incorrecta"
            else null)

        val hayErrores = listOfNotNull(
            errores.email,
            errores.pass
        ).isNotEmpty()

        _uiState.update { it.copy(erroresusuario = errores) }

        if (!hayErrores) {
            viewModelScope.launch {
                dataStore.guardarEstadoSesion(true)
            }
        }
        return !hayErrores
    }
}