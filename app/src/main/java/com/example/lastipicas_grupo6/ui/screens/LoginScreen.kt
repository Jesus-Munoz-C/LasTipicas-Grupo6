package com.example.lastipicas_grupo6.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lastipicas_grupo6.navigation.AppScreen
import com.example.lastipicas_grupo6.ui.theme.LasTipicasGrupo6Theme
import com.example.lastipicas_grupo6.viewmodel.LoginVM

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginVM = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Iniciar Sesión",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = uiState.email,
            onValueChange = viewModel::onEmailChange,
            label = { Text("Correo Electrónico") },
            isError = uiState.erroresusuario.email != null,
            supportingText = {
                uiState.erroresusuario.email?.let {
                    Text(it, color = MaterialTheme.colorScheme.error )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = uiState.pass,
            onValueChange = viewModel::onPassChange,
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            isError = uiState.erroresusuario.pass != null,
            supportingText = {
                uiState.erroresusuario.pass?.let {
                    Text(it, color = MaterialTheme.colorScheme.error )
                }
            }
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (viewModel.validarUsuario()) {
                    navController.navigate(AppScreen.PedidoScreen.route)
                }
            },
            modifier = Modifier.fillMaxWidth()

        ) {
            Text("Entrar")
        }
        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = {
            navController.navigate(AppScreen.RegistroScreen.route)
        }) {
            Text("¿No tienes cuenta? Regístrate")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LasTipicasGrupo6Theme {
        LoginScreen(navController = rememberNavController())
    }
}