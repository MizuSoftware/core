package wtf.mizu.client.api.mod

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
     * Gets a [Mod] instance from its [Mod.identifier].
     *
     * @param modIdentifier the wanted [Mod]'s identifier.
     *
     * @return the wanted [Mod] instance if found, `null` otherwise.
     */
    operator fun get(modIdentifier: String): Mod?

    /**
     * Gets a [Mod] instance from its [Class].
     *
     * @param modClass the wanted [Mod]'s [Class].
     *
     * @return the wanted [Mod] instance if found, `null` otherwise.
     */
    operator fun get(modClass: Class<out Mod>): Mod?

    /**
     * Gets a [Mod] instance from its [Kotlin class][KClass].
     *
     * @param modKlass the wanted mod's [Kotlin class][KClass].
     *
     * @return the wanted [Mod] instance if found, `null` otherwise.
     */
    operator fun get(modKlass: KClass<out Mod>): Mod? = this[modKlass.java]
}
