package com.curso.designpatterns.creationalpatterns.builder

import org.assertj.core.api.Assertions
import org.junit.Test

/**
 * Builder es un patrón de diseño creacional que nos permite construir objetos complejos paso a paso.
 * El patrón nos permite producir distintos tipos y representaciones de un objeto empleando
 * el mismo código de construcción.
 *
 * RESULT
 * com.curso.designpatterns.creationalpatterns.builder.Component@74e28667
 * Some value
 * true
 */
class Component private constructor(builder: Builder) {
    var param1: String? = null
    var param2: Int? = null
    var param3: Boolean? = null

    class Builder {
        private var param1: String? = null
        private var param2: Int? = null
        private var param3: Boolean? = null

        fun setParam1(param1: String) = apply { this.param1 = param1 }
        fun setParam2(param2: Int) = apply { this.param2 = param2 }
        fun setParam3(param3: Boolean) = apply { this.param3 = param3 }
        fun build() = Component(this)

        fun getParam1() = param1
        fun getParam2() = param2
        fun getParam3() = param3
    }

    init {
        param1 = builder.getParam1()
        param2 = builder.getParam2()
        param3 = builder.getParam3()
    }
}

class ComponentTest {
    @Test
    fun builderTest() {
        val component = Component.Builder()
            .setParam1("Some value")
            .setParam3(true)
            .build()

        println(component)
        println(component.param1)
        println(component.param3)

        Assertions.assertThat(component.param1).isEqualTo("Some value")
        Assertions.assertThat(component.param3).isEqualTo(true)
        Assertions.assertThat(component.param2).isEqualTo(null)
    }
}
