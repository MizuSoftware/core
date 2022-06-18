@file:Suppress("NOTHING_TO_INLINE")

package wtf.mizu.core

import wtf.mizu.core.common.Describable
import wtf.mizu.core.common.Identifiable

/**
 * Represents a [Container] that holds both child [Container] and [Setting] instances.
 */
open class Container(
    override val id: String,
    override val desc: String = "$id.desc"
): Identifiable, Describable {

    private val containerToId = mutableMapOf<String, Container>()

    val child: Collection<Container>
        get() = containerToId.values

    /**
     * Returns the [Container] instance stored by this [Container] with specified
     * [id] if it exists.
     */
    operator fun get(id: String) = containerToId[id]

    /**
     * Returns the [Container] instance stored by this [Container] with specified
     * [id] if it exists.
     */
    inline fun find(id: String) = get(id)

    /**
     * Adds given [Container] to this [Container] instance.
     */
    operator fun plusAssign(container: Container) {
        containerToId[container.id] = container
    }

    /**
     * Adds given [Container] to this [Container] instance.
     */
    inline fun add(container: Container) = plusAssign(container)

}

/**
 * Creates and registers a new [Container] inside this container.
 */
inline fun Container.container(id: String, desc: String = "$id.desc")
        = Container(id, desc).also(this::add)

/**
 * Creates and registers a new [Setting] inside this container.
 */
inline fun <T: Any> Container.setting(
    id: String, desc: String = "$id.desc", value: T,
    crossinline block: Setting<T>.() -> Unit = {}
) = Setting(id, desc, value)
    .apply(block)
    .also(this::add)