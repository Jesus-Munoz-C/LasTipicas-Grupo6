package com.example.lastipicas_grupo6.ui.screens

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lastipicas_grupo6.model.Producto
import com.example.lastipicas_grupo6.navigation.AppScreen
import com.example.lastipicas_grupo6.viewmodel.PedidoVM
import com.example.lastipicas_grupo6.ui.screens.ItemResumen

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

            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Total: $${uiState.totalPedido}",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Button(
                        onClick = {
                            navController.navigate(AppScreen.ResumenScreen.route)
                        },

                        enabled = uiState.productosEnCarrito.isNotEmpty()
                    ) {
                        Text("Ir a Resumen")
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {


            if (uiState.productosEnCarrito.isEmpty()) {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Tu carrito está vacío.", style = MaterialTheme.typography.bodyLarge)
                }
            } else {

                val itemsDelCarrito = uiState.productosEnCarrito.entries.toList()

                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {


                    items(itemsDelCarrito) { (producto, cantidad) ->
                        ItemResumen(
                            producto = producto,
                            cantidad = cantidad
                        )
                    }
                }
            }
        }
    }
}


