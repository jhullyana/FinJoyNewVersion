package com.jhullyana.finjoyversion.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Gasto (
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var titulo: String,
    val descricao: String,
    val valorGasto: Double = 0.0,
    val categoria: String = "",
    val concluido: Boolean = false

){
    constructor() : this(null,"","", 0.0,"",false)
}