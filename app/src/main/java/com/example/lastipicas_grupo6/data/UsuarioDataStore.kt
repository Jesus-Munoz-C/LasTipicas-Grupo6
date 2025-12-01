package com.example.lastipicas_grupo6.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.Pair
import androidx.datastore.preferences.core.stringPreferencesKey

val Context.dataStore by preferencesDataStore(name = "preferencias_usuario")

class UsuarioDataStore(private val context: Context) {

    private val SESION_ACTIVA = booleanPreferencesKey("sesion_activa")
    private val USUARIO_EMAIL = stringPreferencesKey("usuario_email")
    private val USUARIO_PASS = stringPreferencesKey("usuario_pass")

    private val USER_PHOTO_URI = stringPreferencesKey("user_photo_uri")
    private val USER_TOKEN = stringPreferencesKey("user_token")


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

    suspend fun guardarCredenciales(email: String, pass: String) {
        context.dataStore.edit { preferencias ->
            preferencias[USUARIO_EMAIL] = email
            preferencias[USUARIO_PASS] = pass
        }
    }

    fun obtenerCredenciales(): Flow<Pair<String?, String?>> {
        return context.dataStore.data.map { preferencias ->
            val email = preferencias[USUARIO_EMAIL]
            val pass = preferencias[USUARIO_PASS]
            Pair(email, pass)
        }
    }

    suspend fun guardarToken(token: String) {
        context.dataStore.edit { preferencias ->
            preferencias[USER_TOKEN] = token
        }
    }

    val obtenerToken: Flow<String?> = context.dataStore.data.map { preferencias ->
        preferencias[USER_TOKEN]
    }

    suspend fun vaciarDataStore() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    suspend fun guardarFoto(uri: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_PHOTO_URI] = uri
        }
    }

    val obtenerFoto: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[USER_PHOTO_URI]
    }
}

