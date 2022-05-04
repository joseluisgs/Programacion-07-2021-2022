package com.example.demo.app

import com.example.demo.view.MainView
import tornadofx.App

/**
 * Esta es mi clase principal, la cual es la que se encarga de iniciar la aplicación.
 * Con ella se inicia el view principal y le asocio un estilo
 * https://www.baeldung.com/kotlin/tornadofx-intro
 * https://medium.com/kotlin-thursdays/kotlin-tuesdays-introduction-to-tornadofx-part-1-34236eeb822a
 */
class MyApp: App(MainView::class, Styles::class)