plugins {
    kotlin("jvm") version Plugins.KOTLIN apply false

    signing
    `maven-publish`
    id("io.github.gradle-nexus.publish-plugin") version Plugins.NEXUS
}

allprojects {
    group = "wtf.mizu.core"
    version = "0.0.1"
}

subprojects {
    apply {
        plugin("java-library")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("signing")
        plugin("maven-publish")
    }

    repositories {
        mavenLocal()
        mavenCentral()
    }

    dependencies {
        with (Dependencies) {
            kotlinModules.forEach {
                "implementation"("org.jetbrains.kotlin", "kotlin-$it", KOTLIN)
            }

            "testImplementation"(platform("org.junit:junit-bom:$JUPITER"))
            "testImplementation"("org.junit.jupiter:junit-jupiter")
        }
    }

    configure<JavaPluginExtension> {
        withSourcesJar()
        withJavadocJar()
    }

    tasks.withType<Test> {
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
