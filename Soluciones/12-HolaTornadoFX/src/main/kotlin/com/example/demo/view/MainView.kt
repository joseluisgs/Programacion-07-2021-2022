package com.example.demo.view

import com.example.demo.app.Styles
import javafx.beans.property.SimpleIntegerProperty
import javafx.scene.control.Label
import javafx.scene.paint.Color
import tornadofx.*
import tornadofx.Stylesheet.Companion.label

/**
 * Esta es mi vista principal
 */
class MainView : View("Hola TornadoFX") {
    // Esta es una propiedad de tipo entero reactiva
    private val contador = SimpleIntegerProperty(0)
    // Esto es la etiqueta con la que se muestra el contado
    private var labelContador: Label by singleAssign()

    // Lo primero es meter el nodo root, es decir, el contenedor principal
    override val root = vbox {

        // Una etiqueta
        label(title) {
            addClass(Styles.heading)
        }
        // una etiqueta con una propiedad reactiva, por eso le indicamos que modelo es
        label("Contador: ${contador.value}") {
            labelContador = this
            addClass(Styles.heading)
            textFill = Color.BLUE
        }
        // Ponemos un boton
        button("Haz click aqui") {
            // Añadimos una clase
            action { println("Haz hecho clic :)")
                contador.value++
                labelContador.text = "Contador: ${contador.value}"
            }
            vboxConstraints {
                marginTop = 10.0
                marginBottom = 10.0
                marginRight = 10.0
                marginLeft = 10.0
            }
        }
    }
}