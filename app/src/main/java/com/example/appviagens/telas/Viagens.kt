package com.example.appviagens.telas

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.rounded.Flight
import androidx.compose.material.icons.rounded.Surfing
import androidx.compose.material.icons.rounded.Work
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.navigation
import com.example.appviagens.ScreenManager
import com.example.appviagens.component.AppBarTelas
import com.example.appviagens.ui.theme.Gainsoro
import com.example.appviagens.ui.theme.Red200
import com.example.appviagens.viewModel.ViagemData
import java.text.DecimalFormat

@Composable
fun ViagensCompose(navController: NavHostController) {
    Scaffold(
        topBar = { AppBarTelas("Suas viagens", Icons.Rounded.Flight) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {  navController.navigate("form/0") }) {
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
            Text(text = "Hora de viajar?", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive))
            Spacer(modifier = Modifier.padding(7.dp))
            ListaViagens(navController = navController)
        }

    }
}

fun NavGraphBuilder.formViagemGrap(navController: NavHostController) {
    navigation(startDestination = "principal", route = "profile") {
        composable("principal") { ViagensCompose(navController) }
        composable("form/{viagemID}",
            arguments = listOf(
                navArgument("viagemID") {
                    type = NavType.IntType
                }
            )
        ) {
            val id = it.arguments?.getInt ("viagemID")
           FormViagem(navController, id)
        }
    }
}

@Composable
fun ListaViagens(navController: NavHostController) {
    val viagens = listOf(
        ViagemData(1, "São Paulo", "20/05/2022", "20/05/2022", 5000.00,1, 1 ),
        ViagemData(2, "Rio de Janeiro", "10/05/2022", "20/05/2022", 5000.00,2, 2 ),
        ViagemData(3, "Santa Catarina", "11/05/2022", "20/05/2022", 5000.00,1, 1 ),
        ViagemData(4, "Brasilia", "15/05/2022", "20/05/2022", 5000.00,2, 2 ),
        ViagemData(5, "São Paulo", "16/05/2022", "20/05/2022", 5000.00,1, 1 ),
        ViagemData(6, "Agrolândia", "18/05/2022", "20/05/2022", 5000.00,2, 2 ),
        ViagemData(7, "Balneário Camboriu", "11/06/2022", "20/06/2022", 5000.00,1, 1 ),
        ViagemData(8, "Santos", "19/05/2022", "20/05/2022", 5000.00,2, 2 ),
        ViagemData(9, "Miami", "19/05/2022", "20/05/2022", 5000.00,1, 1 ),
    )
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ){
        items(items = viagens) {
                v -> ViagensView(navController = navController, v)
        }
    }
}

@Composable
fun ViagensView(navController: NavHostController, viagem: ViagemData) {
    val df = DecimalFormat("0.00")
    val context = LocalContext.current
    Card(
        elevation = 10.dp,
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .clickable {
                Toast
                    .makeText(
                        context,
                        "ID Viagem: ${viagem.id}",
                        Toast.LENGTH_SHORT
                    )
                    .show()
                navController.navigate("form/"+viagem.id)
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.padding(5.dp))

            if (viagem.tipoID == 1 ){
                Icon(
                    imageVector = Icons.Rounded.Surfing,
                    contentDescription = null,
                    tint = Gainsoro,
                    modifier = Modifier
                        .size(60.dp)
                        .padding(vertical = 5.dp)
                )
            } else
            {
                Icon(
                    imageVector = Icons.Rounded.Work,
                    contentDescription = null,
                    tint = Gainsoro,
                    modifier = Modifier
                        .size(60.dp)
                        .padding(vertical = 5.dp)
                )
            }
            Column(modifier = Modifier
                .padding(8.dp)
                .weight(1f)
            ) {
                Text(text = viagem.destino, style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.padding(3.dp))
                Text(
                    text = viagem.dataPartida + " - " + viagem.dataChegada,
                    style = MaterialTheme.typography.caption
                )
                Spacer(modifier = Modifier.padding(5.dp))
            }
            Text(text = "R$ ${df.format(viagem.orcamento)}",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(16.dp)

            )
        }
    }

}
