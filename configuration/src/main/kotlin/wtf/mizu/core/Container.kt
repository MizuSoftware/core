@file:Suppress("NOTHING_TO_INLINE")

package wtf.mizu.core

import wtf.mizu.core.common.Describable
import wtf.mizu.core.common.Identifiable

/**
 * Represents a [Container] that holds both child [Container] and [Setting] instances.
 */
open class Container(
    override val id: String,
    override val desc: String = "$id.desc",
    private val containerToId: MutableMap<String, Configurable> = mutableMapOf()
): Configurable {

    /**
     * @inheritDoc
     */
    override val configurables: Collection<Configurable>
        get() = containerToId.values

    /**
     * @inheritDoc
     */
    override fun configurable(id: String) = containerToId[id]

    /**
     * @inheritDoc
     */
    override fun add(configurable: Configurable) {
        containerToId[configurable.id] = configurable
    }

}

/**
 * Creates and registers a new [Container] inside this container.
 */
inline fun Container.container(id: String, desc: String = "$id.desc")
        = Container(id, desc).also(this::add)