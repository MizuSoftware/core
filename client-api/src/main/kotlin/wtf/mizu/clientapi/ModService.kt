package wtf.mizu.clientapi

/**
 * A [ModService] stores and manages a list of [Mod]s.
 *
 * It is possible to add custom [Mod] to the [ModService] using the [add] function
 * only if:
 * - There is not already a [Mod] with the same ID.
 * - There is not already a [Mod] with the same type.
 *
 * If one of those criteria is not respected, an exception will be thrown.
 */
interface ModService: MutableList<Mod> {

    /**
     * Returns a [Mod] instance from its [id].
     */
    operator fun get(id: String): Mod?

    /**
     * Returns a [Mod] instance from its [class].
     */
    operator fun get(`class`: Class<out Mod>): Mod?

}