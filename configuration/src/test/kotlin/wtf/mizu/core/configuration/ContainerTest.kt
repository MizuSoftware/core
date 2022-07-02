package wtf.mizu.core.configuration

import org.junit.jupiter.api.Assumptions.*
import org.junit.jupiter.api.Test
import wtf.mizu.core.configuration.constraint.range

/**
 * Tests the [Container] class.
 *
 * @author Shyrogan
 * @since 0.0.1
 */
class ContainerTest {
    @Test
    fun addToContainerTest() {
        val container = Container("h", "d")
        val setting = container.setting("d", "e", false)

        val myValue by setting
        assumeFalse(myValue)
    }

    @Test
    fun constraintTest() {
        val container = Container("container")
        val setting = container.setting("setting", 5)
            .range(0..10)

        var number by setting

        assumeTrue(number == 5)
        number = 250
        assumeTrue(number == 10)
    }
}
