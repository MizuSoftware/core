import org.gradle.kotlin.dsl.version

private const val kotlinVersion = "1.7.0"

object Plugins {
    const val KOTLIN = kotlinVersion

    const val NEXUS = "1.0.0"
}

object Dependencies {
    const val KOTLIN = kotlinVersion

    const val JUPITER = "5.8.2"

    val kotlinModules = arrayOf<String>("stdlib")
}
