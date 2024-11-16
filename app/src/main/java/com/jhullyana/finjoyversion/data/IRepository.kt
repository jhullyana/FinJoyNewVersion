package com.jhullyana.finjoyversion.data

import kotlinx.coroutines.flow.Flow

interface IRepository {

    fun listarGastos(): Flow<List<Gasto>>
    suspend fun buscarGastoPorId(idx: Int): Gasto?
    suspend fun gravarGasto(gasto: Gasto)
    suspend fun excluirGasto(gasto: Gasto)
}