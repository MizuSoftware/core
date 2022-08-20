package wtf.mizu.client.feature;

import com.google.auto.service.AutoService;
import org.jetbrains.annotations.Nullable;
import wtf.mizu.client.MizuPlugin;
import wtf.mizu.client.feature.category.DummyFeature;
import wtf.mizu.core.common.Identifiable;
import wtf.mizu.core.feature.FeatureService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
@AutoService(FeatureService.class)
public class MizuFeatureService implements FeatureService {

    private final List<Identifiable> features = List.of(
        DummyFeature.instance()
    );

    private final Map<String, Identifiable> idToFeature = features.stream()
            .collect(Collectors.toMap(Identifiable::id, v -> v));

    private final Map<Class<?>, Identifiable> classToFeature = features.stream()
            .collect(Collectors.toMap(Object::getClass, v -> v));

    @Override
    public Identifiable plugin() {
        return MizuPlugin.instance();
    }

    @Override
    public List<Identifiable> features() {
        return features;
    }

    @Override
    public <T extends Identifiable> @Nullable T find(String id) {
        return (T) idToFeature.get(id);
    }

    @Override
    public <T extends Identifiable> @Nullable T find(Class<T> type) {
        return (T) classToFeature.get(type);
    }
}
