package wtf.mizu.core.common;

import java.util.regex.Pattern;

/**
 * An object that can be identified using its {@link #id()}.
 *
 * @author Shyrogan
 * @since 0.0.1
 */
public interface Identifiable {

    /**
     * The regex used to maintain a consistency between identifiers. Those may only
     * be composed of lower case letters and underscore. First and last characters
     * must be letters.
     *
     * @since 0.0.1
     */
    Pattern IDENTIFIER_PATTERN = Pattern.compile("^[a-z][a-z0-9-_.]{1,63}$");

    /**
     * A <b>unique</b> string used to identify this object.
     * <p>
     * To maintain a consistency between descriptions, those <i>must</i> comply with
     * the regex expression such as coded into the {@link #IDENTIFIER_PATTERN} constant.
     * <p>
     * Some <b>good</b> examples for Identifier would be:
     * <p>
     * {@code domain.object}
     * {@code domain.object.more}
     * {@code domain.object.more.even_more}
     *
     * @author Shyrogan
     * @since 0.0.1
     */
    String id();

}