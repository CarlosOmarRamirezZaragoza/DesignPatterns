package com.curso.designpatterns.creationalpatterns.singleton

import org.assertj.core.api.Assertions
import org.junit.Test

/**
 * Singleton es un patrón de diseño creacional que nos permite asegurarnos de que una clase
 * tenga una única instancia, a la vez que proporciona un punto de acceso global a dicha instancia.
 *
 * RESULTADO
 * Start
 *Initializing: com.curso.designpatterns.creationalpatterns.singleton.NetworkDriver@58cbafc2
 *Network driver: com.curso.designpatterns.creationalpatterns.singleton.NetworkDriver@58cbafc2
 *Network driver: com.curso.designpatterns.creationalpatterns.singleton.NetworkDriver@58cbafc2
 */
// Al crear el objeto estamos creando un Singleton Y EN TODOS LOS LUGARES DONDE SE LLAME,
// SERA EL MISMO OBJETO
object NetworkDriver {
    init {
        println("Initializing: $this")
    }

    fun log(): NetworkDriver = apply { println("Network driver: $this") }
}

class SingletonTest {

    @Test
    fun testSingleton() {
        println("Start")
        val networkDriver1 = NetworkDriver.log()
        val networkDriver2 = NetworkDriver.log()
        Assertions.assertThat(networkDriver1).isSameAs(NetworkDriver)
        Assertions.assertThat(networkDriver2).isSameAs(NetworkDriver)
    }
}
