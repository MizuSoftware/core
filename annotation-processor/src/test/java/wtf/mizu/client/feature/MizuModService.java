package wtf.mizu.client.feature;

import com.google.auto.service.AutoService;
import org.jetbrains.annotations.Nullable;
import wtf.mizu.client.MizuPlugin;
import wtf.mizu.client.feature.category.DummyFeature;
import wtf.mizu.core.common.Identifiable;
import wtf.mizu.core.mod.ModService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This is a simple implementation of the {@link ModService feature
 * service}.
 * <p>
 * Using Google's {@link AutoService} annotation, we don't have to do
 * anything except annotate the class for it to work properly.
 * <p>
 * The {@code features instances} are loaded inside the {@link this#features}
 * list and then {@link Map} to retrieve them are generated from it.
 */
@SuppressWarnings("ALL")
@AutoService(ModService.class)
public final class MizuModService implements ModService {

    /**
     * The {@link Identifiable features} list.
     */
    private final List<Identifiable> features = List.of(
        new DummyFeature()
    );

    /**
     * A map that associates each {@link Identifiable feature} to its id.
     */
    private final Map<String, Identifiable> idToFeature = features.stream()
            .collect(Collectors.toMap(Identifiable::id, v -> v));

    /**
     * A map that associates each {@link Identifiable feature} to its class.
     */
    private final Map<Class<?>, Identifiable> classToFeature = features.stream()
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
    public List<Identifiable> features() {
        return features;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Identifiable> @Nullable T find(String id) {
        return (T) idToFeature.get(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Identifiable> @Nullable T find(Class<T> type) {
        return (T) classToFeature.get(type);
    }
}
