package com.example.appviagens.model

import androidx.room.*

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Pessoa::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("usuarioID"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = TipoViagem::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("tipoID"),
            //onDelete = ForeignKey.CASCADE
        )]
)
data class Viagem(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val destino: String,
    val dataPartida: String,
    val dataChegada: String,
    val orcamento: Double,
    val tipoID: Int,
    val usuarioID: Int
) {

}

//data class PessoaViagem(
//    @Embedded val pessoa: Pessoa,
//    @Relation(
//        parentColumn = "id",
//        entityColumn = "usuarioID"
//    )
//    val viagens: List<Viagem>
//)