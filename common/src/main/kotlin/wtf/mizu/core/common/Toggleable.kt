package wtf.mizu.core.common

/**
 * An object that can be toggled through the [isRunning] property.
 */
interface Toggleable {

    /**
     * A property that returns `true` if this [Toggleable] is toggled
     * or `false`.
     *
     * Modifying this value can actually trigger code as it is generally
     * not only a field.
     */
    var isRunning: Boolean

}