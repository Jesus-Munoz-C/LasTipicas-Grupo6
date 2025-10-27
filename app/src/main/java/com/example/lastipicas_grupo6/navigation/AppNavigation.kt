package com.example.lastipicas_grupo6.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lastipicas_grupo6.ui.screens.*
import com.example.lastipicas_grupo6.viewmodel.DataStoreVM
import com.example.lastipicas_grupo6.viewmodel.LoginVM
import com.example.lastipicas_grupo6.viewmodel.PedidoVM
import com.example.lastipicas_grupo6.viewmodel.RegistroVM

@Composable
fun AppNavigation() {
    val navController = rememberNavController()


    val registroVM : RegistroVM = viewModel()
    val DataStoreVM: DataStoreVM = viewModel()
    val pedidoVM: PedidoVM = viewModel()

    val sesionActiva = DataStoreVM.sesionActiva.collectAsState().value

    if (sesionActiva == null) {

    } else {
        val startDestination = if (sesionActiva == true) {
            AppScreen.MenuScreen.route
        } else {
            AppScreen.HomeScreen.route
        }

    NavHost(
        navController = navController,
        startDestination = AppScreen.HomeScreen.route
    ){


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


        composable(route = AppScreen.MenuScreen.route) {
            MenuScreen(
                navController = navController,
                pedidoVM = pedidoVM,
                mainVM = DataStoreVM
            )
        }

        composable(route = AppScreen.ResumenScreen.route) {
            ResumenScreen(
                navController = navController,
                pedidoVM = pedidoVM
            )
        }

        composable(route = AppScreen.PedidoScreen.route) {
            PedidoScreen(
                navController = navController,
                pedidoVM = pedidoVM
            )
        }

        composable(route = AppScreen.ConfirmarPedidoScreen.route) {
            ConfirmarPedidoScreen(
                navController = navController,
                pedidoVM = pedidoVM
            )
        }
    }
    }
}