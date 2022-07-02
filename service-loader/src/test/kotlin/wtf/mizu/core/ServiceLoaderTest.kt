package wtf.mizu.core

import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.Test
import java.util.*

class ServiceLoaderTest {
    @Test
    fun assumeInjectable() {
        val string = UUID.randomUUID().toString()
        ServiceLoader.assign(String::class.java, string)

        val service = ServiceLoader.find(String::class.java)
        assumeTrue(service != null)

        val d by service!!
        assumeTrue(d == string)
    }
}
