package com.example.appviagens.telas

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FlightTakeoff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.appviagens.ScreenManager
import com.example.appviagens.component.AppBarTelas

@Composable
fun HomeCompose(nameUserLogged: String) {
    Scaffold(
        topBar = { AppBarTelas("Bem vindo", Icons.Rounded.FlightTakeoff) }
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(6.dp))
            Text(
                text = "Olá, $nameUserLogged",
                style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive)
            )
            Spacer(modifier = Modifier.padding(7.dp))
        }
    }
}

@Composable
fun HomeNavigation(nameUserLogged: String, idUserLogged: Int) {

    val navController = rememberNavController()
    val items = listOf(
        ScreenManager.Home,
        ScreenManager.Viagens,
        ScreenManager.Sobre
    )
    Scaffold(
        bottomBar = {
            androidx.compose.material.BottomNavigation(
                backgroundColor = MaterialTheme.colors.secondary,
//        contentColor = Color.Black
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                items.forEach { item ->
                    BottomNavigationItem(
                        icon = { Icon(item.icon, contentDescription = null) },
                        label = {
                            Text(
                                text = stringResource(item.resourceId),
                                fontSize = 12.sp
                            )
                        },
                        selectedContentColor = Color.Black,
                        unselectedContentColor = Color.Black.copy(0.4f),
                        alwaysShowLabel = true,
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                navController.graph.startDestinationRoute?.let { screen_route ->
                                    popUpTo(screen_route) {
                                        saveState = true
                                    }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ScreenManager.Home.route,
            Modifier.padding(innerPadding)
        ) {
            composable(ScreenManager.Home.route) { HomeCompose(nameUserLogged) }
            composable(ScreenManager.Viagens.route) { ViagensCompose(navController = navController, idUserLogged) }
            composable(ScreenManager.Sobre.route) { SobreCompose() }
            formViagemGrap(navController, idUserLogged)
            formDespesaGrap(navController, idUserLogged)
        }
    }
}


