package wtf.mizu.core.clientapi

import wtf.mizu.core.common.Describable
import wtf.mizu.core.common.Identifiable
import wtf.mizu.keen.Listener
import wtf.mizu.keen.Subscription

abstract class Mod: Identifiable, Describable, Listener {

    private val subscriptions = mutableMapOf<Class<*>, List<Subscription<*>>>()

    override fun subscriptions() = subscriptions

}