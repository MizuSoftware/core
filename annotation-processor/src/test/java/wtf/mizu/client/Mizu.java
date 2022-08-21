package wtf.mizu.client;

import wtf.mizu.core.common.plugin.Plugin;
import wtf.mizu.core.common.plugin.PluginContainer;

@Plugin
public class Mizu {

    public static final Mizu mizu = new Mizu();

    // @Inject
    private PluginContainer container;

    public PluginContainer plugin() {
        return container;
    }
}
