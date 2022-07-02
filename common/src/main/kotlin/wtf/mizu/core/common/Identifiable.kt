package wtf.mizu.core.common

/**
 * An object that can be identified using its [identifier] property.
 *
 * @author Shyrogan
 * @since 0.0.1
 */
interface Identifiable {
    /**
     * A **unique** string used to identify this object.
     *
     * To maintain a consistency between descriptions, those *must* comply with
     * the regex expression such as coded into the [IDENTIFIER_PATTERN] const.
     *
     * Some **good** examples for Identifier would be:
     * ```
     * domain.object
     * domain.object.more
     * domain.object.more.even_more
     * ```
     */
    val identifier: String
}
