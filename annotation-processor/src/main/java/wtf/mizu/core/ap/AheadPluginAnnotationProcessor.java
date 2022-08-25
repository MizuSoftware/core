package wtf.mizu.core.ap;

import com.google.auto.service.AutoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import wtf.mizu.core.common.plugin.Plugin;
import wtf.mizu.core.common.plugin.PluginContainer;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.TypeElement;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

/**
 * The {@link AheadPluginAnnotationProcessor} takes the first element
 * annotated with the {@link Plugin} annotation and stores it as the
 * container for future processing.
 */
@AutoService(Processor.class)
@SupportedAnnotationTypes("wtf.mizu.core.common.plugin.Plugin")
public final class AheadPluginAnnotationProcessor extends AbstractProcessor {

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private static PluginContainer container;

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (final var e : roundEnv.getElementsAnnotatedWith(Plugin.class)) {
            final var plugin = e.getAnnotation(Plugin.class);
            final var id = plugin.value().equalsIgnoreCase("<auto>") ?
                    e.getSimpleName().toString().toLowerCase() :
                    plugin.value();
            final var desc = plugin.description().equalsIgnoreCase("<auto>") ?
                    id + ".desc" :
                    plugin.description();

            final Writer writer;
            try {
                writer = processingEnv.getFiler().createResource(
                        StandardLocation.CLASS_OUTPUT, "", PluginContainer.FILE, e
                ).openWriter();

                writer.write(gson.toJson(container = new PluginContainer(id, desc)));
                writer.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            break;
        }

        return true;
    }

    /**
     * Returns the current plugin's {@link PluginContainer container} processed.
     *
     * @return the container
     */
    public static PluginContainer container() {
        return container;
    }
}
