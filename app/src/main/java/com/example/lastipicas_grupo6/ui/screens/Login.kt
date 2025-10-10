package com.example.lastipicas_grupo6.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lastipicas_grupo6.ui.theme.LasTipicasGrupo6Theme // Asegúrate de que esta importación sea correcta

// 🎨 Definición del color principal (Naranja de Doña Marta)
// Un tono que se asemeja al color del logo y el botón en la imagen.
val DonaMartaOrange = Color(0xFFE8873F)

@Composable
fun LoginScreen() {
    // Definimos valores temporales para los campos, ya que no importan las validaciones.
    val dummyUsername = ""
    val dummyPassword = ""

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White // Fondo blanco
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp, vertical = 50.dp), // Padding general
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // 1. Logo/Título "Doña Marta"
            Text(
                text = "Doña Marta",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = DonaMartaOrange,
                modifier = Modifier.padding(bottom = 100.dp) // Gran espacio
            )

            // Contenedor para el texto y formulario (para alinear a la izquierda)
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                // 2. Título de Bienvenida
                Text(
                    text = "Bienvenido de nuevo",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // 3. Subtítulo de Instrucción
                Text(
                    text = "Inicia sesión para gestionar tus pedidos.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(bottom = 40.dp)
                )

                // 4. Campo de "Nombre de usuario o correo"
                OutlinedTextField(
                    value = dummyUsername,
                    onValueChange = { /* Ignoramos el cambio */ },
                    label = { Text("Nombre de usuario o correo") },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Usuario") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    // Estilos para imitar el borde sutil de la imagen
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.LightGray,
                        unfocusedBorderColor = Color.LightGray,
                        focusedLabelColor = Color.LightGray,
                        unfocusedLabelColor = Color.LightGray,
                        cursorColor = Color.DarkGray
                    )
                )

                // 5. Campo de "Contraseña"
                OutlinedTextField(
                    value = dummyPassword,
                    onValueChange = { /* Ignoramos el cambio */ },
                    label = { Text("Contraseña") },
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Contraseña") },
                    singleLine = true,
                    // Oculta el texto para simular una contraseña
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 30.dp),
                    // Estilos para imitar el borde sutil
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.LightGray,
                        unfocusedBorderColor = Color.LightGray,
                        focusedLabelColor = Color.LightGray,
                        unfocusedLabelColor = Color.LightGray,
                        cursorColor = Color.DarkGray
                    )
                )
            }

            // 6. Botón "Iniciar Sesión"
            Button(
                onClick = { /* Lógica ignorada */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                // Color del botón
                colors = ButtonDefaults.buttonColors(containerColor = DonaMartaOrange),
                shape = MaterialTheme.shapes.small // Esquinas ligeramente redondeadas
            ) {
                Text(
                    text = "Iniciar Sesión",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            // 7. Enlace "¿Olvidaste tu contraseña?"
            TextButton(
                onClick = { /* Lógica ignorada */ },
                modifier = Modifier.padding(top = 10.dp)
            ) {
                Text(
                    text = "¿Olvidaste tu contraseña?",
                    color = DonaMartaOrange,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )
            }

            // 8. Copyright al final
            Spacer(modifier = Modifier.weight(1f)) // Empuja el contenido hacia arriba

            Text(
                text = "© 2024 Doña Marta Empanadas. Todos los\nderechos reservados.",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    }
}

// ---

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LasTipicasGrupo6Theme {
        LoginScreen()
    }
}
