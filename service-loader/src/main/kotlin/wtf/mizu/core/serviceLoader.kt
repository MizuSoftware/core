package wtf.mizu.core

import kotlin.reflect.KClass

/**
 * Provides a simple [ServiceLoader] with only a few lines.
 */
@Suppress("UNCHECKED_CAST")
object ServiceLoader {
    private val services = hashMapOf<Class<*>, Service<Any>>()

    /**
     * Syntactic sugar around [find].
     *
     * @see [find]
     */
    @JvmName("find")
    operator fun <T : Any> get(
        serviceClass: KClass<out T>,
    ): Service<out T>? = this.find(serviceClass.java)

    /**
     * Syntactic sugar around [assign].
     *
     * @see [assign]
     */
    @JvmName("assign")
    operator fun <T : Any> set(serviceClass: KClass<out T>, impl: T) =
        this.assign(serviceClass.java, impl)

    /**
     * Assigns the given [implementation] to the given [service].
     *
     * @param service the service to assign the given [implementation] to.
     * @param implementation the implementation to assign to the [service].
     */
    fun <T : Any> assign(service: Class<out T>, implementation: T) {
        this.services[service] = Service(service, implementation) as Service<Any>
    }

    /**
     * Finds the stored instance of the given [serviceClass].
     *
     * @param serviceClass the wanted instance's class.
     *
     * @return the instance, if found, or `null`.
     */
    fun <T : Any> find(serviceClass: Class<out T>): Service<T>? =
        this.services[serviceClass] as? Service<T>
}

/**
 * Assigns the given [impl] to the [ServiceLoader] object.
 */
infix fun <T : Any> Class<out T>.by(impl: T) =
    ServiceLoader.assign(this, impl)
