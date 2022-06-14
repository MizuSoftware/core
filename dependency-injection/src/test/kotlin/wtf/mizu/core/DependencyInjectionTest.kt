package wtf.mizu.core

import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class DependencyInjectionTest {

    @Test
    fun assumeInjectable() {
        val text = "hello"
        text.assign(String::class.java)

        assertDoesNotThrow {
            val find = inject<String>()
        }
    }

}