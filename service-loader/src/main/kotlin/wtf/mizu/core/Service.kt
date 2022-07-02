package wtf.mizu.core

import kotlin.reflect.KProperty

/**
 * Delegates an implementation for the provided service.
 *
 * @param T The service type.
 *
 * @property serviceClass The service class.
 * @property impl The service's implementation instance.
 *
 * @author Shyrogan
 * @since 0.0.1
 */
data class Service<T : Any>(
    val serviceClass: Class<out T>,
    private var impl: T,
) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T = impl

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.impl = value
    }
}
