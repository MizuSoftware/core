package wtf.mizu.core.configuration

import wtf.mizu.core.configuration.constraint.Constrained
import wtf.mizu.core.configuration.constraint.Constraint
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

/**
 * An extension of [Container] that holds a [constrainedValue].
 */
class Setting<T : Any>(
    id: String,
    desc: String = "$id.desc",
    value: T
) : Container(id, desc) {
    private var constrainedValue = Constrained(value)
    var value by constrainedValue

    /**
     * Returns given [Constraint] instance if used, otherwise `null`.
     */
    fun <C : Constraint<T>> find(clazz: Class<out C>) =
        constrainedValue.find(clazz)

    /**
     * @return the given [Constraint] instance if used, otherwise `null`.
     */
    inline fun <reified C : Constraint<T>> find(
        klass: KClass<out C> = C::class,
    ) = this.find(klass.java)

    /**
     * Adds a new [Constraint] to this [Constrained] instance.
     */
    fun add(c: Constraint<T>) = constrainedValue.constrain(c)

    fun remove(constraint: Constraint<T>) = constrainedValue.remove(constraint)

    fun remove(constraintClass: Class<out Constraint<T>>) =
        constrainedValue.remove(constraintClass)

    inline fun <reified C : Constraint<T>> remove(
        klass: KClass<out C> = C::class,
    ) = this.remove(klass.java)

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
 * Creates and registers a new [Setting] in this container.
 */
fun <T : Any> Container.setting(
    id: String, desc: String = "$id.desc", value: T
) = Setting(id, desc, value).also(this::add)

/**
 * Creates and registers a new [Setting] in this container.
 */
fun <T : Any> Container.setting(
    id: String, value: T
) = Setting(id, descriptionIdentifier, value).also(this::add)

/**
 * Creates and registers a new [Setting] in this container.
 */
inline fun <T : Any> Container.setting(
    id: String, desc: String = "$id.desc", value: T,
    crossinline block: Setting<T>.() -> Unit = {}
) = Setting(id, desc, value)
    .apply(block)
    .also(this::add)

/**
 * Creates and registers a new [Setting] in this container.
 */
inline fun <T : Any> Container.setting(
    id: String, value: T,
    crossinline block: Setting<T>.() -> Unit = {}
) = Setting(id, descriptionIdentifier, value)
    .apply(block)
    .also(this::add)
