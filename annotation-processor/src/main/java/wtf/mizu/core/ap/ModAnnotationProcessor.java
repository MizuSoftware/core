package wtf.mizu.core.ap;

import com.google.auto.service.AutoService;
import wtf.mizu.core.mod.Mod;
import wtf.mizu.core.intermediate.ModIntermediate;
import wtf.mizu.core.intermediate.SingletonIntermediate;
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
@SupportedAnnotationTypes("wtf.mizu.core.mod.Mod")
public final class ModAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        try {
            ContainerProcessingPipeline.builder(Mod.class)
                    .initialization(withSuffix("Mod"))
                    .then(new ModIntermediate())
                    .then(new SingletonIntermediate())
                    .writing(usingJavaPoet(processingEnv))
                    .create()
                    .tryProcessing(roundEnv);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
