package com.curso.designpatterns.structuralpatterns.composite

import org.assertj.core.api.Assertions
import org.junit.Test

/**
 * Composite es un patrón de diseño estructural que te permite componer objetos en estructuras
 * de árbol y trabajar con esas estructuras como si fueran objetos individuales.
 *
 * Componer objetos en estructura de arbol y trabajar con esa estructura
 * como si fueran objetos individuales
 * RESULT
 * PC price: 1425
 */
open class Equipment(
    open val price: Int,
    val name: String
)

open class Composite(name: String) : Equipment(0, name) {
    private val equipments = ArrayList<Equipment>()

    override val price: Int
        get() = equipments.sumOf { it.price }

    fun add(equipment: Equipment) = apply { equipments.add(equipment) }
}

/**       Composite
 *          /  \
 *  Equipment   Equipment
 */

class Computer : Composite("PC")
class Processor : Equipment(1000, "Processor")
class HardDrive : Equipment(250, "Hard Driver")

/**       Composite
 *          /  \
 *  Equipment   Equipment
 */
class Memory : Composite("Memory")
class ROM : Equipment(100, "Read Only Memory")
class RAM : Equipment(75, "Random Access Memory")

class CompositeTest {

    @Test
    fun testComposite() {
        val memory = Memory().add(ROM()).add(RAM())
        val pc = Computer().add(memory).add(Processor()).add(HardDrive())
        println("PC price: ${pc.price}")

        Assertions.assertThat(pc.name).isEqualTo("PC")
        Assertions.assertThat(pc.price).isEqualTo(1425)
    }
}
