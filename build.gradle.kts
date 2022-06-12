plugins {
    kotlin("jvm") version "1.7.0" apply false
}

group = "wtf.mizu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

subprojects {
    apply {
        plugin("java-library")
        plugin("org.jetbrains.kotlin.jvm")
    }

    group = parent!!.group
    version = parent!!.version

    repositories {
        mavenCentral()
    }

    val implementation by configurations
    dependencies {
        implementation(kotlin("stdlib-jdk8"))
    }
}