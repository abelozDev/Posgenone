package com.posgenone.app.core.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Role : Screen("role")
    object Pin : Screen("pin")
    object Dashboard : Screen("dashboard")
}