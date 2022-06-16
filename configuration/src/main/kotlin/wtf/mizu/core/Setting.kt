package wtf.mizu.core

import wtf.mizu.core.common.Describable
import wtf.mizu.core.common.Identifiable
import kotlin.reflect.KProperty

class Setting<T: Any>(
    id: String,
    desc: String,
    value: T
): Container(id, desc) {

    var value = value

    /**
     * Returns [value].
     *
     * The operator function allow this class to be used in delegation.
     */
    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

    /**
     * Sets [value].
     *
     * The operator function allow this class to be used in delegation.
     */
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = value
    }

    data class Builder<T: Any>(
        /**
         * Stores the [id] of the [Setting] being built.
         */
        override val id: String,
        /**
         * Stores the [desc] of the [Setting] being built.
         */
        override val desc: String = "$id.desc",
        /**
         * Stores the default value of the [Setting] being built.
         */
        val value: T
    ): Identifiable, Describable {

        val built = Setting(id, desc, value)

    }

}