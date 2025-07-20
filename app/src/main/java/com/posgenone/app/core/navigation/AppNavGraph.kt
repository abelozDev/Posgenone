package com.posgenone.app.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.posgenone.app.feature.auth.RoleSelectionScreen
import com.posgenone.app.feature.auth.PinInputScreen
import com.posgenone.app.feature.dashboard.TableDashboardScreen
import com.posgenone.app.feature.splash.LoadingScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Role.route
    ) {
        composable(Screen.Role.route) {
            RoleSelectionScreen(
                onContinue = {
                    navController.navigate(Screen.Pin.route)
                }
            )
        }

        composable(Screen.Pin.route) {
            PinInputScreen(navController)
        }
        composable(Screen.Dashboard.route) {
            TableDashboardScreen()
        }

        composable("loading") {
            LoadingScreen(
                onFinished = {
                    navController.navigate("dashboard") {
                        popUpTo("pin") { inclusive = true }
                    }
                }
            )
        }
    }
}