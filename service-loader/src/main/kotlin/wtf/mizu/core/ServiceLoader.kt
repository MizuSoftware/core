package wtf.mizu.core

/**
 * Provides a simple [ServiceLoader] with only a few lines.
 */
@Suppress("UNCHECKED_CAST")
object ServiceLoader {

    private val services = mutableMapOf<Class<*>, Service<Any>>()

    /**
     * Assigns to given [service] given [impl].
     */
    fun <T: Any> assign(service: Class<in T>, impl: T) {
        services[service] = Service(service, impl) as Service<Any>
    }

    /**
     * Finds given [dependencyClass] instance.
     */
    @Suppress("UNCHECKED_CAST")
    fun <T: Any> find(dependencyClass: Class<T>)
            = services[dependencyClass] as Service<T>

    /**
     * Assigns to this service class given [impl].
     */
    infix fun <T: Any> Class<in T>.by(impl: T) = assign(this, impl)

}