// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

// Las piezas composable, son las piezas interactivas
@Composable
@Preview
fun App() {
    // podemos definir los estados de nuestra aplicacion reactivos
    var text by remember { mutableStateOf("Hola, Compose Desktop") }
    var contador by remember { mutableStateOf(0) }

    // Le vamos a aplicar estilo Material
    MaterialTheme {
        Column(Modifier.fillMaxSize(), Arrangement.spacedBy(5.dp)) {
            Text(
                text = text,
                Modifier.align(Alignment.CenterHorizontally),
            )
            Text(
                text = "El contador es: $contador",
                Modifier.align(Alignment.CenterHorizontally),
            )

            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                // eventos
                onClick = {
                    contador++
                    text = "Hola, Compose Desktop DAM"
                }
            ) {
                // Texto del boton
                Text("Haz clic")
            }
        }
    }
}

// El main es el punto de entrada de la aplicación
fun main() = application {
    // Lanzamos la ventana de la aplicación
    // Con la pieza composable
    Window(
        onCloseRequest = ::exitApplication,
        title = "Hola Compose Desktop",
        // Vamos a decirle que recuerde el estado de la ventana
        state = rememberWindowState(width = 300.dp, height = 200.dp)
    ) {
        // App será nuestra Comkposable Pieza principal
        App()
    }
}
