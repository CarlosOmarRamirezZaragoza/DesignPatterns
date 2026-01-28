package com.curso.designpatterns.structuralpatterns.adapter

import org.assertj.core.api.Assertions
import org.junit.Test

/**
 * Adapter es un patrón de diseño estructural que permite la colaboración entre
 * objetos con interfaces incompatibles.
 *
 * RESULT Convierte de 2 Int a 2 float y de2 int a 2 String
 * Data Is Displayed: 2.0 - 2
 * Data Is Displayed: 3.0 - 7
 * Data Is Displayed: 4.0 - 24
 */

// 3rd party functionality
data class DisplayDataType(val index: Float, val data: String)

class DataDisplay {
    fun displayData(data: DisplayDataType) {
        println("Data Is Displayed: ${data.index} - ${data.data}")
    }
}

// -------------------------------
// Our Code
data class DatabaseData(val position: Int, val amount: Int)

class DatabaseDataGenerator {
    fun generateData(): List<DatabaseData> {
        val list = arrayListOf<DatabaseData>()
        list.add(DatabaseData(2, 2))
        list.add(DatabaseData(3, 7))
        list.add(DatabaseData(4, 24))
        return list
    }
}

interface DatabaseDataConverter {
    fun convertData(data: List<DatabaseData>): List<DisplayDataType>
}

class DataDisplayAdapter(val display: DataDisplay) : DatabaseDataConverter {
    override fun convertData(data: List<DatabaseData>): List<DisplayDataType> {
        val returnList = arrayListOf<DisplayDataType>()
        data.map {
            val ddt = DisplayDataType(it.position.toFloat(), it.amount.toString())
            display.displayData(ddt)
            returnList.add(ddt)
        }
        return returnList
    }
}

class AdapterTest {
    @Test
    fun adapterTest() {
        val generator = DatabaseDataGenerator()
        val generateData = generator.generateData()
        val adapter = DataDisplayAdapter(DataDisplay())
        val convertData = adapter.convertData(generateData)

        Assertions.assertThat(convertData.size).isEqualTo(3)
        Assertions.assertThat(convertData[1].index).isEqualTo(3f)
        Assertions.assertThat(convertData[1].data).isEqualTo("7")
    }
}
