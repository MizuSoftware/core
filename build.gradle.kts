import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Plugins.KOTLIN apply false

    signing
    `maven-publish`
    id("io.github.gradle-nexus.publish-plugin") version Plugins.NEXUS
}

allprojects {
    with(Coordinates) {
        group = GROUP
        version = VERSION
    }
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
        val implementation by configurations
        val testImplementation by configurations

        with (Dependencies) {
            kotlinModules.forEach {
                implementation("org.jetbrains.kotlin", "kotlin-$it", KOTLIN)
            }

            testImplementation(platform("org.junit:junit-bom:$JUPITER"))
            testImplementation("org.junit.jupiter:junit-jupiter")
        }
    }

    configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.valueOf("VERSION_$JAVA_VERSION")
        targetCompatibility = sourceCompatibility

        withSourcesJar()
        withJavadocJar()
    }

    tasks {
        withType<Test> {
            useJUnitPlatform()
        }

        withType<JavaCompile> {
            options.release.set(JAVA_VERSION)
        }

        withType<KotlinCompile> {
            kotlinOptions.jvmTarget = "$JAVA_VERSION"
        }

        withType<Jar> {
            with(Coordinates) {
                archiveBaseName.set("${rootProject.name}.${project.name}")

                manifest.attributes += mapOf(
                    "Name" to GROUP.replace(".", "/") + "/",
                    "Specification-Title" to "${rootProject.name}:${project.name}",
                    "Specification-Version" to VERSION,
                    "Specification-Vendor" to VENDOR,

                    "Implementation-Title" to project.name,
                    "Specification-Version" to VERSION,
                    "Implementation-Vendor" to VENDOR,
                )
            }
        }
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
    snapshotRepositoryUrl.set(
        uri(
            "https://s01.oss.sonatype.org/content/repositories/snapshots/"
        )
    )

    // Skip this step if environment variables NEXUS_USERNAME or NEXUS_PASSWORD
    // aren't set.
    username.set(properties["NEXUS_USERNAME"] as? String ?: return@sonatype)
    password.set(properties["NEXUS_PASSWORD"] as? String ?: return@sonatype)
}
