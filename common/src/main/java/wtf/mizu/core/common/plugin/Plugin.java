package wtf.mizu.core.common.plugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.SOURCE;

@Target(ElementType.TYPE)
@Retention(SOURCE)
public @interface Plugin {

    /**
     * A <b>unique</b> string used to identify this plugin.
     * <p>
     * To maintain a consistency between ids, those <i>must</i>
     * comply  with the regex expression such as coded into the
     * {@link wtf.mizu.core.common.Identifiable#IDENTIFIER_PATTERN
     * IDENTIFIER_PATTERN} constant.
     * <p>
     * Some <b>good</b> examples for Identifier would be:
     * <p>
     * {@code domain.object}
     * {@code domain.object.more}
     * {@code domain.object.more.even_more}
     * <p>
     * By default, the value is {@code <auto>} telling the annotation
     * processor to figure out a value using the class' name.
     *
     * @author Shyrogan
     * @since 0.0.1
     */
    String value() default "<auto>";


    /**
     * A <b>non-unique</b> string used to describe this plugin.
     * <p>
     * To maintain a consistency between descriptions, those <i>must</i>
     * comply  with the regex expression such as coded into the
     * {@link wtf.mizu.core.common.Identifiable#IDENTIFIER_PATTERN
     * IDENTIFIER_PATTERN} constant.
     * <p>
     * Some <b>good</b> examples for a describable would be:
     * <p>
     * {@code domain.object}
     * {@code domain.object.more}
     * {@code domain.object.more.even_more}
     * <p>
     * By default, the value is {@code <auto>} telling the annotation
     * processor to figure out a value using the class' name.
     *
     * @author Shyrogan
     * @since 0.0.1
     */
    String description() default "<auto>";

}