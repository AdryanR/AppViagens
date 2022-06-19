package com.example.appviagens.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appviagens.model.CategoriaDespesa
import com.example.appviagens.repository.CategoriaDespesaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoriaViewModel(
    private val repository: CategoriaDespesaRepository
) : ViewModel() {

    var id by mutableStateOf(0)
    var nome by mutableStateOf("")

    private val _isIDInsert = MutableLiveData<Int>(0)
    val retornoInsert: LiveData<Int> // To observe the value outside the ViewModel class.
        get() = _isIDInsert

    fun salvar() {
        val categoria = CategoriaDespesa(id, nome)
        viewModelScope.launch {
            _isIDInsert.postValue(repository.save(categoria).toInt())
        }

    }

    fun findAll(): LiveData<List<CategoriaDespesa>> {
        return repository.findAll()
    }

    fun deleteByID(id: Int) {
        viewModelScope.launch {
            repository.deleteByID(id)
        }
    }
}