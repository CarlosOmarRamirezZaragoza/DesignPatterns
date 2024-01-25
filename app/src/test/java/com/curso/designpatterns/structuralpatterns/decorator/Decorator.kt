package com.curso.designpatterns.structuralpatterns.decorator

import org.junit.Test

/**
 * Decorator es un patrón de diseño estructural que te permite añadir funcionalidades a objetos
 * colocando estos objetos dentro de objetos encapsuladores especiales que
 * contienen estas funcionalidades.
 *
 * Sobre escribe el comportamiento sin alterar el codigo
 * RESULT
 * Normal coffee machine: Making small coffee
 * -------------------
 * Enhanced coffee machine: Making large coffee
 * -------------------
 * Enhanced coffee machine: Making milk coffee
 * Normal coffee machine: Making small coffee
 * Enhanced coffee machine: Adding milk
 */
interface CoffeeMachine {
    fun makeSmallCoffee()
    fun makeLargeCoffee()
}

class NormalCoffeeMachine : CoffeeMachine {
    override fun makeSmallCoffee() {
        println("Normal coffee machine: Making small coffee")
    }

    override fun makeLargeCoffee() {
        println("Normal coffee machine: Making large coffee")
    }
}

// Decorator

class EnhancedCoffeeMachine(private val coffeeMachine: CoffeeMachine) :
    CoffeeMachine by coffeeMachine {

    // OverrideBehaviour
    override fun makeLargeCoffee() {
        println("Enhanced coffee machine: Making large coffee")
    }

    // Extending behaviour

    fun makeMilkCoffee() {
        println("Enhanced coffee machine: Making milk coffee")
        coffeeMachine.makeSmallCoffee()
        println("Enhanced coffee machine: Adding milk")
    }
}

class DecoratorTest {
    @Test
    fun testDecorator() {
        val normalMachine = NormalCoffeeMachine()
        val enhancedMachine = EnhancedCoffeeMachine(normalMachine)

        enhancedMachine.makeSmallCoffee()
        println("-------------------")
        enhancedMachine.makeLargeCoffee()
        println("-------------------")
        enhancedMachine.makeMilkCoffee()
    }
}
