package wtf.mizu.core.common.plugin;

import wtf.mizu.core.common.Describable;
import wtf.mizu.core.common.Identifiable;

/**
 * A {@link PluginContainer} is used to provide data about a plugin in Mizu.
 * <p>
 * Using {@code guice}, the container can be injected into a field and used
 * through your code.
 * <p>
 * Any service will require you to provide the {@link PluginContainer},
 * allowing the client to enable/disable plugins.
 *
 * @param id the id
 * @param name the name
 * @param description the description
 */
public record PluginContainer(
        String id,
        String name,
        String description
) implements Identifiable, Describable {

    /**
     * The name of the json file used to identify plugins.
     */
    public static final String FILE = "mizu.plugin.json";

    public PluginContainer(String id, String description) {
        this(id, id, description);
    }

}
