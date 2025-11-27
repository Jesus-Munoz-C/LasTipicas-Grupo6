package com.example.lastipicas_grupo6.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lastipicas_grupo6.model.Producto
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.shape.RoundedCornerShape
import coil.compose.AsyncImage


@Composable
fun ItemCarrito(
    producto: Producto,
    cantidad: Int,
    onAgregar: () -> Unit,
    onQuitar: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = producto.imagen,
                contentDescription = producto.nombre,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))


            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(producto.nombre, style = MaterialTheme.typography.titleMedium)
                Text("$${producto.precio * cantidad}", style = MaterialTheme.typography.bodyMedium)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(onClick = onQuitar) {
                    Icon(Icons.Default.RemoveCircle, contentDescription = "Quitar uno")
                }

                Text(text = "$cantidad", style = MaterialTheme.typography.titleMedium)

                IconButton(onClick = onAgregar) {
                    Icon(Icons.Default.AddCircle, contentDescription = "Agregar uno")
                }
            }
        }
    }
}