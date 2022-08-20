package wtf.mizu.client.mod;

import com.google.auto.service.AutoService;
import org.jetbrains.annotations.Nullable;
import wtf.mizu.client.MizuPlugin;
import wtf.mizu.client.mod.category.DummyMod;
import wtf.mizu.core.common.Identifiable;
import wtf.mizu.core.mod.ModService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This is a simple implementation of the {@link ModService mod
 * service}.
 * <p>
 * Using Google's {@link AutoService} annotation, we don't have to do
 * anything except annotate the class for it to work properly.
 * <p>
 * The {@code mod instances} are loaded inside the {@link this#mods}
 * list and then {@link Map} to retrieve them are generated from it.
 */
@SuppressWarnings("ALL")
@AutoService(ModService.class)
public final class MizuModService implements ModService {

    /**
     * The {@link Identifiable mods} list.
     */
    private final List<Identifiable> mods = List.of(
        new DummyMod()
    );

    /**
     * A map that associates each {@link Identifiable mod} to its id.
     */
    private final Map<String, Identifiable> idToMod = mods.stream()
            .collect(Collectors.toMap(Identifiable::id, v -> v));

    /**
     * A map that associates each {@link Identifiable mod} to its class.
     */
    private final Map<Class<?>, Identifiable> classToMod = mods.stream()
            .collect(Collectors.toMap(Object::getClass, v -> v));

    /**
     * Returns the {@link MizuPlugin} singleton.
     *
     * @return the mizu plugin
     */
    @Override
    public Identifiable plugin() {
        return MizuPlugin.instance();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Identifiable> mods() {
        return mods;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Identifiable> @Nullable T find(String id) {
        return (T) idToMod.get(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Identifiable> @Nullable T find(Class<T> type) {
        return (T) classToMod.get(type);
    }
}
