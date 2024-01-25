package com.curso.designpatterns.creationalpatterns.lazy

import org.assertj.core.api.Assertions
import org.junit.Test

/** RESULT
 * AlertBox com.curso.designpatterns.creationalpatterns.lazy.AlertBox@75437611: Hello window
 * AlertBox com.curso.designpatterns.creationalpatterns.lazy.AlertBox@7dfb0c0f: Second window
 */
class AlertBox {
    var message: String? = null

    fun show() {
        println("AlertBox $this: $message")
    }
}

/**
 * Lazy solo permite val mientras que LATEINIT permite var, la gran diferencia es que si se te olvida
 * intanciar el elemento despues en lateinit la aplicacion va a tronar y usando LAZY te aseguras que
 * eso no pase.
 * Con lazy hasta que se usa se instancia y se dice el tipo de dato.
 * Con lateinit ya se especifica el tipo de dato pero aun no se instancia
 */
class Window {
    val box by lazy { AlertBox() }
    fun showMessage(message: String) {
        box.message = message
        box.show()
    }
}

class Window2 {
    lateinit var box: AlertBox
    fun showMessage(message: String) {
        box = AlertBox()
        box.message = message
        box.show()
    }
}

class WindowTest {
    @Test
    fun windowTest() {
        val window = Window()
        window.showMessage("Hello window")
        Assertions.assertThat(window.box).isNotNull

        val window2 = Window2()
        // si lo usamos antes de instanciarlo, va a tronar la app
        // println(window2.box)
        window2.showMessage("Second window")
        Assertions.assertThat(window2.box).isNotNull
    }
}
