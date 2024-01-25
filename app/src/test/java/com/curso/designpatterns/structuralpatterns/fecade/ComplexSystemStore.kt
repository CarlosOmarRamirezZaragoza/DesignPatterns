package com.curso.designpatterns.structuralpatterns.fecade

import org.assertj.core.api.Assertions
import org.junit.Test

/**
 * Facade es un patrón de diseño estructural que proporciona una interfaz simplificada a una
 * biblioteca, un framework o cualquier otro grupo complejo de clases.
 * RESULT
 * Reading data from the file /data/default.prefs
 * Storing cache data to file /data/default.prefs
 */
class ComplexSystemStore(private val filepath: String) {
    private val cache: HashMap<String, String>

    init {
        println("Reading data from the file $filepath")
        cache = HashMap()
        // Read properties from file and put to cache
    }

    fun store(key: String, value: String) {
        cache[key] = value
    }

    fun read(key: String) = cache[key] ?: ""

    fun commit() = println("Storing cache data to file $filepath")
}

data class User(val login: String)

// Fecade
class UserRepository {
    private val systemPreferences = ComplexSystemStore("/data/default.prefs")

    fun save(user: User) {
        systemPreferences.store("USER_KEY", user.login)
        systemPreferences.commit()
    }

    fun findFirst(): User = User(systemPreferences.read("USER_KEY"))
}

class FecadeTest {
    @Test
    fun testFecade() {
        val userRepo = UserRepository()
        val user = User("john")
        userRepo.save(user)
        val retrieveUser = userRepo.findFirst()

        Assertions.assertThat(retrieveUser.login).isEqualTo("john")
    }
}
