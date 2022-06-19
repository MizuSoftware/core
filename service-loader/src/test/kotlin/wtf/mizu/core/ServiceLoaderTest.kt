package wtf.mizu.core

import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class ServiceLoaderTest {

    @Test
    fun assumeInjectable() {
        val text = "hello"
        ServiceLoader.assign(String::class.java, text)
        val d by ServiceLoader.find(String::class.java)
        println(d)
    }

}