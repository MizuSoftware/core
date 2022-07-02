package wtf.mizu.core.common

/**
 * The regex used to maintain a consistency between identifiers. Those may only
 * be composed of lower case letters and underscore. First and last characters
 * must be letters.
 *
 * @author Shyrogan
 * @since 0.0.1
 */
val IDENTIFIER_PATTERN = "^[a-z][a-z._]+[a-z]\$".toRegex()
