import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lastipicas_grupo6.viewmodel.RegistroVM

@Composable
fun ResumenScreen(viewModel: RegistroVM) {
    val estado by viewModel.uiState.collectAsState()

    Column(Modifier.padding(all = 16.dp)) {
        Text(text = "Resumen del Registro", style = MaterialTheme.typography.headlineMedium)
        Text(text = "Nombre: ${estado.nombre}")
        Text(text = "Correo: ${estado.email}")
        Text(text = "Contraseña: ${"*".repeat(n = estado.pass.length)}")
        Text(text = "Dirección: ${estado.direccion}")
        Text(text = "Telefono: ${estado.telefono}")
        Text(text = "Términos: ${if (estado.aceptaTerminos) "Aceptados" else "No aceptados"}")
    }
}