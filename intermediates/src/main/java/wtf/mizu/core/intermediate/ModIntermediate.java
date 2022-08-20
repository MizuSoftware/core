package wtf.mizu.core.intermediate;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import wtf.mizu.core.common.Describable;
import wtf.mizu.core.common.Identifiable;
import wtf.mizu.core.mod.Mod;
import wtf.mizu.oshanraina.intermediate.ContainerProcessingIntermediate;

import javax.lang.model.element.Element;

import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PUBLIC;

public class ModIntermediate implements ContainerProcessingIntermediate {

    @Override
    public void process(TypeSpec.Builder builder, Element element, ClassName className) throws Exception {
        // TODO (@lambdagg): inference
        final var feature = element.getAnnotation(Mod.class);
        if(feature == null) return;
        final var id = feature.value().equalsIgnoreCase("<INFERENCE>") ?
                element.getSimpleName().toString().toLowerCase()
                : feature.value();
        final var desc = feature.description().equalsIgnoreCase("<INFERENCE>") ?
                id + ".desc" : feature.description();

        builder.addSuperinterface(Identifiable.class);
        builder.addSuperinterface(Describable.class);

        builder.addMethod(MethodSpec.methodBuilder("id")
                .addJavadoc("{@inheritDoc}")
                .addModifiers(PUBLIC, FINAL)
                .returns(String.class)
                .addStatement("return \"$L\"", id)
                .build());

        builder.addMethod(MethodSpec.methodBuilder("description")
                .addJavadoc("{@inheritDoc}")
                .addModifiers(PUBLIC, FINAL)
                .returns(String.class)
                .addStatement("return \"$L\"", desc)
                .build());
    }

}
