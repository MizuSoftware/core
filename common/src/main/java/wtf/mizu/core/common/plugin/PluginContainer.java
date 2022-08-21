package wtf.mizu.core.common.plugin;

import wtf.mizu.core.common.Describable;
import wtf.mizu.core.common.Identifiable;

public record PluginContainer(
        String id,
        String name,
        String description
) implements Identifiable, Describable {

    public static final String FILE = "mizu.plugin.json";

    public PluginContainer(String id, String description) {
        this(id, id, description);
    }

}
