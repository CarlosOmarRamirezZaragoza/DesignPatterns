package com.curso.designpatterns

import org.assertj.core.api.Assertions
import org.junit.Test

class Calculator {
    fun sum(a: Int, b: Int) = a + b
}

class CalculatorTest {
    @Test
    fun testSum() {
        val calc = Calculator()
        Assertions.assertThat(calc.sum(3, 5)).isEqualTo(8)
    }
}
