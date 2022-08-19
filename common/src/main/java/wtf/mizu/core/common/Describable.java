package wtf.mizu.core.common;

/**
 * An object that can be described using its {@link #description()}.
 *
 * @author Shyrogan
 * @since 0.0.1
 */
public interface Describable {

    /**
     * A <b>not always unique</b> string used to identify this object.
     * <p>
     * To maintain a consistency between descriptions, those <i>must</i> comply with
     * the regex expression such as coded into the
     * {@link Identifiable#IDENTIFIER_PATTERN IDENTIFIER_PATTERN} constant.
     * <p>
     * Some <b>good</b> examples for Describable would be:
     * <p>
     * {@code domain.object.desc}
     * {@code domain.object.more.desc}
     * {@code domain.object.more.even_more.desc}
     *
     * @author Shyrogan
     * @since 0.0.1
     */
    String description();

}
