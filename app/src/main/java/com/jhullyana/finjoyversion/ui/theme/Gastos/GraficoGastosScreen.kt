package com.jhullyana.finjoyversion.ui.theme.Gastos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GraficoGastosScreen(viewModel: GastoViewModel, navController: NavController) {
    val gastos by viewModel.gastos.collectAsState()

    // Calcula os totais por categoria
    val gastosPorCategoria = gastos.groupBy { it.categoria }.mapValues { entry ->
        entry.value.sumOf { it.valorGasto }
    }

    val totalGasto = gastosPorCategoria.values.sum()
    val categoriasComPercentagem = gastosPorCategoria.mapValues { (categoria, total) ->
        (total / totalGasto * 100).toFloat()
    }

    // Configura as cores e os dados do gráfico
    val cores = listOf(
        Color(0xFFE57373), // Vermelho
        Color(0xFF81C784), // Verde
        Color(0xFF64B5F6), // Azul
        Color(0xFFFFD54F), // Amarelo
        Color(0xFF9575CD)  // Roxo
    )

    val dados = categoriasComPercentagem.entries.mapIndexed { index, entry ->
        PieEntry(entry.value, entry.key)
    }

    val dataset = PieDataSet(dados, "").apply {
        colors = cores.map { it.toArgb() }
        valueTextSize = 30f // Aumenta o tamanho da fonte da porcentagem para 30
        valueTextColor = Color.White.toArgb()
        valueFormatter = PercentFormatter() // Mostra apenas porcentagens
    }

    val data = PieData(dataset)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "FinJoy",
                        fontSize = 40.sp,
                        color = Color.White,
                        fontWeight = FontWeight(600)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF4F7550))
            )
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Gastos por Categoria",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4F7550)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Gráfico
                    AndroidView(
                        factory = { context ->
                            PieChart(context).apply {
                                this.data = data
                                setUsePercentValues(true)
                                description.isEnabled = false
                                legend.isEnabled = false // Remove a legenda padrão
                                setHoleColor(Color.Transparent.toArgb()) // Fundo transparente
                                setDrawEntryLabels(false) // Remove rótulos no gráfico
                                animateY(1000)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth() // Ocupa toda a largura disponível
                            .height(400.dp) // Aumenta a altura para 400.dp
                    )


                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            navController.navigate("relatorio")

                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4F7550))
                    ) {
                        Text(text = "Ver Relatório de Gastos", color = Color.White)
                    }


                    // Legenda personalizada
                    Column(modifier = Modifier.fillMaxWidth()) {
                        categoriasComPercentagem.entries.forEachIndexed { index, entry ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(vertical = 4.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(16.dp)
                                        .background(cores[index % cores.size], shape = CircleShape)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "${entry.key}: ${"%.1f".format(entry.value)}%",
                                    fontSize = 16.sp,
                                    color = Color(0xFF4F7550)
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}
