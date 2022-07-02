package wtf.mizu.core.configuration

import wtf.mizu.core.common.Describable
import wtf.mizu.core.common.Identifiable

/**
 * An object that can be tweaked to fit the end user's needs through settings.
 *
 * [Setting]s are also [Configurable]. They can be accessed through both
 * [containers] and [configurable] functions.
 *
 * You might also store a [Container] which simply implements the
 * [Configurable] interface.
 */
interface Configurable : Identifiable, Describable {
    /**
     * Returns a list of [Configurable] instances.
     */
    val configurables: Collection<Configurable>

    /**
     * Returns a [Configurable] instance from its [id].
     */
    fun configurable(id: String): Configurable?

    /**
     * Adds the given [Configurable] to this instance.
     */
    fun add(configurable: Configurable)
}
