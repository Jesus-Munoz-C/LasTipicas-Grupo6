package com.example.lastipicas_grupo6.ui.screens // Asegúrate de que el paquete sea correcto

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lastipicas_grupo6.R // Importa R para acceder a tus recursos (ej. R.drawable.logo)
import com.example.lastipicas_grupo6.ui.theme.LasTipicasGrupo6Theme // Cambia al nombre correcto de tu tema

/**
 * Función Composable principal: Define la estructura de la pantalla de inicio
 * con TopAppBar, texto, botón, y un logo.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    // Scaffold proporciona la estructura básica (Barra superior y cuerpo)
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lastipicas Grupo 6") } // Título adaptado a tu proyecto
            )
        }
    ) { innerPadding -> // innerPadding asegura que el contenido no se solape con el TopAppBar

        // Columna para organizar los elementos verticalmente
        Column(
            modifier = Modifier
                .padding(innerPadding) // Aplica el relleno del TopAppBar
                .fillMaxSize()
                .padding(16.dp), // Relleno adicional para el contenido
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp, alignment = Alignment.CenterVertically) // Espacio entre elementos y centrado vertical
        ) {
            // 1. Texto de bienvenida
            Text(
                text = "¡Bienvenido!",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary
            )

            // 2. Botón de ejemplo
            Button(onClick = {  }) {
                Text("Presióname")
            }

            // 3. Imagen (Logo)
            //(PARA REEMPLAZAR EL LOGO DEBE ESTAR EL ARCHIVO CON EL NOMBRE "LOGO" en el RES/DRAWABLE.)
            Image(
                painter = painterResource(id = R.drawable.logo), // Usando R.drawable.logo
                contentDescription = "Logo App",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}

// ---

/**
 * Función Composable para la vista previa en el IDE.
 */
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    LasTipicasGrupo6Theme {
        HomeScreen()
    }
}