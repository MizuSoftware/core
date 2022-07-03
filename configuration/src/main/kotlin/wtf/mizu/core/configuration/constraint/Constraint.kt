package wtf.mizu.core.configuration.constraint

import wtf.mizu.core.configuration.Setting

/**
 * An object that interferes when a [Setting.constrainedValue] has to be
 * constrained.
 *
 * @author Shyrogan
 * @since 0.0.1
 */
interface Constraint<T : Any> {
    /**
     * Applies this [Constraint] to the given [value].
     *
     * By default, returns the [previous] value.
     *
     * @param previous the previous value, can be unconstrained.
     * @param value the value to constrain.
     *
     * @return the constrained value.
     */
    fun apply(previous: T, value: T): T = previous
}
