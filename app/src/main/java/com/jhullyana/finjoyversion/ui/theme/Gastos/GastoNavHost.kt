package com.jhullyana.finjoyversion.ui.theme.Gastos

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun GastoNavHost(
    viewModel: GastoViewModel,
    drawerState: DrawerState
){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "listarGastos"
    )
    {
        composable("listarGastos") {
            ListarGastosScreen(viewModel, navController, drawerState)
        }
        composable("incluirGastos") {
            IncluirEditarGastoScreen(viewModel = viewModel, navController = navController)
        }
        composable("editarGasto/{gastoId}") { navRequest ->
            val gastoId = navRequest.arguments?.getString("gastoId")
            IncluirEditarGastoScreen(
                gastoId?.toInt(), viewModel, navController
            )
        }
        composable("graficoGastos") {
            GraficoGastosScreen(viewModel = viewModel, navController)
        }
        composable("relatorio"){
            RelatorioScreen(viewModel, navController)
        }

        composable(
            "relatorioDetalhado/{categoria}",
            arguments = listOf(navArgument("categoria") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoria = backStackEntry.arguments?.getString("categoria")
            if (categoria != null) {
                RelatorioDetalhadoScreen(categoria = categoria, viewModel = viewModel, navController = navController)
            }
        }



    }
}
