package wtf.mizu.core.constraint

import wtf.mizu.core.Setting

/**
 * Coerces a [Comparable] value into a Kotlin [ClosedRange].
 */
data class RangeConstraint<T : Comparable<T>>(
    val range: ClosedRange<T>,
) : Constraint<T> {
    override fun apply(previous: T, future: T): T = future.coerceIn(range)
}

/**
 * inception
 */
data class RangeBoundConstraint<T : Comparable<T>>(
    val range: ClosedRange<T>,
) : Constraint<ClosedRange<T>> {
    override fun apply(
        previous: ClosedRange<T>,
        future: ClosedRange<T>,
    ): ClosedRange<T> =
        if (
            future.start.coerceIn(range) != future.start ||
            future.endInclusive.coerceIn(range) != future.endInclusive
        )
            (object : ClosedRange<T> {
                override val endInclusive = future.endInclusive.coerceIn(range)
                override val start = future.start.coerceIn(range)
            })
        else
            future
}

/**
 * Defines the value-range of a [Setting] with [Comparable] values.
 */
inline var <T : Comparable<T>> Setting<T>.range: ClosedRange<T>?
    get() = this.find<RangeConstraint<T>>()?.range
    set(value) {
        if (value != null) {
            this.add(RangeConstraint(value))
        } else {
            this.remove<RangeConstraint<T>>()
        }
    }

/**
 * Sets the value-range of a [Setting] with [Comparable] values.
 *
 * @return this instance.
 */
fun <T : Comparable<T>> Setting<T>.range(value: ClosedRange<T>?) = apply {
    this.range = value
}

/**
 * Defines the value-range of a [Setting] with a [ClosedRange] of [Comparable]
 * values.
 */
inline var <T : Comparable<T>> Setting<ClosedRange<T>>.bound: ClosedRange<T>?
    get() = this.find<RangeBoundConstraint<T>>()?.range
    set(value) {
        if (value != null) {
            this.add(RangeBoundConstraint(value))
        } else {
            this.remove<RangeBoundConstraint<T>>()
        }
    }

/**
 * Sets the value-range of a [Setting] with a [ClosedRange] of [Comparable]
 * values.
 *
 * @return this instance.
 */
fun <T : Comparable<T>> Setting<ClosedRange<T>>.bound(value: ClosedRange<T>?) =
    apply { this.bound = value }
