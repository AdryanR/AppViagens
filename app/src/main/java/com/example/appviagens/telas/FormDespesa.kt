package com.example.appviagens.telas

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.appviagens.ScreenManager
import com.example.appviagens.component.CircularProgressBarLoading
import com.example.appviagens.component.CustomTopAppBar
import com.example.appviagens.component.DatePickerDemo
import com.example.appviagens.ui.theme.Gainsoro
import com.example.appviagens.viewModel.CategoriaViewModel
import com.example.appviagens.viewModel.CategoriaViewModelFactory
import com.example.appviagens.viewModel.DespesaViewModel
import com.example.appviagens.viewModel.DespesaViewModelFactory
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FormDespesaCompose(navController: NavHostController, idViagem: Int?, idDespesa: Int?) {

    val ctx = LocalContext.current
    val app = ctx.applicationContext as Application
    var model:
            DespesaViewModel = viewModel(
        factory = DespesaViewModelFactory(app)
    )
    var modelC:
            CategoriaViewModel = viewModel(
        factory = CategoriaViewModelFactory(app)
    )
    if (idDespesa != null && idDespesa > 0) {
        model.id = idDespesa
        model.findById(idDespesa)
    }
    Scaffold(
        topBar = {
            if (idDespesa != null && idDespesa > 0) {
                CustomTopAppBar(navController, "Editar despesa", true)
            } else {
                CustomTopAppBar(navController, "Nova despesa", true)
            }
        }

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressBarLoading()
            Spacer(modifier = Modifier.padding(6.dp))
            if (idDespesa != null && idDespesa > 0) {
                Text(
                    text = "Editar Despesa",
                    style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive)
                )
            } else {
                Text(
                    text = "Nova Despesa",
                    style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive)
                )
            }
            Spacer(modifier = Modifier.padding(20.dp))

            val categorias by modelC.findAll().observeAsState(listOf())
            var exp by remember { mutableStateOf(false) }
            var selecionar by remember { mutableStateOf(true) }
            var selectedOption by remember { mutableStateOf("") }
            var selectedCategoriaID by remember { mutableStateOf(0) }
            val context = LocalContext.current

            ExposedDropdownMenuBox(expanded = exp, onExpandedChange = { exp = !exp }) {
                TextField(
                    value = selectedOption,
//                    modifier = Modifier
//                        .fillMaxWidth(),
                    onValueChange = { selectedOption = it },
                    label = { Text("Selecione ou digite a categoria") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = exp)
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )
                ExposedDropdownMenu(expanded = exp, onDismissRequest = { exp = false }) {
                    categorias.forEach { option ->
                        DropdownMenuItem(
                            onClick = {
                                selectedOption = option.nome
                                selectedCategoriaID = option.id
                                exp = false
                            }
                        ) {
                            Text(text = option.nome)
                        }
                    }
                }
            }
            if (idDespesa != null && idDespesa > 0 && selecionar) {
                categorias.forEach { option ->
                    Log.e("selectedCategoriaID", selectedCategoriaID.toString())
                    selectedCategoriaID = model.categoriaID
                    if (option.id == selectedCategoriaID) {
                        selectedOption = option.nome
                        selecionar = false
                    }
                }
            }
            if (selectedOption.equals("")) {
                selectedCategoriaID = 0
            }

            Spacer(modifier = Modifier.padding(10.dp))
            TextField(
                label = { Text(text = "Valor da despesa") },
                singleLine = true,
                value = model.valor?.toString(),
                onValueChange = {
                    try {
                        model.valor = it.toDouble()
                    } catch (e: Exception) {
                        Log.e("app", "Erro conversão de valor")
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                leadingIcon = {
                    Icon(
                        tint = Gainsoro,
                        imageVector = Icons.Rounded.AttachMoney,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }
            )
            Spacer(modifier = Modifier.padding(10.dp))
            model.data = DatePickerDemo("Data da despesa", model.data)
            Spacer(modifier = Modifier.padding(10.dp))
            TextField(
                label = { Text(text = "Descrição da despesa") },
                singleLine = true,
                value = model.descricao,
                onValueChange = { model.descricao = it },
                leadingIcon = {
                    Icon(
                        tint = Gainsoro,
                        imageVector = Icons.Rounded.Description,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }
            )
            Spacer(modifier = Modifier.padding(10.dp))
            TextField(
                label = { Text(text = "Local da despesa") },
                singleLine = true,
                value = model.local,
                onValueChange = { model.local = it },
                leadingIcon = {
                    Icon(
                        tint = Gainsoro,
                        imageVector = Icons.Rounded.Place,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }
            )
            Spacer(modifier = Modifier.padding(20.dp))

            if (idViagem != null) {
                model.viagemID = idViagem
            }

            var onSave by remember { mutableStateOf(false) }
            var catSalva by remember { mutableStateOf(true) }
            var msg by remember { mutableStateOf(true) }

            if (onSave) {
                if (msg) {
                    if (idDespesa != null && idDespesa > 0) {
                        Toast
                            .makeText(
                                context,
                                "Despesa editada com sucesso!",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                        msg = false
                    } else {
                        Toast
                            .makeText(
                                context,
                                "Despesa cadastrada a viagem com sucesso!",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }
                    msg = false
                }
                // salvando e obtendo o ID da nova categoria ou escolhendo uma
                if (selectedCategoriaID > 0) {
                    onSave = false
                    model.categoriaID = selectedCategoriaID
                    model.salvar()
                    navController.navigateUp()
                } else {
                    if (catSalva) {
                        modelC.nome = selectedOption
                        modelC.salvar()
                        catSalva = false
                    }
                    model.categoriaID = modelC.retornoInsert.observeAsState(initial = 0).value
                    if (model.categoriaID > 0) {
                        onSave = false
                        model.salvar()
                        navController.navigateUp()
                    }
                }
            }

            Button(
                onClick = {
                    onSave = true
                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .width(350.dp)
                    .height(50.dp)
            ) {
                if (idDespesa != null && idDespesa > 0) {
                    Text(text = "Editar despesa")
                } else {
                    Text(text = "Adicionar despesa a viagem")
                }
            }
        }
    }
}




