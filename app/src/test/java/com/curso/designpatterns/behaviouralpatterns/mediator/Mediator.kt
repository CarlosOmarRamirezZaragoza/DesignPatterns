package com.curso.designpatterns.behaviouralpatterns.mediator

import org.junit.Test

/**
 * Mediator es un patrón de diseño de comportamiento que te permite reducir las dependencias
 * caóticas entre objetos. El patrón restringe las comunicaciones directas entre los objetos,
 * forzándolos a colaborar únicamente a través de un objeto mediador.
 * RESULT
 * Carol: Sending message: Hi everyone!!
 * Alice: Received message: Hi everyone!!
 * Bob: Received message: Hi everyone!!
 */

class ChatUser(private val mediator: Mediator, private val name: String) {
    fun send(msg: String) {
        println("$name: Sending message: $msg")
        mediator.sendMessage(msg, this)
    }

    fun receive(msg: String) {
        println("$name: Received message: $msg")
    }
}

class Mediator {
    private val users = arrayListOf<ChatUser>()

    fun sendMessage(msg: String, user: ChatUser) {
        users.filter { it != user }.forEach { it.receive(msg) }
    }

    fun addUser(user: ChatUser): Mediator = apply { users.add(user) }
}

class MediatorTest {
    @Test
    fun testMediator() {
        val mediator = Mediator()
        val alice = ChatUser(mediator, "Alice")
        val bob = ChatUser(mediator, "Bob")
        val carol = ChatUser(mediator, "Carol")

        mediator.addUser(alice).addUser(bob).addUser(carol)
        carol.send("Hi everyone!!")
    }
}
