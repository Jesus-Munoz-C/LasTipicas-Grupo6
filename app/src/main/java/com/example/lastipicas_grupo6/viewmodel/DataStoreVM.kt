package com.example.lastipicas_grupo6.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.lastipicas_grupo6.data.UsuarioDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.SharingStarted

class DataStoreVM(application: Application) : AndroidViewModel(application) {

    private val dataStore = UsuarioDataStore(getApplication())

    private val _sesionActiva = MutableStateFlow<Boolean?>(null)
    val sesionActiva: StateFlow<Boolean?> = _sesionActiva.asStateFlow()

    init {
        viewModelScope.launch {
            _sesionActiva.value = dataStore.obtenerEstadoSesion().first() ?: false
        }
    }

    val fotoUsuario = dataStore.obtenerFoto
        .stateIn(viewModelScope, SharingStarted.Lazily, null)


    fun cerrarSesion() {
        viewModelScope.launch {
            viewModelScope.launch {

                dataStore.vaciarDataStore()

                _sesionActiva.value = false
            }
        }
    }
}