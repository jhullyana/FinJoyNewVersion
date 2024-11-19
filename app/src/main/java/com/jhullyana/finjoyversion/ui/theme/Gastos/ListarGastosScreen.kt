package com.jhullyana.finjoyversion.ui.theme.Gastos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jhullyana.finjoyversion.data.Gasto
import com.jhullyana.finjoyversion.ui.theme.screens.Util.FinjoyTopBar
import kotlinx.coroutines.launch

@Composable
fun ListarGastosScreen(
    viewModel: GastoViewModel,
    navController: NavController,
    drawerState: DrawerState
) {
    val coroutineScope = rememberCoroutineScope()
    val gastos by viewModel.gastos.collectAsState()

    Scaffold(
        topBar = { FinjoyTopBar(drawerState) },
        content = { padding ->

            // Box para garantir que o conteúdo ocupe todo o espaço da tela
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                // Lista de Gastos (LazyColumn)
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 100.dp) // Garantir espaço suficiente para os botões
                ) {
                    // Adiciona o título como o primeiro item
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "Meus Gastos",
                                fontSize = 30.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFF4F7550), // Cor agradável para o título
                                modifier = Modifier.align(Alignment.Center) // Centraliza horizontalmente
                            )
                        }
                    }

                    // Adiciona os itens da lista
                    items(gastos) { gasto ->
                        GastoCard(
                            gasto = gasto,
                            onEditClick = {
                                navController.navigate("editarGasto/${gasto.id}")
                            },
                            onDeleteClick = {
                                coroutineScope.launch {
                                    viewModel.excluir(gasto)
                                }
                            }
                        )
                    }
                }

                // Botões fixos na parte inferior
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.BottomCenter) // Alinha os botões na parte inferior
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp) // Espaço entre os botões
                    ) {
                        // Botão Novo Gasto
                        Button(
                            onClick = {
                                navController.navigate("incluirGastos")
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp),
                            shape = RoundedCornerShape(50.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4F7550))
                        ) {
                            Text(text = "Novo gasto", fontSize = 20.sp, color = Color.White)
                        }

                        // Botão Ver Gráfico
                        Button(
                            onClick = {
                                navController.navigate("graficoGastos")
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp),
                            shape = RoundedCornerShape(50.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4F7550))
                        ) {
                            Text(text = "Ver Gráfico", fontSize = 20.sp, color = Color.White)
                        }
                    }
                }
            }

        }
    )
}

@Composable
fun GastoCard(
    gasto: Gasto,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .background(Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = gasto.titulo,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Categoria: ${gasto.categoria}",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Text(
                    text = "R$ ${"%.2f".format(gasto.valorGasto)}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4F7550) // cor agradável para o valor
                )
            }

            // Botões de Ação (Editar e Excluir)
            Column(
                horizontalAlignment = Alignment.End
            ) {
                IconButton(onClick = onEditClick) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Editar")
                }
                IconButton(onClick = onDeleteClick) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Excluir")
                }
            }
        }
    }
}




//    Column(
//        modifier = Modifier.padding(
//            top =  90.dp,
//            start = 30.dp,
//            end = 30.dp,
//            bottom = 30.dp
//        )
 //   ) {
//        Spacer(modifier = Modifier.height(30.dp))
//        Text(text = "Lista de gastos",
//            fontSize = 30.sp,
//            fontWeight = FontWeight.ExtraBold)
//        Spacer(modifier = Modifier.height(10.dp))
//
//        for(gasto in gastos){
//            Row {
//                Text(text = gasto.titulo,
//                    fontSize = 30.sp)
//
//                Button(onClick = {
//                    //Navegação editar
//                    navController.navigate("editarGasto/${gasto.id}")
//                }) {
//                    Text(text = "Editar", fontSize = 25.sp)
//                }
//
//
//                Button(onClick = {
//                    coroutineScope.launch {
//                        viewModel.excluir(gasto)
//                    }
//                }) {
//                    Text(text = "Excluir", fontSize = 25.sp)
//                }
//            }
//
//        }
//
//        Button(onClick = {
//            navController.navigate("incluirGastos")
//        }) {
//            Text(text = "Novo gasto")
//        }
//    }
//
