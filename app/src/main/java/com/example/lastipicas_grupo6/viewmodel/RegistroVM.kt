package com.example.lastipicas_grupo6.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.lastipicas_grupo6.data.UsuarioDataStore
import com.example.lastipicas_grupo6.model.RegistroUsuarioErrores
import com.example.lastipicas_grupo6.model.UsuarioRegistro
import com.example.lastipicas_grupo6.model.UsuarioUiState
import com.example.lastipicas_grupo6.repository.AuthRepository // IMPORTANTE
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegistroVM(application: Application) : AndroidViewModel(application) {

    private val repository = AuthRepository() // CAMBIO: Usamos el repo de Auth

    private val _uiState = MutableStateFlow(UsuarioUiState())
    val uiState: StateFlow<UsuarioUiState> = _uiState.asStateFlow()

    private val _fotoPerfil = MutableStateFlow<Uri?>(null)
    val fotoPerfil: StateFlow<Uri?> = _fotoPerfil.asStateFlow()

    fun limpiarFormulario() {
        _uiState.value = UsuarioUiState()
        _fotoPerfil.value = null
    }
    fun onNombreChange(valor: String){ _uiState.update{ it.copy(nombre = valor) } }
    fun onEmailChange(valor: String) { _uiState.update { it.copy(email = valor) } }
    fun onPassChange(valor: String) { _uiState.update { it.copy(pass = valor) } }
    fun onDirecccionChange(valor: String){ _uiState.update { it.copy(direccion = valor) } }
    fun onTelefonoChange(valor: String){ _uiState.update { it.copy(telefono = valor) } }
    fun onAceptaTerminos(valor: Boolean){ _uiState.update { it.copy(aceptaTerminos = valor) } }

    fun onFotoTomada(uri: Uri) {
        _fotoPerfil.value = uri
    }

    fun registrarse(onSuccess: () -> Unit) {
        val estado = _uiState.value

        val errores = RegistroUsuarioErrores(
            nombre = if (estado.nombre.isBlank()) "Ingresa un Nombre." else null,
            email = if (!estado.email.contains("@")) "Email inválido" else null,
            pass = if (estado.pass.length < 8) "Ingresa 8 Caracteres" else null,
            telefono = if (estado.telefono.length != 9 || !estado.telefono.all { it.isDigit() }) "Debe tener 9 números válidos"
            else null
        )

        val hayErrores = listOfNotNull(errores.nombre, errores.email, errores.pass, errores.telefono).isNotEmpty()
        _uiState.update { it.copy(errores = errores) }

        if (hayErrores || !estado.aceptaTerminos) return


        val nombreCompleto = estado.nombre.trim()
        val espacioIndex = nombreCompleto.lastIndexOf(' ')

        val nombreFinal = if (espacioIndex != -1) nombreCompleto.substring(0, espacioIndex) else nombreCompleto

        val apellidoFinal = if (espacioIndex != -1) nombreCompleto.substring(espacioIndex + 1) else "."

        val telefonoLong = try { estado.telefono.toLong() } catch (e: Exception) { 0L }

        val usuarioParaEnviar = UsuarioRegistro(
            nombre = nombreFinal,
            apellido = apellidoFinal,
            email = estado.email,
            telefono = telefonoLong,
            direccion = estado.direccion,
            pass = estado.pass
        )

        viewModelScope.launch {
            try {
                val respuesta = repository.registrarUsuario(usuarioParaEnviar)

                if (respuesta.isSuccessful) {

                    val fotoActual = _fotoPerfil.value
                    if (fotoActual != null) {

                        val dataStore = UsuarioDataStore(getApplication())
                        dataStore.guardarFoto(fotoActual.toString())
                    }
                    limpiarFormulario()
                    onSuccess()
                } else {
                    val errorBody = respuesta.errorBody()?.string() ?: "Error desconocido"
                    _uiState.update {
                        it.copy(errores = it.errores.copy(email = "Error del servidor: $errorBody"))
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(errores = it.errores.copy(nombre = "Error de red: ${e.message}"))
                }
            }
        }
    }
}