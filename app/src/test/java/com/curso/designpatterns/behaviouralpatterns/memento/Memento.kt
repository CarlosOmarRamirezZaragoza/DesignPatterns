package com.curso.designpatterns.behaviouralpatterns.memento

import org.assertj.core.api.Assertions
import org.junit.Test

/**
 * Memento es un patrón de diseño de comportamiento que te permite guardar y restaurar el estado
 * previo de un objeto sin revelar los detalles de su implementación.
 * RESULT
 * Current state is Initial state
 * Current state is State 1
 * Current state is State 2
 * Current state is State 1
 * Current state is Initial state
 * Current state is State 2
 */
data class Memento(val state: String)

class Originator(var state: String) {
    fun createMemento() = Memento(state)
    fun restoreMemento(memento: Memento) {
        state = memento.state
    }
}

class CareTaker {
    private val mementoList = arrayListOf<Memento>()
    fun saveState(state: Memento) {
        mementoList.add(state)
    }

    fun restore(index: Int): Memento = mementoList[index]
}

class MementoTest {
    @Test
    fun testMemento() {
        val originator = Originator("Initial state")
        val careTaker = CareTaker()
        careTaker.saveState(originator.createMemento())
        println("Current state is ${originator.state}")

        originator.state = "State 1"
        careTaker.saveState(originator.createMemento())
        println("Current state is ${originator.state}")

        originator.state = "State 2"
        careTaker.saveState(originator.createMemento())
        println("Current state is ${originator.state}")

        Assertions.assertThat(originator.state).isEqualTo("State 2")

        originator.restoreMemento(careTaker.restore(1))
        println("Current state is ${originator.state}")
        Assertions.assertThat(originator.state).isEqualTo("State 1")

        originator.restoreMemento(careTaker.restore(0))
        println("Current state is ${originator.state}")
        Assertions.assertThat(originator.state).isEqualTo("Initial state")

        originator.restoreMemento(careTaker.restore(2))
        println("Current state is ${originator.state}")
        Assertions.assertThat(originator.state).isEqualTo("State 2")
    }
}
