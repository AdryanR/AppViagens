package com.example.appviagens.telas

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.navigation
import com.example.appviagens.R
import com.example.appviagens.component.AppBarTelas
import com.example.appviagens.component.CircularProgressBarLoading
import com.example.appviagens.ui.theme.Gainsoro
import com.example.appviagens.model.Viagem
import com.example.appviagens.viewModel.PessoaViewModel
import com.example.appviagens.viewModel.PessoaViewModelFactory
import com.example.appviagens.viewModel.ViagemViewModel
import com.example.appviagens.viewModel.ViagemViewModelFactory
import java.text.DecimalFormat

@Composable
fun ViagensCompose(navController: NavHostController, idUserLogged: Int) {
    Scaffold(
        topBar = { AppBarTelas("Suas viagens", Icons.Rounded.Flight) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("form/0/$idUserLogged") }) {
                Icon(Icons.Filled.Add, contentDescription = "Nova Viagem")
            }
        },
        isFloatingActionButtonDocked = true,
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(6.dp))
            Text(
                text = "Hora de viajar?",
                style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive, textAlign = TextAlign.Center)
            )
            Spacer(modifier = Modifier.padding(7.dp))
            ListaViagens(navController = navController, idUserLogged)
        }

    }
}

fun NavGraphBuilder.formViagemGrap(navController: NavHostController, idUserLogged: Int) {
    navigation(startDestination = "principal", route = "viagens") {
        composable("principal") { ViagensCompose(navController, idUserLogged) }
        composable("form/{viagemID}/{UserID}",
            arguments = listOf(
                navArgument("viagemID") {
                    type = NavType.IntType
                },
                navArgument("UserID") {
                    type = NavType.IntType
                }
            )
        ) {
            val id = it.arguments?.getInt("viagemID")
            val idUser = it.arguments?.getInt("UserID")
            if (idUser != null) {
                FormViagemCompose(navController, id, idUser)
            }
        }
        composable("despesas/{viagemID}/{destinoViagem}",
            arguments = listOf(
                navArgument("viagemID") {
                    type = NavType.IntType
                },
                navArgument("destinoViagem") {
                    type = NavType.StringType
                }
            )
        ) {
            val id = it.arguments?.getInt("viagemID")
            val destino = it.arguments?.getString("destinoViagem")
            if (id != null) {
                if (destino != null) {
                    DespesasCompose(navController, id, destino)
                }
            }
        }
    }
}

@Composable
fun ListaViagens(navController: NavHostController, idUserLogged: Int) {
    val ctx = LocalContext.current
    val app = ctx.applicationContext as Application
    val model:
            ViagemViewModel = viewModel(
        factory = ViagemViewModelFactory(app)
    )

    // passando ID do user logado
    val viagens by model.allViagensByUser(idUserLogged).observeAsState(listOf())
    CircularProgressBarLoading()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(items = viagens) { v ->
            val custoViagem by model.somaDespesasByViagem(v.id).observeAsState(initial = 0.00)
            if (custoViagem > 0) {
                ViagensView(navController = navController, v, model, custoViagem)
            } else {
                ViagensView(navController = navController, v, model, 0.00)
            }
        }
    }
}

@Composable
fun ViagensView(
    navController: NavHostController,
    viagem: Viagem,
    model: ViagemViewModel,
    custoV: Double
) {
    val df = DecimalFormat("0.00")
    val context = LocalContext.current

    var aExcluir by remember {
        mutableStateOf(false)
    }
    if (aExcluir) {
        val openDialog = remember { mutableStateOf(true) }
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            text = {
                Text(
                    "Qual ação deseja?",
                    style = MaterialTheme.typography.h6,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        model.deleteByID(viagem.id)
                        Toast
                            .makeText(
                                context,
                                "Viagem apagada!",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                        aExcluir = false
                    }
                ) {
                    Text(
                        "Excluir viagem!", fontSize = 18.sp,
                        color = Color.White
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        aExcluir = false
                    }
                ) {
                    Text(
                        "Nada",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                TextButton(
                    onClick = {
                        openDialog.value = false
                        aExcluir = false
                        navController.navigate("form/" + viagem.id + "/" + viagem.usuarioID)
                    }
                ) {
                    Text(
                        "Editar viagem",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            backgroundColor = colorResource(id = R.color.status_bar),
            contentColor = Color.White
        )
    }
    Card(
        elevation = 10.dp,
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        navController.navigate("despesas/" + viagem.id + "/" + viagem.destino)
                    },
                    onLongPress = {
                        aExcluir = true;
                    },
                )
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.padding(5.dp))

            if (viagem.tipoID == 1) {
                Icon(
                    imageVector = Icons.Rounded.Surfing,
                    contentDescription = null,
                    tint = Gainsoro,
                    modifier = Modifier
                        .size(60.dp)
                        .padding(vertical = 5.dp)
                )
            } else {
                Icon(
                    imageVector = Icons.Rounded.Work,
                    contentDescription = null,
                    tint = Gainsoro,
                    modifier = Modifier
                        .size(60.dp)
                        .padding(vertical = 5.dp)
                )
            }
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            ) {
                Text(
                    text = viagem.destino,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.padding(3.dp))
                Text(
                    text = viagem.dataPartida + " — " + viagem.dataChegada,
                    fontSize = 14.sp
                    //style = MaterialTheme.typography.caption
                )
                Spacer(modifier = Modifier.padding(3.dp))
                if (custoV > 0) {
                    var cor by remember {
                        mutableStateOf(Color.White)
                    }
                    if (custoV > viagem.orcamento) {
                        cor = Color.Red
                    }
                    Text(
                        text = "R$ ${df.format(viagem.orcamento)}" + " — " + "R$ ${df.format(custoV)}",
                        fontSize = 14.sp,
                        color = cor
                        //style = MaterialTheme.typography.caption
//                    modifier = Modifier
//                        .align(Alignment.CenterVertically)
//                        .padding(16.dp)

                    )
                } else {
                    Text(
                        text = "R$ ${df.format(viagem.orcamento)}" + " — " + "R$ 00,00",
                        fontSize = 14.sp,
                        color = Color.White
                        //style = MaterialTheme.typography.caption
//                    modifier = Modifier
//                        .align(Alignment.CenterVertically)
//                        .padding(16.dp)

                    )
                }
                Spacer(modifier = Modifier.padding(5.dp))
            }
//            Icon(
//                imageVector = Icons.Rounded.FormatListBulleted,
//                contentDescription = null,
//                tint = Gainsoro,
//                modifier = Modifier
//                    .size(60.dp)
//                    .align(Alignment.CenterVertically)
//                   .padding(5.dp)
//            )
        }
    }

}

