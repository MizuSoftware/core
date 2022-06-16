@file:Suppress("NOTHING_TO_INLINE")

package wtf.mizu.core

import wtf.mizu.core.common.Describable
import wtf.mizu.core.common.Identifiable

/**
 * Represents a [Container] that holds both child [Container] and [Setting] instances.
 */
open class Container(
    override val id: String,
    override val desc: String,
    private val containers: MutableList<Container> = mutableListOf()
): Identifiable, Describable, MutableList<Container> by containers {

    private val containerToId = mutableMapOf<String, Container>()

    override fun add(element: Container): Boolean {
        containerToId[element.id] = element
        return containers.add(element)
    }

    /**
     * Returns the [Container] instance stored by this [Container] with specified
     * [id] if it exists.
     */
    operator fun get(id: String) = containerToId[id]

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
    crossinline block: Setting.Builder<T>.() -> Unit
) = Setting.Builder(id, desc, value)
    .apply(block)
    .built.also(this::add)