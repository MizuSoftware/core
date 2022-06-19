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

subprojects {
    apply {
        plugin("java-library")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("maven-publish")
        plugin("signing")
    }

    group = parent!!.group
    version = parent!!.version

    repositories {
        mavenLocal()
        mavenCentral()
    }

    dependencies {
        implementation("org.jetbrains.kotlin", "kotlin-stdlib", "1.7.0")

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

    publishing.publications {
        create("mavenJava", MavenPublication::class.java) {
            from(components["java"])
            groupId = project.group.toString()
            version = project.version.toString()

            signing {
                isRequired = project.properties["signing.keyId"] != null
                sign(this@create)
            }
        }
    }
}

// Configure publishing to Maven Central
nexusPublishing.repositories.sonatype {
    nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
    snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))

    // Skip this step if environment variables NEXUS_USERNAME or NEXUS_PASSWORD aren't set.
    username.set(properties["NEXUS_USERNAME"] as? String ?: return@sonatype)
    password.set(properties["NEXUS_PASSWORD"] as? String ?: return@sonatype)
}