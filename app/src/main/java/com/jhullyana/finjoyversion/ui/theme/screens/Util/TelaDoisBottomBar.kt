//package com.jhullyana.finjoyversion.ui.theme.screens.Util
//
//import androidx.compose.foundation.layout.size
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.CheckCircle
//import androidx.compose.material3.Icon
//import androidx.compose.material3.NavigationBar
//import androidx.compose.material3.NavigationBarItem
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import com.jhullyana.finjoyversion.R
//
//
//@Composable
//fun TelaDoisBottomBar (navController: NavController){
//    NavigationBar(containerColor = Color(0xFF4F7550)) {
//        NavigationBarItem(
//            selected = true,
//            onClick = {
//                navController.navigate(TelaDois.tela_boleto_route)
//            },
//            icon = {
//                Icon(
//                    imageVector = Icons.Default.CheckCircle,
//                    contentDescription = "A",
//                    modifier = Modifier.size(40.dp)
//                )
//            },
//            label = { Text(text = "Boletos") }
//        )
//        NavigationBarItem(
//            selected = false,
//            onClick = {
//                navController.navigate(TelaDois.tela_pagos_route)
//            }, icon = {
//                Icon(
//                    painter = painterResource(id = R.drawable.pdff),
//                    contentDescription = "B",
//                    modifier = Modifier.size(40.dp),
//                )
//            },
//            label = { Text(text = "Pagos") }
//        )
//        NavigationBarItem(
//            selected = false,
//            onClick = {
//                navController.navigate(TelaDois.tela_pendentes_route)
//            }, icon = {
//                Icon(painter = painterResource(id = R.drawable.graph),
//                    contentDescription = "C",
//                    modifier = Modifier.size(40.dp),
//                )
//
//            },
//            label = { Text(text = "Pendentes") }
//        )
//        NavigationBarItem(
//            selected = false,
//            onClick = {
//                navController.navigate(TelaDois.tela_graficos_route)
//            },
//            icon = {
//                Icon(painter = painterResource(id = R.drawable.graph),
//                    contentDescription = "D",
//                    modifier = Modifier.size(40.dp),
//                )
//            },
//            label = { Text(text = "Gráficos") }
//        )
//    }
//}