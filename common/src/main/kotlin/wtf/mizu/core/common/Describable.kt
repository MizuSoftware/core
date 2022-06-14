package wtf.mizu.core.common

/**
 * An object that can be identified using its [desc] property.
 * @see desc
 */
interface Describable {

    /**
     * Returns an identifier used to describe this object.
     *
     * To maintain a consistency between descriptions, they must respect
     * the following regex expression:
     * ```regex
     * ^[a-z][a-z._]+[a-z]$
     * ```
     *
     * An [desc] must be composed of lower case letters and underscore.
     * The first and last characters must be letters.
     *
     * Some **good** examples for descriptions would be:
     * ```
     * domain.object
     * domain.object.more
     * domain.object.more.even_more
     * ```
     */
    val desc: String

}