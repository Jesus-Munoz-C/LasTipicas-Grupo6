package com.example.lastipicas_grupo6.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lastipicas_grupo6.navigation.AppScreen
import com.example.lastipicas_grupo6.viewmodel.PedidoVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PedidoScreen(
    navController: NavController,
    pedidoVM: PedidoVM
) {

    val uiState by pedidoVM.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Mi Carrito de Compras") })
        },
        bottomBar = {
            // Usamos tu barra de navegación personalizada
            BarraNavegacion(
                navController = navController,
                rutaActual = AppScreen.PedidoScreen.route
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {

            // Mostrar Total arriba
            Text(
                text = "Total a Pagar: $${uiState.totalPedido}",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Verificamos si hay productos para mostrar la lista o un mensaje de vacío
            if (uiState.productosEnCarrito.isEmpty()) {
                Box(
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Tu carrito está vacío", style = MaterialTheme.typography.bodyLarge)
                }
            } else {
                // AQUÍ ESTABA EL ERROR: Ahora llenamos el contenido
                LazyColumn(
                    modifier = Modifier
                        .weight(1f) // Esto hace que la lista ocupe el espacio sobrante
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Convertimos el mapa a una lista de pares para poder recorrerla
                    val itemsDelCarrito = uiState.productosEnCarrito.toList()

                    items(itemsDelCarrito) { (producto, cantidad) ->
                        ItemCarrito(
                            producto = producto,
                            cantidad = cantidad,
                            onAgregar = { pedidoVM.agregarAlCarrito(producto) },
                            onQuitar = { pedidoVM.quitarDelCarrito(producto) }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón de Pagar
            Button(
                onClick = { navController.navigate(AppScreen.ResumenScreen.route) },
                modifier = Modifier.fillMaxWidth(),
                enabled = uiState.productosEnCarrito.isNotEmpty()
            ) {
                Text("Ir a Pagar")
            }
        }
    }
}