package wtf.mizu.core.common

/**
 * An object that can be identified using its [descriptionIdentifier] property.
 *
 * @author Shyrogan
 * @since 0.0.1
 */
interface Describable {
    /**
     * A string used to reference this object's description.
     *
     * To maintain a consistency between descriptions, those *must* comply with
     * the regex expression such as coded into the [IDENTIFIER_PATTERN] const.
     *
     * Some **good** examples for descriptions would be:
     * ```
     * domain.object.desc
     * domain.object.more.desc
     * domain.object.more.even_more.desc
     * ```
     */
    val descriptionIdentifier: String
}
