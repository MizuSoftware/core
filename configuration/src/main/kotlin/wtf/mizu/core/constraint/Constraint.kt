package wtf.mizu.core.constraint

import wtf.mizu.core.Setting

/**
 * A [Constraint] is an object that interferes when a [Setting.value]
 */
interface Constraint<T: Any> {

    /**
     * Apply the [Constraint] condition by returning a value that
     * might differ of [future] to modify the value.
     */
    fun constrain(previous: T, future: T) = previous

}