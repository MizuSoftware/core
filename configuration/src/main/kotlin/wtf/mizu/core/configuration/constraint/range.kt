package wtf.mizu.core.configuration.constraint

import wtf.mizu.core.configuration.Setting

/**
 * Coerces a [Comparable] value into a Kotlin [ClosedRange].
 *
 * @author Shyrogan
 * @since 0.0.1
 */
data class RangeConstraint<T : Comparable<T>>(
    val range: ClosedRange<T>,
) : Constraint<T> {
    /**
     * TODO
     */
    override fun apply(previous: T, value: T): T = value.coerceIn(range)
}

/**
 * Coerces a [Comparable] value into a Kotlin [ClosedRange] in a given bound.
 *
 * @author Shyrogan
 * @since 0.0.1
 */
data class RangeBoundConstraint<T : Comparable<T>>(
    val range: ClosedRange<T>,
) : Constraint<ClosedRange<T>> {
    /**
     * TODO
     */
    override fun apply(
        previous: ClosedRange<T>,
        value: ClosedRange<T>,
    ): ClosedRange<T> =
        if (
            value.start.coerceIn(range) != value.start ||
            value.endInclusive.coerceIn(range) != value.endInclusive
        )
            (object : ClosedRange<T> {
                override val endInclusive = value.endInclusive.coerceIn(range)
                override val start = value.start.coerceIn(range)
            })
        else
            value
}

/**
 * Defines the value-range of a [Setting] with [Comparable] values.
 *
 * @author Shyrogan
 * @since 0.0.1
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
 *
 * @author Shyrogan
 * @since 0.0.1
 */
fun <T : Comparable<T>> Setting<T>.range(value: ClosedRange<T>?) = apply {
    this.range = value
}

/**
 * Defines the value-range of a [Setting] with a [ClosedRange] of [Comparable]
 * values.
 *
 * @author Shyrogan
 * @since 0.0.1
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
 *
 * @author Shyrogan
 * @since 0.0.1
 */
fun <T : Comparable<T>> Setting<ClosedRange<T>>.bound(value: ClosedRange<T>?) =
    apply { this.bound = value }
