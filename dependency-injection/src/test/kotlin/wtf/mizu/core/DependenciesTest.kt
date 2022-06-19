package wtf.mizu.core

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class DependenciesTest {
    @Test
    fun assumeInjectable() {
        val text = "hello"
        text.assign(String::class.java)

        assertDoesNotThrow {
            inject<String>()
        }
    }
}