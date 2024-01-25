package com.curso.designpatterns.structuralpatterns.proxy

import org.junit.Test

/**
 * Proxy es un patrón de diseño estructural que te permite proporcionar un sustituto o marcador de
 * posición para otro objeto. Un proxy controla el acceso al objeto original, permitiéndote hacer
 * algo antes o después de que la solicitud llegue al objeto original.
 * RESULTADO
 * ProxyImage: Displaying test.jpg
 * RealImage: Loading test.jpg
 * RealImage: Displaying test.jpg
 * ---------------
 * ProxyImage: Displaying test.jpg
 * RealImage: Displaying test.jpg
 */

interface Image {
    fun display()
}

class RealImage(private val fileName: String) : Image {
    override fun display() {
        println("RealImage: Displaying $fileName")
    }

    private fun loadFromDisk(fileName: String) {
        println("RealImage: Loading $fileName")
    }

    init {
        loadFromDisk(fileName)
    }
}

class ProxyImage(private val filename: String) : Image {
    private var realImage: RealImage? = null
    override fun display() {
        println("ProxyImage: Displaying $filename")
        if (realImage == null) {
            realImage = RealImage(filename)
        }
        realImage!!.display()
    }
}

class ProxyTest {
    @Test
    fun testProxy() {
        val image = ProxyImage("test.jpg")
        // load image from disk
        image.display()
        println("---------------")

        // load image from "cache"
        image.display()
    }
}
