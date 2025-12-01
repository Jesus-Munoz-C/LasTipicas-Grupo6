package com.example.lastipicas_grupo6.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lastipicas_grupo6.navigation.AppScreen
import com.example.lastipicas_grupo6.viewmodel.PedidoVM
import com.example.lastipicas_grupo6.model.PedidoResponse
import com.example.lastipicas_grupo6.model.ItemHistorial
import androidx.compose.runtime.collectAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistorialScreen(
    navController: NavController,
    pedidoVM: PedidoVM
) {

    LaunchedEffect(Unit) {
        pedidoVM.cargarHistorial()
    }

    val historial by pedidoVM.historial.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Mis Pedidos") }) },
        bottomBar = {
            BarraNavegacion(navController, AppScreen.HistorialScreen.route)
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {

            if (historial.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No tienes pedidos registrados", color = Color.Gray)
                }
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(historial) { pedido ->
                        CardPedido(pedido)
                    }
                }
            }
        }
    }
}

@Composable
fun CardPedido(pedido: PedidoResponse) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Pedido #${pedido.id.takeLast(6).uppercase()}",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = pedido.fecha.take(10),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            pedido.productos.forEach { item ->
                FilaProductoHistorial(item)
            }

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            // --- TOTAL ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Total Pagado: $${pedido.total}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }
    }
}

@Composable
fun FilaProductoHistorial(item: ItemHistorial) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "${item.cantidad}x ${item.nombre}", fontWeight = FontWeight.SemiBold)
            Text(
                text = "${item.masa} / ${item.coccion}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
        Text(text = "$${item.precio}")
    }
}