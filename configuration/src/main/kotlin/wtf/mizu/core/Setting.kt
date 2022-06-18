@file:Suppress("UNCHECKED_CAST", "NOTHING_TO_INLINE")

package wtf.mizu.core

import wtf.mizu.core.constraint.ConstrainedValue
import wtf.mizu.core.constraint.Constraint
import kotlin.reflect.KProperty

/**
 * An extension of [Container] that holds a [constrainedValue].
 */
class Setting<T: Any>(
    id: String,
    desc: String = "$id.desc",
    value: T
): Container(id, desc) {

    private var constrainedValue = ConstrainedValue(value)
    var value by constrainedValue

    /**
     * Returns given [Constraint] instance if used, otherwise `null`.
     */
    fun <C: Constraint<T>> find(`class`: Class<C>) = constrainedValue.find(`class`)

    /**
     * Returns given [Constraint] instance if used, otherwise `null`.
     */
    inline fun <reified C: Constraint<T>> find() = this.find(C::class.java)

    /**
     * Adds a new [Constraint] to this [ConstrainedValue].
     */
    fun add(c: Constraint<T>) = constrainedValue.add(c)

    /**
     * Returns [constrainedValue].
     *
     * The operator function allow this class to be used in delegation.
     */
    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

    /**
     * Sets [value].
     *
     * The operator function allow this class to be used in delegation.
     */
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = value
    }

}

/**
 * Creates and registers a new [Setting] inside this container.
 */
inline fun <T: Any> Container.setting(
    id: String, desc: String = "$id.desc", value: T
) = Setting(id, desc, value).also(this::add)

/**
 * Creates and registers a new [Setting] inside this container.
 */
inline fun <T: Any> Container.setting(
    id: String, desc: String = "$id.desc", value: T,
    crossinline block: Setting<T>.() -> Unit = {}
) = Setting(id, desc, value)
    .apply(block)
    .also(this::add)

/**
 * Creates and registers a new [Setting] inside this container.
 */
inline fun <T: Any> Container.setting(
    id: String, value: T,
    crossinline block: Setting<T>.() -> Unit = {}
) = Setting(id, desc, value)
    .apply(block)
    .also(this::add)

/**
 * Creates and registers a new [Setting] inside this container.
 */
inline fun <T: Any> Container.setting(
    id: String, value: T
) = Setting(id, desc, value).also(this::add)