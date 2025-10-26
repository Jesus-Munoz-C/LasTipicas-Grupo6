import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lastipicas_grupo6.ui.screens.LoginScreen
import com.example.lastipicas_grupo6.ui.theme.LasTipicasGrupo6Theme
import com.example.lastipicas_grupo6.viewmodel.RegistroVM

@Composable
fun RegistroUsuarioScreen(
    navController: NavController,
    viewModel: RegistroVM
){
    val uiState by viewModel.uiState.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        //Nombre
        OutlinedTextField(
            value = uiState.nombre,
            onValueChange = viewModel::onNombreChange,
            label = { Text("Nombre") },
            isError = uiState.errores.nombre != null,
            supportingText = {
                uiState.errores.nombre?.let{
                    Text(it, color = MaterialTheme.colorScheme.error )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        //Correo
        OutlinedTextField(
            value = uiState.email,
            onValueChange = viewModel::onEmailChange,
            label = {Text("Correo Electronico")},
            isError = uiState.errores.email != null,
            supportingText = {
                uiState.errores.email?.let{
                    Text(it, color = MaterialTheme.colorScheme.error )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        //clave
        OutlinedTextField(
            value = uiState.pass,
            onValueChange = viewModel::onPassChange,
            label = {Text("Contraseña")},
            visualTransformation = PasswordVisualTransformation(),
            isError = uiState.errores.pass != null,
            supportingText = {
                uiState.errores.pass?.let{
                    Text(it, color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        //Direccion
        OutlinedTextField(
            value = uiState.direccion,
            onValueChange = viewModel::onDirecccionChange,
            label = { Text("Direccion")},
            isError = uiState.errores.direccion != null,
            supportingText = {
                uiState.errores.direccion?.let{
                Text(it, color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        //Telefono
        OutlinedTextField(
            value = uiState.telefono,
            onValueChange = viewModel::onTelefonoChange,
            label = {Text("Telefono")},
            isError = uiState.errores.telefono != null,
            supportingText = {
                uiState.errores.telefono?.let{
                    Text(it, color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        //checkbox: AceptarTerminos
        Row(verticalAlignment = Alignment.CenterVertically){
            Checkbox(
                checked = uiState.aceptaTerminos,
                onCheckedChange = viewModel::onAceptaTerminos
            )
            Spacer( Modifier.width(8.dp))
            Text("Acepto los terminos y condiciones")
        }

        //Boton Enviar
        Button(
            onClick = {
                if (viewModel.validarUsuario()) {
                    navController.navigate("Registro")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar")
        }
    }
}

