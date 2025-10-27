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
import com.example.lastipicas_grupo6.model.Producto
import com.example.lastipicas_grupo6.ui.screens.ItemResumen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResumenScreen(
    navController: NavController,
    pedidoVM: PedidoVM
) {

    val uiState by pedidoVM.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Resumen de tu Pedido") })
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
                    horizontalArrangement = Arrangement.Center
                ) {

                    Button(
                        onClick = {
                            navController.navigate(AppScreen.ConfirmarPedidoScreen.route)
                        },
                        modifier = Modifier.fillMaxWidth(0.8f)
                    ) {
                        Text("CONFIRMAR PEDIDO (Total: $${uiState.totalPedido})")
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

            Text("Por favor, revisa tu pedido:", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))

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
