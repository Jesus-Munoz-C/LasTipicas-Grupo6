package com.example.lastipicas_grupo6.viewmodel

import androidx.lifecycle.ViewModel
import com.example.lastipicas_grupo6.model.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginVM : ViewModel() {


    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        _uiState.update { currentState ->
            currentState.copy(email = email)
        }
    }


    fun onPassChange(pass: String) {
        _uiState.update { currentState ->
            currentState.copy(pass = pass)
        }
    }

    fun login() {
        // ALEX. Aquí debe ir la lógica de validación e inicio de sesión
        println("LoginUiState: ${_uiState.value}")
    }
}