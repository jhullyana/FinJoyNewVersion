package com.jhullyana.finjoyversion.data

import kotlinx.coroutines.flow.Flow

class LocalRepository(
    private val dao: GastoDao
) : IRepository {

    override fun listarGastos(): Flow<List<Gasto>> {
        return dao.listargastos()
    }

    override suspend fun buscarGastoPorId(idx: Int): Gasto {
        return dao.buscarGastoPorId(idx)
    }

    override suspend fun gravarGasto(gasto: Gasto) {
        dao.excluirGasto(gasto)
    }

    override suspend fun excluirGasto(gasto: Gasto) {
        dao.excluirGasto(gasto)
    }

}