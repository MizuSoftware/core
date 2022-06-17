package wtf.mizu.core.configuration

import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.Test
import wtf.mizu.core.Container
import wtf.mizu.core.constraint.range
import wtf.mizu.core.setting

class ContainerTest {

    @Test
    fun addToContainerTest() {
        val container = Container("h", "d")
        val setting = container.setting("d", "e", false)
        val myValue by setting
        assumeTrue(!myValue)
    }

    @Test
    fun constraintTest() {
        val container = Container("h", "d")
        val setting = container.setting("d", "e", 5) {
            range = 0..10
        }
        var number by setting

        assumeTrue(number == 5)
        number = 250
        assumeTrue(number == 10)
    }

}