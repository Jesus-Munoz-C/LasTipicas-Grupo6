package com.example.lastipicas_grupo6.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lastipicas_grupo6.ui.screens.HomeScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lastipicas_grupo6.ui.screens.LoginScreen
import com.example.lastipicas_grupo6.ui.screens.RegistroUsuarioScreen
import com.example.lastipicas_grupo6.ui.screens.ResumenScreen
import com.example.lastipicas_grupo6.viewmodel.LoginVM
import com.example.lastipicas_grupo6.viewmodel.RegistroVM
import com.example.lastipicas_grupo6.ui.screens.PedidoScreen
import com.example.lastipicas_grupo6.viewmodel.DataStoreVM

@Composable
fun AppNavigation() {
    val navController = rememberNavController()


    val registroVM : RegistroVM = viewModel()
    val DataStoreVM: DataStoreVM = viewModel()

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
            RegistroUsuarioScreen(
                navController = navController,
                viewModel = registroVM
            )
        }

        composable(route = AppScreen.ResumenScreen.route) {
            ResumenScreen(
                viewModel = registroVM
            )
        }

        composable(route = AppScreen.PedidoScreen.route) {
            PedidoScreen(
                navController = navController,
                DataStoreVM = DataStoreVM
            )
        }
    }
}