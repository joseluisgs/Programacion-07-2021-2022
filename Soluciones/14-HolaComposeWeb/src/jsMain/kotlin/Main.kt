import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable

fun main() {
    var text: String  by mutableStateOf("Hola, Compose Desktop")
    var contador: Int by mutableStateOf(0)

    renderComposable(rootElementId = "root") {
        Div({ style { padding(15.px) } }) {
            Text(text)
        }

        Div({ style { padding(15.px) } }) {
            Text("El contador es: $contador")
        }

        Div({ style { padding(15.px) } }) {
            Button(attrs = {
                onClick {
                    contador ++
                    text = "Hola, Compose Desktop DAM"
                }
            }) {
                Text("Haz click aquí")
            }
        }
    }
}

