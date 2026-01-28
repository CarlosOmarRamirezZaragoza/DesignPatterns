package com.curso.designpatterns.behaviouralpatterns.chainofresponsibility

import org.assertj.core.api.Assertions
import org.junit.Test

/**
 * Chain of Responsibility es un patrón de diseño de comportamiento que te permite pasar solicitudes
 * a lo largo de una cadena de manejadores. Al recibir una solicitud, cada manejador decide si
 * la procesa o si la pasa al siguiente manejador de la cadena.
 * RESULT
 * Headers with authentication
 * Authorization: 123456
 * ContentType: json
 * Body: {"username" = "john"}
 * ----------------
 * Headers without authentication
 * ContentType: json
 * Body: {"username" = "john"}
 */
interface HandlerChain {
    fun addHeader(inputHeader: String): String
}

class AuthenticationHeader(val token: String?, var next: HandlerChain? = null) : HandlerChain {
    override fun addHeader(inputHeader: String) = "$inputHeader\nAuthorization: $token".let {
        next?.addHeader(it) ?: it
    }
}

class ContentTypeHeader(val contentType: String, var next: HandlerChain? = null) : HandlerChain {
    override fun addHeader(inputHeader: String) =
        "$inputHeader\nContentType: $contentType".let { next?.addHeader(it) ?: it }
}

class BodyPayLoadHeader(val body: String, var next: HandlerChain? = null) : HandlerChain {
    override fun addHeader(inputHeader: String) =
        "$inputHeader\n$body".let { next?.addHeader(it) ?: it }
}

class ChainOfResponsibilityTest {
    @Test
    fun testChainOfResponsibility() {
        val authenticationHeader = AuthenticationHeader("123456")
        val contentTypeHeader = ContentTypeHeader("json")
        val bodyPayLoadHeader = BodyPayLoadHeader("Body: {\"username\" = \"john\"}")
        authenticationHeader.next = contentTypeHeader
        contentTypeHeader.next = bodyPayLoadHeader

        val messageWithAuthentication =
            authenticationHeader.addHeader("Headers with authentication")
        println(messageWithAuthentication)

        println("----------------")

        val messageWithoutAuthentication =
            contentTypeHeader.addHeader("Headers without authentication")
        println(messageWithoutAuthentication)

        Assertions.assertThat(messageWithAuthentication).isEqualTo(
            """
                Headers with authentication
                Authorization: 123456
                ContentType: json
                Body: {"username" = "john"}
            """.trimIndent()
        )
        Assertions.assertThat(messageWithoutAuthentication).isEqualTo(
            """
                Headers without authentication
                ContentType: json
                Body: {"username" = "john"}
            """.trimIndent()
        )
    }
}
