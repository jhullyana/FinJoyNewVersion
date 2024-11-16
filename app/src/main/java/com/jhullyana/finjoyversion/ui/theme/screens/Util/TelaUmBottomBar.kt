package com.jhullyana.finjoyversion.ui.theme.screens.Util

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jhullyana.finjoyversion.R

@Composable
fun TelaUmBottomBar(navController: NavController) {
    NavigationBar(containerColor = Color(0xFF4F7550)) {
        NavigationBarItem(
            selected = true,
            onClick = {
                navController.navigate("adicionar") // Nova rota
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Adicionar",
                    modifier = Modifier.size(40.dp)
                )
            },
            label = { Text(text = "Gastos") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("exportar") // Nova rota
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.pdff),
                    contentDescription = "Exportar",
                    modifier = Modifier.size(40.dp),
                )
            },
            label = { Text(text = "Relatórios") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("grafico") // Nova rota
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.graph),
                    contentDescription = "Gráficos",
                    modifier = Modifier.size(40.dp),
                )
            },
            label = { Text(text = "Gráficos") }
        )
    }
}
