package com.curso.designpatterns.behaviouralpatterns.observer

import org.junit.Test
import java.io.File

/**
 * Observer es un patrón de diseño de comportamiento que te permite definir un mecanismo de
 * suscripción para notificar a varios objetos sobre cualquier evento que le suceda
 * al objeto que están observando.
 *
 * RESULT
 * Save to log path/to/log/file.txt: Someone has performed open operation the file test.txt
 * Email to test@test.com: Someone has performed open operation with the file test.txt
 * Email to test@test.com: Someone has performed save operation with the file test.txt
 */

class Editor {
    var events = EventManager("open", "save")

    private var file: File? = null
    fun openFile(filePath: String) {
        file = File(filePath)
        events.notify("open", file)
    }

    fun saveFile() {
        file?.let {
            events.notify("save", file)
        }
    }
}

class EventManager(vararg operations: String) {
    var listeners = hashMapOf<String, ArrayList<EventListener>>()

    init {
        for (operation in operations) {
            listeners[operation] = ArrayList()
        }
    }

    fun subscribe(eventType: String?, listener: EventListener) {
        val users = listeners.get(eventType)
        users?.add(listener)
    }

    fun unsubscribe(eventType: String?, listener: EventListener) {
        val users = listeners.get(eventType)
        users?.remove(listener)
    }

    fun notify(eventType: String?, file: File?) {
        val users = listeners.get(eventType)
        users?.let {
            for (listener in it) {
                listener.update(eventType, file)
            }
        }
    }
}

interface EventListener {
    fun update(eventType: String?, file: File?)
}

class EmailNotificationListener(private val email: String) : EventListener {
    override fun update(eventType: String?, file: File?) {
        println("Email to $email: Someone has performed $eventType operation with the file ${file?.name}")
    }
}

class LogOpenListener(var filename: String) : EventListener {
    override fun update(eventType: String?, file: File?) {
        println("Save to log $filename: Someone has performed $eventType operation the file ${file?.name}")
    }
}

class ObserverTest {

    @Test
    fun testObserver() {
        val editor = Editor()

        val logListener = LogOpenListener("path/to/log/file.txt")
        val emailListener = EmailNotificationListener("test@test.com")
        editor.events.subscribe("open", logListener)
        editor.events.subscribe("open", emailListener)
        editor.events.subscribe("save", emailListener)

        editor.openFile("test.txt")
        editor.saveFile()
    }
}
