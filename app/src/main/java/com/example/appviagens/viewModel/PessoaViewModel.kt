package com.example.appviagens.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appviagens.model.Pessoa
import com.example.appviagens.repository.PessoaRepository
import kotlinx.coroutines.launch

class PessoaViewModel(
    private val repository: PessoaRepository
) : ViewModel() {

    var id by mutableStateOf(0)
    var nome by mutableStateOf("")
    var login by mutableStateOf("")
    var senha by mutableStateOf("")

    fun salvar() {
        val pessoa = Pessoa(id, nome, login, senha)
        viewModelScope.launch {
            repository.save(pessoa)
        }
    }

    fun login(onSucess: (Pessoa) -> Unit, onNotFound: () -> Unit) {
        viewModelScope.launch {
            val pessoa = repository.login(login, senha)
            if (pessoa != null && pessoa.id != 0) {
                onSucess(pessoa)
            } else {
                onNotFound()
            }
        }
    }

}