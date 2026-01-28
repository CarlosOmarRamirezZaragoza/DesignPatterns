package com.curso.designpatterns.behaviouralpatterns.command

import org.junit.Test

/**
 * Command es un patrón de diseño de comportamiento que convierte una solicitud en un objeto
 * independiente que contiene toda la información sobre la solicitud. Esta transformación te permite
 * parametrizar los métodos con diferentes solicitudes, retrasar o poner en cola la ejecución de una
 * solicitud y soportar operaciones que no se pueden realizar.
 *
 * RESULT
 * Adding Order with id 1
 * Adding Order with id 2
 * Paying for order with id 2
 * Paying for order with id 1
 */
interface Command {
    fun execute()
}

class OrderAddCommand(private val id: Long) : Command {
    override fun execute() {
        println("Adding Order with id $id")
    }
}

class OrderPayCommand(private val id: Long) : Command {
    override fun execute() {
        println("Paying for order with id $id")
    }
}

class CommandProcessor {
    private val queue = arrayListOf<Command>()

    fun addToQueue(command: Command): CommandProcessor = apply { queue.add(command) }

    fun processCommand(): CommandProcessor = apply {
        queue.forEach { it.execute() }
        queue.clear()
    }
}

class CommandTest {
    @Test
    fun testCommand() {
        CommandProcessor()
            .addToQueue(OrderAddCommand(1L))
            .addToQueue(OrderAddCommand(2L))
            .addToQueue(OrderPayCommand(2L))
            .addToQueue(OrderPayCommand(1L))
            .processCommand()
    }
}
