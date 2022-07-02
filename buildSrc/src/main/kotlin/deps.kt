private const val KOTLIN_VERSION = "1.7.0"

object Plugins {
    const val KOTLIN = KOTLIN_VERSION

    /**
     * Maven Central publishing plugin.
     */
    const val NEXUS = "1.0.0"
}

object Dependencies {
    const val KOTLIN = KOTLIN_VERSION

    val kotlinModules = arrayOf<String>("stdlib")

    /**
     * Testing framework.
     */
    const val JUPITER = "5.8.2"

    /**
     * Mizu's event bus.
     */
    const val KAWA = "0.3.0"
}
