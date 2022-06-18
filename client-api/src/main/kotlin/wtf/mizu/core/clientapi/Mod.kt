package wtf.mizu.core.clientapi

import wtf.mizu.core.Container
import wtf.mizu.core.common.Describable
import wtf.mizu.core.common.Identifiable
import wtf.mizu.keen.Listener
import wtf.mizu.keen.Subscription

/**
 * A client [Mod] is an object modifies the behaviour of the game
 * through events/inputs.
 *
 * Using `configurations`, we can adjust [Mod] to the context and
 * provides the best experience possible.
 */
abstract class Mod(
    id: String,
    desc: String = "$id.desc",
): Listener, Container(id, desc) {

    private val subscriptions = mutableMapOf<Class<*>, List<Subscription<*>>>()

    override fun subscriptions() = subscriptions

}