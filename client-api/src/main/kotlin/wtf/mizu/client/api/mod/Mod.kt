package wtf.mizu.client.api.mod

import wtf.mizu.core.configuration.Container

import wtf.mizu.kawa.api.Listener
import wtf.mizu.kawa.api.Subscription

/**
 * Modifies the game behaviour through events and/or inputs.
 *
 * Using
 * [`configurations`](https://github.com/MizuSoftware/core/tree/main/configuration),
 * we can adjust [Mod] to the wanted context and provide the best possible
 * experience.
 *
 * Even if there is no abstract [Mod] class that implements
 * [Toggleable][wtf.mizu.core.common.Toggleable], it is common for a [Mod] to
 * do so.
 *
 * @author Shyrogan
 * @since 0.0.1
 */
abstract class Mod(
    identifier: String,
    descriptionIdentifier: String = "$identifier.desc",
) : Listener, Container(identifier, descriptionIdentifier) {
    private val subscriptions: MutableMap<Class<*>, List<Subscription<*>>>
            by lazy { hashMapOf() }

    override fun subscriptions() = subscriptions
}
