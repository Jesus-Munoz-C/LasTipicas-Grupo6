// Archivo: ui/screens/PedidoScreen.kt
package com.example.lastipicas_grupo6.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lastipicas_grupo6.navigation.AppScreen
import com.example.lastipicas_grupo6.viewmodel.DataStoreVM

@Composable
fun PedidoScreen(
    navController: NavController,
    DataStoreVM: DataStoreVM = viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "PANTALLA DE PEDIDOS")

        Button(onClick = {
            DataStoreVM.cerrarSesion()

            navController.navigate(AppScreen.HomeScreen.route) {
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }) {
            Text("Cerrar Sesión")
        }
    }
}