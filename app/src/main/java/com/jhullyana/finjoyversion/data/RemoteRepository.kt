package com.jhullyana.finjoyversion.data

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class RemoteRepository() : IRepository {

    var db = FirebaseFirestore.getInstance()
    var gastoCollection = db.collection("gastos")


    //FUNÇÃO REATIVA
    override fun listarGastos(): Flow<List<Gasto>> = callbackFlow {
        val listener = gastoCollection.addSnapshotListener {
                dados, erros ->
            if (erros != null){
                close(erros)
                return@addSnapshotListener
            }
            if (dados != null){
                val gastos = dados.documents.mapNotNull { dado ->
                    dado.toObject(Gasto::class.java)
                }
                trySend(gastos).isSuccess
            }
        }
        awaitClose{ listener.remove()}
    }

    suspend fun getId(): Int{
        val dados = gastoCollection.get().await()
        //Recupera o maior id do firestore no format inteiro
        val maxId = dados.documents.mapNotNull {
            it.getLong("id")?.toInt()
        }.maxOrNull() ?: 0
        return maxId + 1
    }

    override suspend fun gravarGasto(gasto : Gasto) {
        val document: DocumentReference
        if (gasto.id == null){ // Inclusão
            gasto.id = getId()
            document = gastoCollection.document(gasto.id.toString())
        } else { // Alteração
            document = gastoCollection.document(gasto.id.toString())
        }
        document.set(gasto).await()
    }

    override suspend fun buscarGastoPorId(idx: Int): Gasto? {
        val dados = gastoCollection.document(idx.toString()).get().await()
//        val gasto = dados.toObject(Gasto::class.java)
//        return afazer
        return dados.toObject(Gasto::class.java)
    }

    override suspend fun excluirGasto(gasto: Gasto) {
        gastoCollection.document(gasto.id.toString()).delete().await()
    }
}