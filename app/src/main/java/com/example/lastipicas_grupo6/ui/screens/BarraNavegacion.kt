package com.example.lastipicas_grupo6.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.lastipicas_grupo6.navigation.AppScreen

@Composable
fun BarraNavegacion(
    navController: NavController,
    rutaActual: String?
) {
    NavigationBar {
        // Botón 1: Menú (Catálogo)
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Menú") },
            label = { Text("Menú") },
            selected = rutaActual == AppScreen.MenuScreen.route,
            onClick = {
                if (rutaActual != AppScreen.MenuScreen.route) {
                    navController.navigate(AppScreen.MenuScreen.route) {
                        // Esto evita que se acumulen pantallas si pulsas mucho
                        popUpTo(AppScreen.MenuScreen.route) { inclusive = true }
                    }
                }
            }
        )

        // Botón 2: Carrito (Pedido)
        NavigationBarItem(
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito") },
            label = { Text("Carrito") },
            selected = rutaActual == AppScreen.PedidoScreen.route,
            onClick = {
                if (rutaActual != AppScreen.PedidoScreen.route) {
                    navController.navigate(AppScreen.PedidoScreen.route)
                }
            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.List, contentDescription = "Historial") },
            label = { Text("Historial") },
            selected = rutaActual == AppScreen.HistorialScreen.route,
            onClick = {
                if (rutaActual != AppScreen.HistorialScreen.route) {
                    navController.navigate(AppScreen.HistorialScreen.route)
                }
            }
        )

    }
}