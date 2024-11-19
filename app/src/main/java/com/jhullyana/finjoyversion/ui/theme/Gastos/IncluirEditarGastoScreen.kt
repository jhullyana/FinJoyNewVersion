package com.jhullyana.finjoyversion.ui.theme.Gastos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jhullyana.finjoyversion.data.Gasto
import kotlinx.coroutines.launch

//@Composable
//fun IncluirEditarGastoScreen(
//    gastoId: Int? = null,
//    viewModel: GastoViewModel,
//    navController: NavController
//){
//
//    var coroutineScope = rememberCoroutineScope()
//    //Dados novo gasto
//    var titulo by remember { mutableStateOf("")}
//    var descricao by remember { mutableStateOf("")}
//
//    var gasto: Gasto? by remember {mutableStateOf(null)}
//
//    var label = if (gastoId == null) "Novo gasto" else "Editar Gasto"
//
//
//    LaunchedEffect(gastoId) {
//        coroutineScope.launch {
//            if(gastoId != null){
//                gasto = viewModel.buscarPorId(gastoId)
//                gasto?.let {
//                    titulo = it.titulo
//                    descricao = it.descricao
//                }
//            }
//        }
//    }
//
//    Column(
//        modifier = Modifier.padding(
//            top =  90.dp,
//            start = 30.dp,
//            end = 30.dp,
//            bottom = 30.dp
//        )
//    ) {
//        Text(
//            text = label,
//            fontSize = 30.sp,
//            fontWeight = FontWeight.ExtraBold
//        )
//        Spacer(modifier = Modifier.height(10.dp))
//        OutlinedTextField(
//            value = titulo,
//            onValueChange = { titulo = it }
//        )
//        Spacer(modifier = Modifier.height(10.dp))
//        OutlinedTextField(
//            value = descricao,
//            onValueChange = { descricao = it }
//        )
//        Spacer(modifier = Modifier.height(10.dp))
//        Button(onClick = {
//            //Serve para disparar o segundo processo
//            coroutineScope.launch {
//                val gastoSalvar = Gasto(
//                    id = gastoId,
//                    titulo = titulo,
//                    descricao = descricao
//                )
//
//                viewModel.gravar(gastoSalvar)
//                navController.popBackStack()
//           }
//        }) {
//            Text(text = "Salvar", fontSize = 30.sp)
//        }
//    }
//
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncluirEditarGastoScreen(
    gastoId: Int? = null,
    viewModel: GastoViewModel,
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()

    // Estados do formulário
    var titulo by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var valorGasto by remember { mutableStateOf("") }
    var categoriaSelecionada by remember { mutableStateOf("") }
    var expandedCategoria by remember { mutableStateOf(false) }
    val categorias = listOf(
        "Alimentação", "Moradia", "Transporte", "Lazer e Entretenimento",
        "Saúde", "Educação", "Roupas e Acessórios", "Tecnologia",
        "Investimentos", "Outros"
    )

    var erroTitulo by remember { mutableStateOf(false) }
    var erroValor by remember { mutableStateOf(false) }

    // Carregando os dados do gasto para edição
    LaunchedEffect(gastoId) {
        if (gastoId != null) {
            val gasto = viewModel.buscarPorId(gastoId)
            gasto?.let {
                titulo = it.titulo
                descricao = it.descricao
                valorGasto = it.valorGasto.toString()
                categoriaSelecionada = it.categoria
            }
        }
    }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(text = if (gastoId == null) "Novo Gasto" else "Editar Gasto") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Voltar")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFF4F7550),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                // Campo Título
                OutlinedTextField(
                    value = titulo,
                    onValueChange = {
                        titulo = it
                        erroTitulo = it.isBlank()
                    },
                    label = { Text("Título") },
                    isError = erroTitulo,
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                if (erroTitulo) {
                    Text(
                        text = "O título é obrigatório!",
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Campo Descrição
                OutlinedTextField(
                    value = descricao,
                    onValueChange = { descricao = it },
                    label = { Text("Descrição (opcional)") },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 3
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Campo Valor Gasto
                OutlinedTextField(
                    value = valorGasto,
                    onValueChange = {
                        valorGasto = it
                        erroValor = it.toDoubleOrNull() == null
                    },
                    label = { Text("Valor Gasto") },
                    isError = erroValor,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                if (erroValor) {
                    Text(
                        text = "Digite um valor válido!",
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Seleção de Categoria
                ExposedDropdownMenuBox(
                    expanded = expandedCategoria,
                    onExpandedChange = { expandedCategoria = !expandedCategoria }
                ) {
                    OutlinedTextField(
                        value = categoriaSelecionada,
                        onValueChange = {},
                        label = { Text("Categoria") },
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCategoria)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = expandedCategoria,
                        onDismissRequest = { expandedCategoria = false }
                    ) {
                        categorias.forEach { categoria ->
                            DropdownMenuItem(
                                text = { Text(categoria) },
                                onClick = {
                                    categoriaSelecionada = categoria
                                    expandedCategoria = false
                                }
                            )
                        }
                    }
                }
            }

            // Botão Salvar
            Button(
                onClick = {
                    erroTitulo = titulo.isBlank()
                    erroValor = valorGasto.toDoubleOrNull() == null

                    if (!erroTitulo && !erroValor) {
                        coroutineScope.launch {
                            val gastoSalvar = Gasto(
                                id = gastoId,
                                titulo = titulo,
                                descricao = descricao,
                                valorGasto = valorGasto.toDoubleOrNull() ?: 0.0,
                                categoria = categoriaSelecionada
                            )
                            viewModel.gravar(gastoSalvar)
                            navController.popBackStack()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4F7550)),
                shape = RoundedCornerShape(50.dp)
            ) {
                Text(text = "Salvar", color = Color.White, fontSize = 18.sp)
            }


        }
    }
}



