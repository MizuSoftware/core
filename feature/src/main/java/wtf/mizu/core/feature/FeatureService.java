package wtf.mizu.core.feature;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;
import wtf.mizu.core.common.Identifiable;

import java.util.List;

/**
 * A {@link FeatureService} contains and manages a list of features.
 *
 * <p>
 *     Features are the object responsible of interacting with the game. They
 *     do it through events and <b>only through events</b>.
 * </p>
 */
public interface FeatureService {

    /**
     * The {@link Identifiable plugin container} of the plugin that manages
     * this {@link FeatureService}.
     *
     * @return the {@link Identifiable plugin container}
     */
    Identifiable plugin();

    /**
     * Returns a list of the features available in this service.
     *
     * @return the features
     */
    @Unmodifiable
    List<Identifiable> features();

    /**
     * Finds given {@link Identifiable feature's container} from its type.
     *
     * @param type the container's type
     * @return the container
     * @param <T> the container's type
     */
    @Nullable
    <T extends Identifiable> T find(Class<T> type);

    /**
     * Finds given {@link Identifiable feature's container} from its id.
     *
     * @param id the container's type
     * @return the container
     * @param <T> the container's type
     */
    @Nullable
    <T extends Identifiable> T find(String id);

}