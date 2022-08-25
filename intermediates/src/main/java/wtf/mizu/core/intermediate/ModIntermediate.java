package wtf.mizu.core.intermediate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import wtf.mizu.core.common.Describable;
import wtf.mizu.core.common.Identifiable;
import wtf.mizu.core.common.plugin.PluginContainer;
import wtf.mizu.core.mod.Mod;
import wtf.mizu.oshanraina.intermediate.ContainerProcessingIntermediate;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;

import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PUBLIC;

public class ModIntermediate implements ContainerProcessingIntermediate {

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private final PluginContainer container;

    public ModIntermediate(PluginContainer container) {
        this.container = container;
    }

    @Override
    public void process(TypeSpec.Builder builder, Element element,
                        ClassName className, ProcessingEnvironment env) throws Exception {
        // TODO (@lambdagg): inference
        final var mod = element.getAnnotation(Mod.class);
        if(mod == null) return;
        final var id = mod.value().equalsIgnoreCase("<auto>") ?
                element.getSimpleName().toString().toLowerCase() :
                mod.value();
        final var desc = mod.description().equalsIgnoreCase("<auto>") ?
                id + ".desc" : mod.description();

        builder.addSuperinterface(Identifiable.class);
        builder.addSuperinterface(Describable.class);

        builder.addMethod(MethodSpec.methodBuilder("id")
                .addJavadoc("{@inheritDoc}")
                .addModifiers(PUBLIC, FINAL)
                .returns(String.class)
                .addStatement("return \"$L\"", container.id() + "." + id)
                .build());

        builder.addMethod(MethodSpec.methodBuilder("description")
                .addJavadoc("{@inheritDoc}")
                .addModifiers(PUBLIC, FINAL)
                .returns(String.class)
                .addStatement("return \"$L\"", container.id() + "." + desc)
                .build());
    }

}
