package wtf.mizu.core.constraint

import wtf.mizu.core.Setting

/**
 * Constrains the setting to [choices] values.
 */
data class ChoiceConstraint<T: Any>(
    val choices: LinkedHashSet<T>
): Constraint<T> {

    override fun constrain(previous: T, future: T): T {
        if(!choices.contains(previous))
            choices.add(previous)

        return if(!choices.contains(future)) previous
        else future
    }

}

/**
 * Defines the possible values of a [Setting].
 */
inline var <T: Any> Setting<T>.choices: Set<T>
    get() = constraint<ChoiceConstraint<T>>()?.choices ?: emptySet()
    set(value) {
        constrain(ChoiceConstraint(LinkedHashSet(value)))
    }