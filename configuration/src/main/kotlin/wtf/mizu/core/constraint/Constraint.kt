package wtf.mizu.core.constraint

import wtf.mizu.core.Setting

/**
 * An object that interferes when a [Setting.constrainedValue] has to be
 * constrained.
 */
interface Constraint<T : Any> {
    /**
     * Applies this [Constraint].
     *
     * By default, returns the [previous] value.
     *
     * @param previous the previous value, can be unconstrained.
     * @param future the value to constrain.
     *
     * @return the constrained value.
     */
    fun apply(previous: T, future: T): T = previous
}
