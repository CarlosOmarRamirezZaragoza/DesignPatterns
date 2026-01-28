package com.curso.designpatterns.structuralpatterns.bridge

import org.assertj.core.api.Assertions
import org.junit.Test

/**
 * Bridge es un patrón de diseño estructural que te permite dividir una clase grande, o un grupo
 * de clases estrechamente relacionadas, en dos jerarquías separadas (abstracción e implementación)
 * que pueden desarrollarse independientemente la una de la otra.
 *
 * Permite dividir una clase grande en un grupo de clases o interfaces,
 * RESULT
 * Tv com.curso.designpatterns.structuralpatterns.bridge.Tv@7ef82753 volume up: 1
 * Tv com.curso.designpatterns.structuralpatterns.bridge.Tv@7ef82753 volume up: 2
 * Tv com.curso.designpatterns.structuralpatterns.bridge.Tv@7ef82753 volume down: 1
 * Radio com.curso.designpatterns.structuralpatterns.bridge.Radio@6bbe85a8 volume up: 1
 * Radio com.curso.designpatterns.structuralpatterns.bridge.Radio@6bbe85a8 volume up: 2
 * Radio com.curso.designpatterns.structuralpatterns.bridge.Radio@6bbe85a8 volume up: 3
 * Radio com.curso.designpatterns.structuralpatterns.bridge.Radio@6bbe85a8 volume down: 2
 */

interface Device {
    var volume: Int
    fun getName(): String
}

class Radio : Device {
    override var volume = 0
    override fun getName() = "Radio $this"
}

class Tv : Device {
    override var volume = 0
    override fun getName() = "Tv $this"
}

interface Remote {
    fun volumeUp()
    fun volumeDown()
}

class BasicRemote(val device: Device) : Remote {
    override fun volumeUp() {
        device.volume++
        println("${device.getName()} volume up: ${device.volume}")
    }

    override fun volumeDown() {
        device.volume--
        println("${device.getName()} volume down: ${device.volume}")
    }
}

class BridgeTest {
    @Test
    fun testBridge() {
        val tv = Tv()
        val radio = Radio()

        val tvRemote = BasicRemote(tv)
        val radioRemote = BasicRemote(radio)

        tvRemote.volumeUp()
        tvRemote.volumeUp()
        tvRemote.volumeDown()

        radioRemote.volumeUp()
        radioRemote.volumeUp()
        radioRemote.volumeUp()
        radioRemote.volumeDown()

        Assertions.assertThat(tv.volume).isEqualTo(1)
        Assertions.assertThat(radio.volume).isEqualTo(2)
    }
}
