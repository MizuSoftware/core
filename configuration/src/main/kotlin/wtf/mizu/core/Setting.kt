@file:Suppress("UNCHECKED_CAST")

package wtf.mizu.core

import wtf.mizu.core.constraint.Constraint
import kotlin.reflect.KProperty

/**
 * An extension of [Container] that holds a [value].
 */
class Setting<T: Any>(
    id: String,
    desc: String,
    value: T,
    private val constraints: MutableMap<Class<out Constraint<T>>, Constraint<T>> = mutableMapOf()
): Container(id, desc) {

    var value = value
        set(value) {
            constraints.values.forEach {
                field = it.constrain(field, value)
            }
        }

    /**
     * Finds given [Constraint] instance if it exists.
     */
    fun <C: Constraint<T>> constraint(`class`: Class<C>)
            = constraints[`class`] as C?

    /**
     * Finds given [Constraint] instance if it exists.
     */
    inline fun <reified C: Constraint<T>> constraint()
            = constraint(C::class.java)

    /**
     * Contrains this [Setting] using given [Constraint]
     */
    fun constrain(c: Constraint<T>) {
        value = value
        constraints[c.javaClass] = c
    }

    /**
     * Returns [value].
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