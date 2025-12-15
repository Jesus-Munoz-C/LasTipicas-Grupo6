package com.example.lastipicas_grupo6.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import com.example.lastipicas_grupo6.R
import com.example.lastipicas_grupo6.navigation.AppScreen
import com.example.lastipicas_grupo6.viewmodel.PedidoVM

@Composable
fun ConfirmarPedidoScreen(
    navController: NavController,
    pedidoVM: PedidoVM
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components { add(GifDecoder.Factory()) }
        .build()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        AsyncImage(
            model = R.drawable.confirmarp,
            imageLoader = imageLoader,
            contentDescription = "Pedido Confirmado",
            modifier = Modifier.size(150.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "¡Estás a un paso!",
            style = MaterialTheme.typography.headlineSmall
        )
        Text("Confirma para enviar tu pedido a cocina.")

        Spacer(modifier = Modifier.height(32.dp))


        Button(
            onClick = {

                pedidoVM.confirmarCompra {
                    navController.navigate(AppScreen.MenuScreen.route) {
                        popUpTo(AppScreen.MenuScreen.route) { inclusive = true }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Finalizar Compra y Guardar")
        }
        // ---------------------------
    }
}