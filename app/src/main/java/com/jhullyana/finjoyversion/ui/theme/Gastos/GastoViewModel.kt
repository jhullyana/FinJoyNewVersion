package com.jhullyana.finjoyversion.ui.theme.Gastos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhullyana.finjoyversion.data.Gasto
import com.jhullyana.finjoyversion.data.IRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GastoViewModel(
    private val repository : IRepository
): ViewModel(){

    private val _gastos = MutableStateFlow<List<Gasto>>(emptyList())
    val gastos: StateFlow<List<Gasto>> get() = _gastos

    init {
        viewModelScope.launch {
            repository.listarGastos().collectLatest { listaDeGastos ->
                _gastos.value = listaDeGastos
            }
        }
    }

    fun excluir(gasto: Gasto) {
        viewModelScope.launch {
            repository.excluirGasto(gasto)
        }
    }

    suspend fun buscarPorId(gastoId: Int): Gasto? {
        return withContext(Dispatchers.IO){
            repository.buscarGastoPorId(gastoId)
        }
    }

    fun gravar(gasto: Gasto) {
        viewModelScope.launch {
            repository.gravarGasto(gasto)
        }
    }
}