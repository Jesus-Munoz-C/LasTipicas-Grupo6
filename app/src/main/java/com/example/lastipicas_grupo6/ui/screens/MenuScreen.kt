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
import com.example.lastipicas_grupo6.model.Producto
import com.example.lastipicas_grupo6.navigation.AppScreen
import com.example.lastipicas_grupo6.viewmodel.DataStoreVM
import com.example.lastipicas_grupo6.viewmodel.PedidoVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    navController: NavController,
    pedidoVM: PedidoVM,
    mainVM: DataStoreVM
) {

    val uiState by pedidoVM.uiState.collectAsState()


    val listaProductos = pedidoVM.listaProductos

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Catálogo de Productos") })
        },
        bottomBar = {

            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Total: $${uiState.totalPedido}",
                        style = MaterialTheme.typography.titleLarge
                    )
                    val totalItems = uiState.productosEnCarrito.values.sum()

                    Button(onClick = {
                        navController.navigate(AppScreen.PedidoScreen.route)
                    }) {
                        Text("Ver Carrito ($totalItems)")
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                items(listaProductos) { producto ->

                    ProductoItem(
                        producto = producto,
                        onAgregar = { pedidoVM.agregarAlCarrito(producto) }
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))


            Button(
                onClick = {
                    mainVM.cerrarSesion()
                    navController.navigate(AppScreen.HomeScreen.route) {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Cerrar Sesión")
            }
        }
    }
}

@Composable
fun ProductoItem(
    producto: Producto,
    onAgregar: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(producto.nombre, style = MaterialTheme.typography.titleMedium)
                Text("$${producto.precio}", style = MaterialTheme.typography.bodyMedium)
            }
            Button(onClick = onAgregar) {
                Text("Agregar")
            }
        }
    }
}