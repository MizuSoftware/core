@file:Suppress("UNCHECKED_CAST")

package wtf.mizu.core.configuration.constraint

import kotlin.reflect.KClass
import kotlin.reflect.KProperty

/**
 * A [value] that is constrained using [Constraint] implementations.
 */
class Constrained<T : Any>(
    /**
     * The default value.
     */
    defaultValue: T,

    /**
     * A map used to associate a constraint class to its instance.
     */
    private val constraintMap: MutableMap<Class<out Constraint<T>>, Constraint<T>> =
        hashMapOf(),
) {
    /**
     * A property that gets applied each element from the [constraintMap]
     * (if there are any) everytime its value is changed.
     */
    private var value: T = defaultValue
        set(value) {
            if (constraintMap.isEmpty())
                field = value
            else
                constraintMap.values.forEach {
                    field = it.apply(field, value)
                }
        }

    operator fun getValue(_this: Any?, _property: KProperty<*>): T = value

    operator fun setValue(_this: Any?, _property: KProperty<*>, value: T) {
        this.value = value
    }

    /**
     * Finds a [Constraint] instance from its class.
     *
     * @param clazz the wanted constraint's class.
     *
     * @return the wanted [Constraint] instance if used, `null` otherwise.
     */
    fun <C : Constraint<T>> find(clazz: Class<C>): C? =
        constraintMap[clazz] as? C

    /**
     * Adds a new [Constraint] to this [Constrained] instance if no other
     * [Constraint] from the same class exists. Otherwise, replaces it.
     *
     * @return this instance.
     */
    fun constrain(constraint: Constraint<T>): Constrained<T> = apply {
        constraintMap[constraint::class.java] = constraint
    }

    /**
     * Removes the wanted [Constraint] from this [Constrained] instance.
     *
     * @return this instance.
     */
    fun remove(constraint: Constraint<T>): Constrained<T> =
        this.remove(constraint::class)

    /**
     * Removes the wanted [Constraint]'s class from this [Constrained] instance.
     *
     * @return this instance.
     */
    fun remove(
        constraintClass: Class<out Constraint<T>>
    ): Constrained<T> = apply { constraintMap.remove(constraintClass) }

    /**
     * Removes the wanted [Constraint]'s class from this [Constrained]
     * instance.
     *
     * @return this instance.
     */
    inline fun <reified C : Constraint<T>> remove(
        constraintKlass: KClass<out C> = C::class,
    ): Constrained<T> = this.remove(constraintKlass.java)
}
