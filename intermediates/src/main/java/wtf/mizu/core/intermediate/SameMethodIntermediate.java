package wtf.mizu.core.intermediate;

import com.squareup.javapoet.*;
import wtf.mizu.oshanraina.intermediate.ContainerProcessingIntermediate;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.TypeKind;

import java.util.stream.Collectors;

import static javax.lang.model.element.ElementKind.METHOD;
import static javax.lang.model.element.Modifier.PUBLIC;

public class SameMethodIntermediate implements ContainerProcessingIntermediate {

    @Override
    public void process(TypeSpec.Builder builder, Element element, ClassName className) throws Exception {
        element.getEnclosedElements().stream()
                .filter(e -> e.getKind() == METHOD && e.getModifiers().contains(PUBLIC))
                .map(e -> (ExecutableElement) e)
                .map(this::copy)
                .forEach(builder::addMethod);
    }

    private MethodSpec copy(ExecutableElement e) {
        final var parameters = e.getParameters().stream()
                .map(v -> ParameterSpec.builder(ClassName.get(v.asType()),
                        v.getSimpleName().toString()))
                .map(ParameterSpec.Builder::build)
                .toList();
        final var parametersNames = parameters.stream()
                .map(p -> p.name)
                .collect(Collectors.joining(", "));
        final var code = e.getReturnType().getKind() == TypeKind.VOID ?
                CodeBlock.of("obj().$L($L);", e.getSimpleName().toString(),
                        parametersNames) :
                CodeBlock.of("return obj().$L($L);",
                        e.getSimpleName().toString(), parametersNames);

        return MethodSpec.methodBuilder(e.getSimpleName().toString())
                .addParameters(parameters)
                .returns(ClassName.get(e.getReturnType()))
                .addModifiers(e.getModifiers())
                .addCode(code)
                .build();
    }

}
