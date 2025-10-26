package com.example.lastipicas_grupo6.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.lastipicas_grupo6.data.UsuarioDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class DataStoreVM(application: Application) : AndroidViewModel(application) {

    private val dataStore = UsuarioDataStore(getApplication())

    private val _sesionActiva = MutableStateFlow<Boolean?>(null)
    val sesionActiva: StateFlow<Boolean?> = _sesionActiva.asStateFlow()

    init {
        viewModelScope.launch {
            _sesionActiva.value = dataStore.obtenerEstadoSesion().first() ?: false
        }
    }

    fun cerrarSesion() {
        viewModelScope.launch {
            dataStore.guardarEstadoSesion(false)

            _sesionActiva.value = false
        }
    }
}