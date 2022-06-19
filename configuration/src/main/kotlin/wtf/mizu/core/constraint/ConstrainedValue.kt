@file:Suppress("UNCHECKED_CAST")

package wtf.mizu.core.constraint

import kotlin.reflect.KProperty

/**
 * A [ConstrainedValue] is a [value] that is constrained using [Constraint]
 * implementations.
 */
class ConstrainedValue<T: Any>(
    /**
     * The default value
     */
    value: T,
    /**
     * The constraints map
     */
    val constraints: MutableMap<Class<out Constraint<T>>, Constraint<T>> = mutableMapOf()
) {

    /**
     * A property that applies each [constraints] (if there are any) everytime
     * a value is changed.
     */
    var value = value
        set(value) {
            if(constraints.isEmpty())
                field = value
            else
                constraints.forEach { (_, c) -> field = c.constrain(field, value) }
        }

    /**
     * Returns given [Constraint] instance if used, otherwise `null`.
     */
    fun <C: Constraint<T>> find(`class`: Class<C>) = constraints[`class`] as C?

    /**
     * Adds a new [Constraint] to this [ConstrainedValue].
     */
    fun add(c: Constraint<T>) {
        constraints[c.javaClass] = c
    }

    /**
     * Delegation
     */
    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

    /**
     * Delegation
     */
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = value
    }

}