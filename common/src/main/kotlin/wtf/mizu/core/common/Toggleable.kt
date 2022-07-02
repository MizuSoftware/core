package wtf.mizu.core.common

/**
 * An object that can be toggled through the [isRunning] property.
 *
 * @author Shyrogan
 * @since 0.0.1
 */
interface Toggleable {
    /**
     * A boolean used to know whether this [Toggleable] object is toggled.
     *
     * Modifying this value can actually trigger code as it is not supposed to
     * *only* a property.
     */
    var isRunning: Boolean
}
