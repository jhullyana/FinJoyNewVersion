package com.jhullyana.finjoyversion.ui.theme.screens.Util

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jhullyana.finjoyversion.ui.theme.CorDoTitulo
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinjoyTopBar(drawerState: DrawerState){

    val coroutineScope = rememberCoroutineScope()

    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                coroutineScope.launch {
                    drawerState.open()
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "=",
                    tint = Color.White,
                    modifier = Modifier.size(40.dp))

            }
        },
        title = {
            Text(text = "FinJoy", fontSize = 40.sp,
                color = Color.White,
                fontWeight = FontWeight(600)
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(CorDoTitulo)
    )



}