package com.example.lastipicas_grupo6.navigation

sealed class  AppScreen(val route: String) {
    data object HomeScreen : AppScreen("home_screen")
    data object LoginScreen : AppScreen("login_screen")
    data object RegistroScreen : AppScreen("registro_screen")
    data object ResumenScreen : AppScreen("resumen_screen")
    data object MenuScreen : AppScreen("menu_screen")
    data object PedidoScreen : AppScreen("pedido_screen")
    data object ConfirmarPedidoScreen : AppScreen("confirmar_pedido_screen")

}