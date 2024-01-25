package com.curso.designpatterns.creationalpatterns.factorymethod

import org.assertj.core.api.Assertions
import org.junit.Test

/**
 * Factory Method es un patrón de diseño creacional que proporciona una interfaz para crear objetos
 * en una superclase, mientras permite a las subclases alterar el tipo de objetos que se crearán.
 *
 * RESULTADO
 * Greek currency: EUR
 * USA currency: USD
 */
sealed class Country {
    object Canada : Country()
}

object Spain : Country()
class Greece(val someProperty: String) : Country()
data class USA(val someProperty: String) : Country()
// class Poland : Country()

class Currency(val code: String)

object CurrencyFactory {
    fun currencyForCountry(country: Country): Currency = when (country) {
        is Spain -> Currency("EUR")
        is Greece -> Currency("EUR")
        is USA -> Currency("USD")
        is Country.Canada -> Currency("CAD")
    }
}

class FactoryMethodTest {
    @Test
    fun currencyTest() {
        val greekCurrency = CurrencyFactory.currencyForCountry(Greece("")).code
        println("Greek currency: $greekCurrency")

        val usaCurrency = CurrencyFactory.currencyForCountry(USA("")).code
        println("USA currency: $usaCurrency")

        Assertions.assertThat(greekCurrency).isEqualTo("EUR")
        Assertions.assertThat(usaCurrency).isEqualTo("USD")
    }
}
