package com.example.appviagens.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appviagens.model.TipoViagem
import com.example.appviagens.repository.TipoViagemRepository
import kotlinx.coroutines.launch

class TipoViagemViewModel(
    private val repository: TipoViagemRepository
) : ViewModel() {

    var id by mutableStateOf(0)
    var tipo by mutableStateOf("")

    fun salvar() {
        val tipoViagem = TipoViagem(id,tipo)
        viewModelScope.launch {
            repository.save(tipoViagem)
        }
    }
}