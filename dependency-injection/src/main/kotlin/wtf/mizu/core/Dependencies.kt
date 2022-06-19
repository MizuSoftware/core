@file:Suppress("NOTHING_TO_INLINE")

package wtf.mizu.core

/**
 * Provides a simple [Dependencies] with only a few lines.
 */
object Dependencies {
    private val dependencies = mutableMapOf<Class<*>, Any>()

    /**
     * Assigns to given [dependencyClass] given [instance].
     */
    fun <T : Any> assign(dependencyClass: Class<in T>, instance: T) {
        dependencies[dependencyClass] = instance
    }

    /**
     * Finds given [dependencyClass] instance.
     */
    @Suppress("UNCHECKED_CAST")
    fun <T : Any> find(dependencyClass: Class<T>) =
        dependencies[dependencyClass] as T
}

/**
 * Replaces [Dependencies] by one line.
 */
inline fun <reified T : Any> inject() =
    Dependencies.find(T::class.java)

/**
 * Assigns to given [dependencyClass] given [T].
 */
inline fun <T : Any> T.assign(dependencyClass: Class<in T>) =
    Dependencies.assign(dependencyClass, this)