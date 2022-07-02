package wtf.mizu.core.configuration

/**
 * An object that holds both child [Container] and [Setting] instances.
 */
open class Container(
    override val identifier: String,

    override val descriptionIdentifier: String = "$identifier.desc",

    /**
     * A map used to associate each child [Configurable] to its ID.
     */
    private val containerToId: MutableMap<String, Configurable> =
        hashMapOf(),
) : Configurable {
    override val configurables: Collection<Configurable>
        get() = containerToId.values

    override fun configurable(id: String) = containerToId[id]

    override fun add(configurable: Configurable) {
        containerToId[configurable.identifier] = configurable
    }

    /**
     * Creates and registers a new [Container] in this instance.
     *
     * @return the newly created [Container].
     */
    fun container(id: String, desc: String = "$id.desc") =
        Container(id, desc).also(this::add)
}
