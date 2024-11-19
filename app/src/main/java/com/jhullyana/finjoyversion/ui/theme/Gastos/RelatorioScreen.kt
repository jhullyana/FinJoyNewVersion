package com.jhullyana.finjoyversion.ui.theme.Gastos

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RelatorioScreen(
    viewModel: GastoViewModel,
    navController: NavController
) {
    val gastosPorCategoria = viewModel.calcularTotalPorCategoria()
    val cores = listOf(
        Color(0xFFE57373), // Vermelho
        Color(0xFF81C784), // Verde
        Color(0xFF64B5F6), // Azul
        Color(0xFFFFD54F), // Amarelo
        Color(0xFF9575CD)  // Roxo
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Relatório de Gastos", fontSize = 24.sp, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF4F7550))
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Gastos por Categoria",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4F7550)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Lista de gastos
                gastosPorCategoria.entries.forEachIndexed { index, entry ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .background(cores[index % cores.size].copy(alpha = 0.2f), shape = RoundedCornerShape(8.dp))
                            .padding(16.dp)
                            .clickable {
                                // Navega para a tela de detalhes da categoria
                                navController.navigate("relatorioDetalhado/${entry.key}")
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .background(cores[index % cores.size], shape = CircleShape)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = entry.key,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF4F7550)
                            )
                            Text(
                                text = "Total: R$ ${"%.2f".format(entry.value)}",
                                fontSize = 16.sp,
                                color = Color(0xFF4F7550)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        navController.popBackStack()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4F7550))
                ) {
                    Text(text = "Voltar", color = Color.White)
                }
            }
        }
    )
}

//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun RelatorioScreen(
//    viewModel: GastoViewModel,
//    navController: NavController
//) {
//    val gastosPorCategoria = viewModel.calcularTotalPorCategoria()
//    val cores = listOf(
//        Color(0xFFE57373), // Vermelho
//        Color(0xFF81C784), // Verde
//        Color(0xFF64B5F6), // Azul
//        Color(0xFFFFD54F), // Amarelo
//        Color(0xFF9575CD)  // Roxo
//    )
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "Relatório de Gastos", fontSize = 24.sp, color = Color.White) },
//                navigationIcon = {
//                    IconButton(onClick = { navController.popBackStack() }) {
//                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar", tint = Color.White)
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF4F7550))
//            )
//        },
//        content = { padding ->
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(padding)
//                    .padding(16.dp)
//            ) {
//                Text(
//                    text = "Gastos por Categoria",
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color(0xFF4F7550)
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                // Lista de gastos
//                gastosPorCategoria.entries.forEachIndexed { index, entry ->
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(vertical = 8.dp)
//                            .background(cores[index % cores.size].copy(alpha = 0.2f), shape = RoundedCornerShape(8.dp))
//                            .padding(16.dp),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Box(
//                            modifier = Modifier
//                                .size(16.dp)
//                                .background(cores[index % cores.size], shape = CircleShape)
//                        )
//                        Spacer(modifier = Modifier.width(8.dp))
//                        Column {
//                            Text(
//                                text = entry.key,
//                                fontSize = 18.sp,
//                                fontWeight = FontWeight.Bold,
//                                color = Color(0xFF4F7550)
//                            )
//                            Text(
//                                text = "Total: R$ ${"%.2f".format(entry.value)}",
//                                fontSize = 16.sp,
//                                color = Color(0xFF4F7550)
//                            )
//                        }
//                    }
//                }
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                Button(
//                    onClick = {
//                        // Voltar ou realizar outra ação
//                        navController.popBackStack()
//                    },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4F7550))
//                ) {
//                    Text(text = "Voltar", color = Color.White)
//                }
//            }
//        }
//    )
//}

