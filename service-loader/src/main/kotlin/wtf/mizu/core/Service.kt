package wtf.mizu.core

import kotlin.reflect.KProperty

/**
 * Holds a [Service] implementation [impl] for [service].
 */
data class Service<T: Any>(
    val service: Class<in T>,
    var impl: T
) {

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = impl
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.impl = value
    }

}