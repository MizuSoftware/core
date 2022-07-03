package wtf.mizu.core.service

import kotlin.reflect.KProperty

/**
 * Delegates an implementation for the provided service.
 *
 * @param T the service type.
 *
 * @property serviceClass the service class.
 * @property impl the service's implementation instance.
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
