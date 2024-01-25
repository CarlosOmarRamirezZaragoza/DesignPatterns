package com.curso.designpatterns.behaviouralpatterns.state

import org.assertj.core.api.Assertions
import org.junit.Test

/**
 * State es un patrón de diseño de comportamiento que permite a un objeto alterar su
 * comportamiento cuando su estado interno cambia. Parece como si el objeto cambiara su clase.
 * RESULT
 * Use admin is logged in: true
 * Use Unknown is logged in: false
 */

sealed class AuthorizationState

object Unauthorized : AuthorizationState()

class Authorized(val username: String) : AuthorizationState()

class AuthorizationPresenter {
    private var state: AuthorizationState = Unauthorized

    val isAuthorized: Boolean
        get() = when (state) {
            is Authorized -> true
            is Unauthorized -> false
        }

    val username: String
        get() {
            return when (val state = this.state) {
                is Authorized -> state.username
                is Unauthorized -> "Unknown"
            }
        }

    fun loginUser(username: String) {
        state = Authorized(username)
    }

    fun logoutUser() {
        state = Unauthorized
    }

    override fun toString() = "Use $username is logged in: $isAuthorized"
}

class StateTest {
    @Test
    fun testState() {
        val authorizationPresenter = AuthorizationPresenter()
        authorizationPresenter.loginUser("admin")
        println(authorizationPresenter)
        Assertions.assertThat(authorizationPresenter.isAuthorized).isEqualTo(true)
        Assertions.assertThat(authorizationPresenter.username).isEqualTo("admin")

        authorizationPresenter.logoutUser()
        println(authorizationPresenter)
        Assertions.assertThat(authorizationPresenter.isAuthorized).isEqualTo(false)
        Assertions.assertThat(authorizationPresenter.username).isEqualTo("Unknown")
    }
}
