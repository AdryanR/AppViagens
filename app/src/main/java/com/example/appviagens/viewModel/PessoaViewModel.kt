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

    var nome by mutableStateOf("")
    var login by mutableStateOf("")
    var senha by mutableStateOf("")

    fun salvar() {
        val pessoa = Pessoa(nome, login, senha)
        viewModelScope.launch {
            repository.save(pessoa)
        }
    }

//    fun login(login: String, senha: String) {
//        viewModelScope.launch {
//            repository.login(login, senha)
//        }
//    }
//
//      fun login(login: String, senha: String): Int? {
//        return repository.login(login, senha)
//    }

}