package com.example.lastipicas_grupo6.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import com.example.lastipicas_grupo6.R
import com.example.lastipicas_grupo6.ui.theme.LasTipicasGrupo6Theme
import androidx.navigation.NavController
import com.example.lastipicas_grupo6.navigation.AppScreen

import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center)
         {
            //Saludo
            //Imagen (Logo)
            //(PARA REEMPLAZAR EL LOGO DEBE ESTAR EL ARCHIVO CON EL NOMBRE "LOGO" en el RES/DRAWABLE.)
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo App",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.None

            )
            Text(
                text = "Bienvenido a Las Típicas",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )

             Spacer(modifier = Modifier.height(8.dp))

             Text(
                 text = "Las mejores empanadas, a un toque de distancia.",
                 style = MaterialTheme.typography.titleMedium,
                 color = MaterialTheme.colorScheme.onSurfaceVariant,
                 textAlign = TextAlign.Center
             )

             Spacer(modifier = Modifier.height(32.dp))

            //Boton
             Button(
                 onClick = {
                     navController.navigate(AppScreen.LoginScreen.route)
                 },
                 modifier = Modifier
                     .fillMaxWidth()
                     .padding(horizontal = 16.dp)
             ) {
                 Text("Iniciar Sesión")
             }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    LasTipicasGrupo6Theme {
        HomeScreen(navController = rememberNavController())
    }
}
