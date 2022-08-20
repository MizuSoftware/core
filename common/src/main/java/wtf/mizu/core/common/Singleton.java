package wtf.mizu.core.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Marks a class as a {@code singleton}. This means the annotation-processor
 * will create an instance for it.
 */
@Target(ElementType.TYPE)
@Retention(SOURCE)
public @interface Singleton {
}
