package com.curso.designpatterns.creationalpatterns.abstractfactory

import org.assertj.core.api.Assertions
import org.junit.Test

/**
 * Abstract Factory es un patrón de diseño creacional que nos permite producir familias de
 * objetos relacionados sin especificar sus clases concretas.
 *
 * RESULT
 * Created datasource com.curso.designpatterns.creationalpatterns.abstractfactory.DatabaseDataSource@55de24cc
 * VALUE Printed
 */
interface DataSource

class DatabaseDataSource : DataSource {
    fun printValue() = apply { println("VALUE Printed") }
}

class NetworkDataSource : DataSource

abstract class DataSourceFactory {
    abstract fun makeDataSource(): DataSource

    companion object {
        inline fun <reified T : DataSource> createFactory(): DataSourceFactory =
            when (T::class) {
                DatabaseDataSource::class -> DatabaseFactory()
                NetworkDataSource::class -> NetworkFactory()
                else -> throw IllegalArgumentException()
            }
    }
}

class NetworkFactory : DataSourceFactory() {
    override fun makeDataSource(): DataSource = NetworkDataSource()
}

class DatabaseFactory : DataSourceFactory() {
    override fun makeDataSource(): DataSource = DatabaseDataSource()
}

class AbstractFactoryTest {
    @Test
    fun afterTest() {
        val datasourceFactory = DataSourceFactory.createFactory<DatabaseDataSource>()
        val dataSource = datasourceFactory.makeDataSource()
        println("Created datasource $dataSource")
        val data = dataSource as DatabaseDataSource
        data.printValue()

        Assertions.assertThat(dataSource).isInstanceOf(DatabaseDataSource::class.java)
    }
}
