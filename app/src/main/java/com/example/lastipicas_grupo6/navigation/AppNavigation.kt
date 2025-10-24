package com.example.lastipicas_grupo6.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lastipicas_grupo6.ui.screens.HomeScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lastipicas_grupo6.ui.screens.LoginScreen
import com.example.lastipicas_grupo6.ui.screens.RegPedidoScreen
import com.example.lastipicas_grupo6.viewmodel.LoginVM

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreen.HomeScreen.route
    ) {

        composable(route = AppScreen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }


        composable(route = AppScreen.LoginScreen.route) {

            val loginViewModel: LoginVM = viewModel()
            LoginScreen(
                navController = navController,
                viewModel = loginViewModel
            )
        }

        composable(route = AppScreen.RegistroScreen.route) {

            RegistroScreen(navController = navController)
        }
    }
}


@Composable
fun LoginScreen(navController: androidx.navigation.NavController) {
    androidx.compose.material3.Text("PANTALLA DE LOGIN")
}

@Composable
fun RegistroScreen(navController: androidx.navigation.NavController) {
    androidx.compose.material3.Text("PANTALLA DE WEONES/REGISTRO JASJDJASJD")
}