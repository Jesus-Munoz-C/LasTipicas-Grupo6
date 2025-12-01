package com.example.lastipicas_grupo6.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.lastipicas_grupo6.data.UsuarioDataStore
import com.example.lastipicas_grupo6.model.LoginUiState
import com.example.lastipicas_grupo6.model.UsuarioErrores
import com.example.lastipicas_grupo6.repository.AuthRepository // IMPORTANTE
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginVM(application: Application) : AndroidViewModel(application) {

    private val repository = AuthRepository() // CAMBIO: Usamos el repo de Auth
    private val dataStore = UsuarioDataStore(getApplication())

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChange(valor: String) {
        _uiState.update { it.copy(email = valor, erroresusuario = it.erroresusuario.copy(email = null)) }
    }

    fun onPassChange(valor: String) {
        _uiState.update { it.copy(pass = valor, erroresusuario = it.erroresusuario.copy(pass = null)) }
    }

    fun login(onSuccess: () -> Unit) {
        val email = _uiState.value.email
        val pass = _uiState.value.pass

        val errorEmail = if (email.isBlank()) "Ingrese su correo" else null
        val errorPass = if (pass.isBlank()) "Ingrese su contraseña" else null

        if (errorEmail != null || errorPass != null) {
            _uiState.update {
                it.copy(erroresusuario = UsuarioErrores(email = errorEmail, pass = errorPass))
            }
            return
        }

        viewModelScope.launch {
            try {
                val respuesta = repository.loginUsuario(email, pass) // Ahora sí existe esta función

                if (respuesta.isSuccessful && respuesta.body() != null) {
                    val loginResponse = respuesta.body()!!

                    dataStore.guardarToken(loginResponse.token)
                    dataStore.guardarCredenciales(email, pass)
                    dataStore.guardarEstadoSesion(true)

                    onSuccess()
                } else {
                    _uiState.update { it.copy(erroresusuario = UsuarioErrores(pass = "Credenciales incorrectas")) }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(erroresusuario = UsuarioErrores(pass = "Error: ${e.message}")) }
            }
        }
    }
}