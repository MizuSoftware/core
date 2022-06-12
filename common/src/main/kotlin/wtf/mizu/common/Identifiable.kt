package wtf.mizu.common

/**
 * An object that can be identified using its [id] property.
 *
 * @see id
 */
interface Identifiable {

    /**
     * Returns an identifier used to identify this object.
     *
     * To maintain a consistency between identifiers, they must respect
     * the following regex expression:
     * ```regex
     * ^[a-z][a-z._]+[a-z]$
     * ```
     *
     * An [id] must be composed of lower case letters and underscore.
     * The first and last characters must be letters.
     *
     * Some **good** examples for Identifier would be:
     * ```
     * domain.object
     * domain.object.more
     * domain.object.more.even_more
     * ```
     */
    val id: String

    companion object {
        /**
         * Returns the regex used to maintain a consistency between
         * identifiers.
         */
        val ID_PATTERN = "^[a-z][a-z._]+[a-z]\$".toRegex()
    }
}