package com.example.appviagens.component

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun PasswordField(value: String,
                  onChange: (String) -> Unit,
                  label: String = "Senha"
) {
    var passwordVisibility by remember{
        mutableStateOf(false)
    }
    TextField(
        value = value,
        onValueChange = { s -> onChange(s) },
        label = {
            Text(text = label)
        },
        visualTransformation = if (passwordVisibility)
            VisualTransformation.None
        else
            PasswordVisualTransformation(),
        trailingIcon = {
            val icone =
                if (passwordVisibility)
                    Icons.Filled.Visibility
                else
                    Icons.Filled.VisibilityOff

            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(imageVector = icone, contentDescription = "")
            }
        }
    )

}