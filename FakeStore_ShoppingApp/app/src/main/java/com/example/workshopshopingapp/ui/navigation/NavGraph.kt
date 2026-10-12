package com.example.workshopshopingapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.workshopshopingapp.ui.screens.ProductDetailScreen
import com.example.workshopshopingapp.ui.screens.ProductListScreen
import com.example.workshopshopingapp.ui.viewmodel.ProductViewModel

sealed class Screen(val route: String) {
    object ProductList : Screen("products")
    object ProductDetail : Screen("product_detail/{productId}") {
        fun createRoute(productId: Int) = "product_detail/$productId"
    }
}

/**
 * Main Navigation Graph defining screens and transitions.
 */
@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    viewModel: ProductViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.ProductList.route
    ) {
        // Products List Screen
        composable(route = Screen.ProductList.route) {
            ProductListScreen(
                viewModel = viewModel,
                onProductClick = { productId ->
                    navController.navigate(Screen.ProductDetail.createRoute(productId))
                }
            )
        }

        // Product Detail Screen
        composable(
            route = Screen.ProductDetail.route,
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: 1
            ProductDetailScreen(
                productId = productId,
                viewModel = viewModel,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
