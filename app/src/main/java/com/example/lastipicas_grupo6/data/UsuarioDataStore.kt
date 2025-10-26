package com.example.lastipicas_grupo6.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "preferencias_usuario")

class UsuarioDataStore(private val context: Context) {

    private val SESION_ACTIVA = booleanPreferencesKey("sesion_activa")

    suspend fun guardarEstadoSesion(valor: Boolean) {
        context.dataStore.edit { preferencias ->
            preferencias[SESION_ACTIVA] = valor
        }
    }

    fun obtenerEstadoSesion(): Flow<Boolean?> {
        return context.dataStore.data.map { preferencias ->
            preferencias[SESION_ACTIVA]
        }
    }
}