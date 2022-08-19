package wtf.mizu.core.ap;

import com.google.auto.service.AutoService;
import wtf.mizu.core.common.Plugin;
import wtf.mizu.core.intermediate.PluginIntermediate;
import wtf.mizu.oshanraina.ContainerProcessingPipeline;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.TypeElement;
import java.util.Set;

import static wtf.mizu.oshanraina.step.ContainerProcessingStage.Initialization.withSuffix;
import static wtf.mizu.oshanraina.step.ContainerProcessingStage.Writing.usingJavaPoet;

@AutoService(Processor.class)
@SupportedAnnotationTypes("wtf.mizu.core.common.Plugin")
public final class PluginAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        try {
            ContainerProcessingPipeline.builder(Plugin.class)
                    .initialization(withSuffix("Plugin"))
                    .then(new PluginIntermediate())
                    .writing(usingJavaPoet(processingEnv))
                    .create()
                    .tryProcessing(roundEnv);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
