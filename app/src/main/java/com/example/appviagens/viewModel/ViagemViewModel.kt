package com.example.appviagens.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appviagens.model.TipoViagem
import com.example.appviagens.model.Viagem
import com.example.appviagens.repository.ViagemRepository
import kotlinx.coroutines.launch

class ViagemViewModel(
    private val repository: ViagemRepository
) : ViewModel() {

    var id by mutableStateOf(0)
    var destino by mutableStateOf("")
    var dataPartida by mutableStateOf("")
    var dataChegada by mutableStateOf("")
    var orcamento by mutableStateOf(0.00)
    var tipoID by mutableStateOf(0) // lazer = 1 | negocios = 2
    var usuarioID by mutableStateOf(0)

    fun salvar() {
        val viagem = Viagem(id, destino, dataPartida, dataChegada, orcamento, tipoID, usuarioID)
        viewModelScope.launch {
            repository.save(viagem)
        }
    }

    fun allViagensByUser(userID: Int): LiveData<List<Viagem>> {
        return repository.allViagensByUser(userID)
    }

    fun findById(id: Int) {
        viewModelScope.launch {
            val v = repository.findById(id)
            destino = v.destino
            dataPartida = v.dataPartida
            dataChegada = v.dataChegada
            orcamento = v.orcamento
            tipoID = v.tipoID
        }
    }

    fun deleteByID(id: Int) {
        viewModelScope.launch {
            repository.deleteByID(id)
        }
    }

    fun getDestinoByViagem(idViagem : Int) : String {
        return repository.getDestinoByViagem(idViagem)
    }

//    fun insertTipos(){
//        val tipoviagem = TipoViagem("Lazer")
//        viewModelScope.launch {
//            repository.insertTipos(tipoviagem)
//        }
//    }


}