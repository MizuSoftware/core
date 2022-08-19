plugins {
    `java-library`
    `maven-publish`
}

group   = "wtf.mizu"
version = "0.1.0"

allprojects {
    repositories {
        // Most of the libraries designed by Mizu are published on maven
        // central.
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java-library")

    dependencies {
        implementation("com.google.guava", "guava", "31.1-jre")
        implementation("org.jetbrains", "annotations", "23.0.0")
    }
}

publishing {
    publications {
        create("mavenJava", MavenPublication::class.java) {
            from(components["java"])
        }
    }
}