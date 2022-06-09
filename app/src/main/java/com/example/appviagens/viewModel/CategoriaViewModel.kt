package com.example.appviagens.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appviagens.model.CategoriaDespesa
import com.example.appviagens.repository.CategoriaDespesaRepository
import kotlinx.coroutines.launch

class CategoriaViewModel(
    private val repository: CategoriaDespesaRepository
) : ViewModel() {

    var id by mutableStateOf(0)
    var nome by mutableStateOf("")

    fun salvar() {
        val categoria = CategoriaDespesa(id, nome)
        viewModelScope.launch {
            repository.save(categoria)
        }
    }

}