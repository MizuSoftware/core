package wtf.mizu.core.clientapi.mod

import kotlin.reflect.KClass

/**
 * Stores and manages a [Mod] list.
 *
 * It is possible to add a custom [Mod] to a [ModService] instance using the
 * [add] method if and ONLY if no other [Mod] having the same id or type
 * already exists.
 *
 * If one of those criteria is not respected, an exception will be thrown.
 *
 * @author Shyrogan
 * @see Mod
 * @since 0.0.1
 */
interface ModService : MutableList<Mod> {
    /**
     * Gets a [Mod] instance from its id.
     *
     * @param modId the wanted mod's id.
     *
     * @return the wanted mod, or null if not found.
     */
    operator fun get(modId: String): Mod?

    /**
     * Gets a [Mod] instance from its class.
     *
     * @param modClass the wanted mod's class.
     *
     * @return the wanted mod, or null if not found.
     */
    operator fun get(modClass: Class<out Mod>): Mod?

    /**
     * Gets a [Mod] instance from its Kotlin class.
     *
     * @param modKlass the wanted mod's Kotlin class.
     *
     * @return the wanted mod, or null if not found.
     */
    operator fun get(modKlass: KClass<out Mod>): Mod? = this[modKlass.java]
}
