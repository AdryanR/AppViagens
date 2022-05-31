package com.example.appviagens.telas

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Flight
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appviagens.model.Viagem
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.rounded.CalendarToday
import androidx.compose.material.icons.rounded.Surfing
import androidx.compose.material.icons.rounded.Work
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavHostController
import com.example.appviagens.component.CustomTopAppBar
import com.example.appviagens.ui.theme.Gainsoro
import java.util.*


@Composable
fun FormViagem(navController: NavHostController, id: Int?) {
    Scaffold(
        topBar = {
            CustomTopAppBar(navController, "Nova Viagem", true)
        }

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val model: Viagem = viewModel()
            Spacer(modifier = Modifier.padding(6.dp))
            Text(
                text = "Nova Viagem",
                style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive)
            )
            Text(text = "Profile Form ${id}")
            Spacer(modifier = Modifier.padding(20.dp))
            OutlinedTextField(
                label = { Text(text = "Destino") },
                singleLine = true,
                value = model.destino,
                onValueChange = { model.destino = it },
                leadingIcon = {
                    Icon(
                        tint = Gainsoro,
                        imageVector = Icons.Rounded.Flight,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                var selectedOption by remember {
                    mutableStateOf(0)
                }
                var cor1 by remember {
                    mutableStateOf(Color.Unspecified)
                }
                var cor2 by remember {
                    mutableStateOf(Color.Unspecified)
                }
                if (selectedOption == 1) {
                    cor1 = Color.DarkGray
                    cor2 = Color.Unspecified
                } else if (selectedOption == 2) {
                    cor2 = Color.DarkGray
                    cor1 = Color.Unspecified
                }
                Button(
                    onClick = { selectedOption = 1 },
                    colors = ButtonDefaults.buttonColors(backgroundColor = cor1),
                    contentPadding = PaddingValues(
                        start = 20.dp,
                        top = 12.dp,
                        end = 20.dp,
                        bottom = 12.dp
                    )
                ) {
                    Icon(
                        Icons.Rounded.Surfing,
                        tint = Gainsoro,
                        contentDescription = "Lazer",
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text("Lazer", color = Color.DarkGray)
                }

                Button(
                    onClick = { selectedOption = 2 },
                    colors = ButtonDefaults.buttonColors(backgroundColor = cor2),
                    contentPadding = PaddingValues(
                        start = 20.dp,
                        top = 12.dp,
                        end = 20.dp,
                        bottom = 12.dp
                    )
                ) {
                    // Inner content including an icon and a text label
                    Icon(
                        Icons.Rounded.Work,
                        tint = Gainsoro,
                        contentDescription = "Negócios",
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text("Negócios", color = Color.DarkGray)
                }
            }

            Spacer(modifier = Modifier.padding(10.dp))
            DatePickerDemo(model, "Data de partida")
            Spacer(modifier = Modifier.padding(15.dp))
            DatePickerDemo(model, "Data de chegada")
            Spacer(modifier = Modifier.padding(15.dp))
            var orcamentoString by remember { mutableStateOf("") }
            OutlinedTextField(
                label = { Text(text = "Orçamento") },
                singleLine = true,
                value = orcamentoString,
                onValueChange = { orcamentoString = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                leadingIcon = {
                    Icon(
                        tint = Gainsoro,
                        imageVector = Icons.Rounded.Flight,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }
            )
//            model.orcamento = orcamentoString.toDouble() TEM QUE CONVERTER DEPOIS....
            Spacer(modifier = Modifier.padding(20.dp))
            Button(
                onClick = { navController.navigateUp() },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .width(350.dp)
                    .height(50.dp)
            ) {
                Text(text = "Adicionar")
            }
        }
    }
}

@Composable
fun DatePickerDemo(model: Viagem, label: String) {
    val mContext = LocalContext.current
    val mYear: Int
    val mMonth: Int
    val mDay: Int
    val mCalendar = Calendar.getInstance()
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
    mCalendar.time = Date()
    val mDate = remember { mutableStateOf("") }
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
        }, mYear, mMonth, mDay
    )
    Box(Modifier.clickable(
        onClick = {
            mDatePickerDialog.show()
        }
    )) {
        OutlinedTextField(
            value = mDate.value,
            onValueChange = {
                if (label.equals("Data de partida")) {
                    model.dataPartida
                } else {
                    model.dataChegada
                }
            },
            singleLine = true,
            enabled = false,
            label = { Text(text = label, color = Color.DarkGray) },
            modifier = Modifier
                .clickable {
                    mDatePickerDialog.show()
                },
            leadingIcon = {
                Icon(
                    tint = Gainsoro,
                    imageVector = Icons.Rounded.CalendarToday,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(16.dp)
                )
            }
        )
    }
}


