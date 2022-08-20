package wtf.mizu.core.ap;

import com.google.auto.service.AutoService;
import wtf.mizu.core.feature.Feature;
import wtf.mizu.core.intermediate.FeatureIntermediate;
import wtf.mizu.core.intermediate.SingletonIntermediate;
import wtf.mizu.oshanraina.ContainerProcessingPipeline;
import wtf.mizu.oshanraina.step.ContainerProcessingStage;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.TypeElement;
import java.util.Set;

import static wtf.mizu.oshanraina.step.ContainerProcessingStage.Initialization.withSuffix;
import static wtf.mizu.oshanraina.step.ContainerProcessingStage.Writing.usingJavaPoet;

@AutoService(Processor.class)
@SupportedAnnotationTypes("wtf.mizu.core.feature.Feature")
public final class FeatureAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        try {
            ContainerProcessingPipeline.builder(Feature.class)
                    .initialization(withSuffix("Feature"))
                    .then(new FeatureIntermediate())
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
