private const val KOTLIN_VERSION = "1.7.0"

object Plugins {
    const val KOTLIN = KOTLIN_VERSION

    /**
     * Git repo informations.
     */
    const val GRGIT = "5.0.0"

    /**
     * Documentation generation.
     */
    const val DOKKA = KOTLIN

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
    const val KAWA = "0.3.1"
}
