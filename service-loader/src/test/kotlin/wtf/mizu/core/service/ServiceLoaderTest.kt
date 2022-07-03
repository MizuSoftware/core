package wtf.mizu.core.service

import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.Test
import java.util.*

/**
 * Tests the [ServiceLoader] object.
 *
 * @author Shyrogan
 * @author lambdagg
 * @since 0.0.1
 */
class ServiceLoaderTest {
    @Test
    fun assumeInjectable() {
        val string = UUID.randomUUID().toString()
        ServiceLoader[String::class] = string

        val service = ServiceLoader[String::class]

        val provided by service
        assumeTrue(provided == string)
    }
}
