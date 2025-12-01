package com.example.lastipicas_grupo6.ui.screens

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.lastipicas_grupo6.navigation.AppScreen
import com.example.lastipicas_grupo6.viewmodel.RegistroVM
import java.io.File

@Composable
fun RegistroUsuarioScreen(
    navController: NavController,
    viewModel: RegistroVM
) {
    val uiState by viewModel.uiState.collectAsState()


    val fotoPerfil by viewModel.fotoPerfil.collectAsState()


    val context = LocalContext.current


    var tempPhotoUri by remember { mutableStateOf<Uri?>(null) }


    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->

        if (success && tempPhotoUri != null) {
            viewModel.onFotoTomada(tempPhotoUri!!)
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally // Centramos todo
    ) {

        Text("Crear Cuenta", style = MaterialTheme.typography.headlineMedium)


        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
                .clickable {

                    val photoFile = File.createTempFile(
                        "IMG_",
                        ".jpg",
                        context.getExternalFilesDir("my_images")
                    )
                    tempPhotoUri = FileProvider.getUriForFile(
                        context,
                        "com.example.lastipicas_grupo6.fileprovider",
                        photoFile
                    )

                    cameraLauncher.launch(tempPhotoUri!!)
                }
        ) {
            if (fotoPerfil != null) {

                AsyncImage(
                    model = fotoPerfil,
                    contentDescription = "Foto Perfil",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {

                Icon(
                    imageVector = Icons.Default.CameraAlt,
                    contentDescription = "Tomar Foto",
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
        Text("Toca el círculo para tomar una foto", style = MaterialTheme.typography.bodySmall)

        OutlinedTextField(
            value = uiState.nombre,
            onValueChange = viewModel::onNombreChange,
            label = { Text("Nombre") },
            isError = uiState.errores.nombre != null,
            supportingText = {
                uiState.errores.nombre?.let { Text(it) }
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = uiState.email,
            onValueChange = viewModel::onEmailChange,
            label = { Text("Correo Electrónico") },
            isError = uiState.errores.email != null,
            supportingText = {
                uiState.errores.email?.let { Text(it) }
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = uiState.pass,
            onValueChange = viewModel::onPassChange,
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            isError = uiState.errores.pass != null,
            supportingText = {
                uiState.errores.pass?.let { Text(it) }
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = uiState.direccion,
            onValueChange = viewModel::onDirecccionChange,
            label = { Text("Dirección") },
            isError = uiState.errores.direccion != null,
            supportingText = {
                uiState.errores.direccion?.let { Text(it) }
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = uiState.telefono,
            onValueChange = viewModel::onTelefonoChange,
            label = { Text("Teléfono") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = uiState.telefono,
            onValueChange = { input ->
                if (input.all { it.isDigit() } && input.length <= 9) {
                    viewModel.onTelefonoChange(input)
                }
            },
            label = { Text("Teléfono") },
            isError = uiState.errores.telefono != null,
            supportingText = {
                uiState.errores.telefono?.let { mensaje -> Text(mensaje) }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )


        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = uiState.aceptaTerminos,
                onCheckedChange = viewModel::onAceptaTerminos
            )
            Spacer(Modifier.width(8.dp))
            Text("Acepto los términos y condiciones")
        }


        Button(
            onClick = {
                viewModel.registrarse {

                    navController.navigate(AppScreen.LoginScreen.route)
                }
            },
            enabled = uiState.aceptaTerminos,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar")
        }
    }
}