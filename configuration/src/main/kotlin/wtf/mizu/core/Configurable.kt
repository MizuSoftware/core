package wtf.mizu.core

import wtf.mizu.core.common.Describable
import wtf.mizu.core.common.Identifiable

/**
 * A [Configurable] object is an object that can be tweaked to fit someone needs
 * through settings.
 *
 * [Setting] are also [Configurable]. They can be accessed through both [containers]
 * and [configurable] functions.
 *
 * You might also store [Container] which simply implement the [Configurable] interface.
 */
interface Configurable: Identifiable, Describable {

    /**
     * Returns a list of [Configurable] instances.
     */
    val configurables: Collection<Configurable>

    /**
     * Returns a [Configurable] instance from its [id].
     */
    fun configurable(id: String): Configurable?

    /**
     * Adds given [Configurable] to this [Configurable].
     */
    fun add(configurable: Configurable)

}