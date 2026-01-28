package com.curso.designpatterns.behaviouralpatterns.strategy

import org.junit.Test
import java.util.*

/**
 * Strategy es un patrón de diseño de comportamiento que te permite definir una familia de
 * algoritmos, colocar cada uno de ellos en una clase separada y hacer sus objetos intercambiables.
 * RESULT
 * lorem ipsum dolor sit amet
 * LOREM IPSUM DOLOR SIT AMET
 */

class Printer(private val stringFormatterStrategy: (String) -> String) {
    fun printString(message: String) {
        println(stringFormatterStrategy(message))
    }
}

val lowercaseFormatter = { it: String -> it.lowercase(Locale.getDefault()) }
val uppercaseFormatter = { it: String -> it.uppercase(Locale.getDefault()) }

class StrategyTest {
    @Test
    fun testStrategy() {
        val inputString = "LOREM ipsum Dolor sit amet"
        val lowercasePrinter = Printer(lowercaseFormatter)
        lowercasePrinter.printString(inputString)

        val uppercasePrinter = Printer(uppercaseFormatter)
        uppercasePrinter.printString(inputString)
    }
}
