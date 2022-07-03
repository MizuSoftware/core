package wtf.mizu.core.service

import wtf.mizu.core.service.exception.UnimplementedServiceException
import kotlin.reflect.KClass

/**
 * An overly simplified [ServiceLoader].
 *
 * @author Shyrogan
 * @since 0.0.1
 */
@Suppress("UNCHECKED_CAST")
object ServiceLoader {
    private val services = hashMapOf<Class<*>, Service<Any>>()

    /**
     * Syntactic sugar around the [find] method.
     *
     * @see ServiceLoader.find
     */
    @JvmName("find")
    operator fun <T : Any> get(
        serviceClass: KClass<out T>,
    ): Service<out T> = find(serviceClass.java)

    /**
     * Syntactic sugar around the [assign] method.
     *
     * @see ServiceLoader.assign
     */
    @JvmName("assign")
    operator fun <T : Any> set(serviceClass: KClass<out T>, impl: T) =
        assign(serviceClass.java, impl)

    /**
     * Assigns the given [implementation] to the given [service].
     *
     * @param service the service to assign the given [implementation] to.
     * @param implementation the implementation to assign to the [service].
     */
    fun <T : Any> assign(service: Class<out T>, implementation: T) {
        services[service] = Service(service, implementation) as Service<Any>
    }

    /**
     * Finds the stored instance of the given [serviceClass].
     *
     * @param serviceClass the wanted instance's class.
     *
     * @throws UnimplementedServiceException if the wanted service's
     *                                       implementation could not be found.
     *
     * @return the instance, if found, or `null`.
     */
    fun <T : Any> find(serviceClass: Class<out T>): Service<T> =
        services[serviceClass] as? Service<T>
            ?: throw UnimplementedServiceException(serviceClass)
}

/**
 * Assigns the given [serviceImplementation] to the [ServiceLoader] singleton.
 *
 * @author Shyrogan
 * @since 0.0.1
 */
infix fun <T : Any> Class<out T>.by(serviceImplementation: T) =
    ServiceLoader.assign(this, serviceImplementation)
