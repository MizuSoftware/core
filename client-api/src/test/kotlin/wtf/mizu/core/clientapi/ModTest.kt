package wtf.mizu.core.clientapi

import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.Test
import wtf.mizu.core.clientapi.mod.Mod
import wtf.mizu.core.setting
import wtf.mizu.keen.on

class ModTest {

    inline fun mod(id: String, desc: String = "$id.desc", block: Mod.() -> Unit)
            = object: Mod(id, desc) {}.apply(block)

    @Test
    fun modTest() {
        val m = mod("mod") {
            val setting by setting("my_setting", false)

            on<String> {
                println("$this $setting")
            }
        }
        assumeTrue(m.id.equals("mod", true))
        assumeTrue(m.subscriptions()[String::class.java] != null)
    }

}