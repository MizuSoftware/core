package wtf.mizu.core.constraint

import wtf.mizu.core.Setting

data class RangeConstraint<T: Comparable<T>>(
    val range: ClosedRange<T>
): Constraint<T> {
    override fun constrain(previous: T, future: T) = future.coerceIn(range)
}

/**
 * inception
 */
data class RangeBoundConstraint<T: Comparable<T>>(
    val range: ClosedRange<T>
): Constraint<ClosedRange<T>> {
    override fun constrain(previous: ClosedRange<T>, future: ClosedRange<T>): ClosedRange<T> {
        if(future.start.coerceIn(range) != future.start || future.endInclusive.coerceIn(range) != future.endInclusive) {
            return object: ClosedRange<T> {
                override val endInclusive = future.endInclusive.coerceIn(range)
                override val start = future.start.coerceIn(range)
            }
        }
        return future;
    }
}

/**
 * Defines the value-range of a [Setting] with [Comparable] value.
 */
inline var <T: Comparable<T>> Setting<T>.range: ClosedRange<T>?
    get() = this.constraint<RangeConstraint<T>>()?.range
    set(value) {
        if (value != null) {
            this.constrain(RangeConstraint(value))
        }
    }

/**
 * Defines the value-range of a [Setting] with a [ClosedRange] of [Comparable] value.
 */
inline var <T: Comparable<T>> Setting<ClosedRange<T>>.bound: ClosedRange<T>?
    get() = this.constraint<RangeBoundConstraint<T>>()?.range
    set(value) {
        if (value != null) {
            this.constrain(RangeBoundConstraint(value))
        }
    }