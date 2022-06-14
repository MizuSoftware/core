plugins {
    `java-library`
    `maven-publish`
    signing
    id("io.github.gradle-nexus.publish-plugin") version "1.0.0"
    id("com.github.jmongard.git-semver-plugin") version "0.4.2"
    kotlin("jvm") version "1.7.0" apply false
}

group = "wtf.mizu.core"
version = semver.version

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
        mavenLocal()
    }

    val implementation by configurations
    dependencies {
        implementation(kotlin("stdlib-jdk8"))
        testImplementation(platform("org.junit:junit-bom:5.8.2"))
        testImplementation("org.junit.jupiter:junit-jupiter")
    }

    java {
        withSourcesJar()
        withJavadocJar()
    }

    tasks.test {
        useJUnitPlatform()
    }
}