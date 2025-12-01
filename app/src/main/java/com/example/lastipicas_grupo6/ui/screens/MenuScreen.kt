package com.example.lastipicas_grupo6.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
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
    val listaProductos by pedidoVM.listaProductos.collectAsState()

    // Obtenemos la foto guardada
    val fotoPerfilUri by mainVM.fotoUsuario.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Catálogo de Productos") },
                actions = {
                    // AQUÍ ESTÁ EL NUEVO COMPONENTE DE PERFIL
                    PerfilUsuarioTopBar(
                        fotoUri = fotoPerfilUri,
                        onCerrarSesion = {
                            pedidoVM.reiniciarPedido()
                            mainVM.cerrarSesion()
                            navController.navigate(AppScreen.HomeScreen.route) {
                                popUpTo(0)
                            }
                        }
                    )
                }
            )
        },
        bottomBar = {
            BarraNavegacion(navController, AppScreen.MenuScreen.route)
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
                    // AHORA SÍ FUNCIONARÁ PORQUE LA FUNCIÓN ESTÁ ABAJO 👇
                    ProductoItem(
                        producto = producto,
                        onAgregar = { pedidoVM.agregarAlCarrito(producto) }
                    )
                }
            }
        }
    }
}

// --- COMPONENTE: AVATAR CON MENÚ DESPLEGABLE ---
@Composable
fun PerfilUsuarioTopBar(
    fotoUri: String?,
    onCerrarSesion: () -> Unit
) {
    var menuExpandido by remember { mutableStateOf(false) }

    Box(contentAlignment = Alignment.Center) {
        // EL BOTÓN CIRCULAR (Avatar)
        IconButton(onClick = { menuExpandido = true }) {
            if (fotoUri != null) {
                // Si hay foto, la mostramos
                AsyncImage(
                    model = fotoUri,
                    contentDescription = "Perfil",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            } else {
                // Si no hay foto, mostramos un ícono de usuario genérico
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Perfil",
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.LightGray, CircleShape)
                        .padding(4.dp),
                    tint = Color.White
                )
            }
        }

        // EL MENÚ QUE SE DESPLIEGA AL TOCAR
        DropdownMenu(
            expanded = menuExpandido,
            onDismissRequest = { menuExpandido = false }
        ) {
            DropdownMenuItem(
                text = { Text("Cerrar Sesión") },
                onClick = {
                    menuExpandido = false
                    onCerrarSesion()
                }
            )
        }
    }
}

// --- COMPONENTE: ITEM DE PRODUCTO (ESTE ERA EL QUE FALTABA) ---
@Composable
fun ProductoItem(
    producto: Producto,
    onAgregar: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen del producto
            AsyncImage(
                model = producto.imagen,
                contentDescription = producto.nombre,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Textos (Nombre y Precio)
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(producto.nombre, style = MaterialTheme.typography.titleMedium)
                Text("$${producto.precio}", style = MaterialTheme.typography.bodyMedium)
            }

            // Botón Agregar
            Button(onClick = onAgregar) {
                Text("Agregar")
            }
        }
    }
}