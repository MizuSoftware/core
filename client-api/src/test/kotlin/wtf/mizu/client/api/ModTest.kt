package wtf.mizu.client.api

import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.Test
import wtf.mizu.client.api.mod.Mod
import wtf.mizu.core.configuration.setting
import wtf.mizu.kawa.api.Subscription
import wtf.mizu.kawa.dsl.on

/**
 * Tests the [Mod] abstract class.
 *
 * @author Shyrogan
 * @author lambdagg
 * @since 0.0.1
 */
class ModTest {
    companion object {
        /**
         * The expected dummy mod identifier.
         */
        const val DUMMY_MOD_IDENTIFIER = "dummy_mod"

        /**
         * The expected String value, dispatched using Kawa.
         */
        const val EXPECTED_VALUE = "foobar1234"
    }

    var dispatchedValue: String? = null

    @Suppress("UNCHECKED_CAST")
    @Test
    fun testMod() {
        val dummyMod = (object : Mod(DUMMY_MOD_IDENTIFIER) {}).apply {
            val setting by setting(DUMMY_MOD_IDENTIFIER, false)

            on<String> {
                dispatchedValue = it
            }
        }

        assumeTrue(dummyMod.identifier == DUMMY_MOD_IDENTIFIER)

        val subList: List<Subscription<String>>? =
            dummyMod.subscriptions()[String::class.java] as List<Subscription<String>>?
        assumeTrue(subList != null)

        subList!!.forEach { it.consume(EXPECTED_VALUE) }
        assumeTrue(EXPECTED_VALUE == dispatchedValue)
    }
}
