import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.dokka.gradle.DokkaTaskPartial
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URL
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

plugins {
    with(Plugins) {
        id("org.ajoberstar.grgit") version GRGIT

        kotlin("jvm") version KOTLIN apply false

        id("org.jetbrains.dokka") version DOKKA

        signing
        `maven-publish`
        id("io.github.gradle-nexus.publish-plugin") version NEXUS
    }
}

allprojects {
    with(Coordinates) {
        project.group = group
        project.version = version
    }

    repositories {
        mavenLocal()
        mavenCentral()
    }
}

// Normalizing project version
val prettyProjectVersion = rootProject.version.toString().run {
    Regex("(\\d+\\.\\d+\\.\\d+).*").matchEntire(this)
        ?.run { groupValues[1] }
        ?: throw Error(
            "Version '$this' does not match version pattern, e.g. 1.0.0-QUALIFIER"
        )
}

// The latest commit ID
val buildRevision: String = grgit.log()[0].id ?: "dev"

subprojects {
    apply {
        plugin("java-library")
        plugin("org.jetbrains.kotlin.jvm")

        plugin("org.jetbrains.dokka")

        plugin("signing")
        plugin("maven-publish")
    }

    val sourceSets = project.extensions.getByType<SourceSetContainer>()

    dependencies {
        val implementation by configurations
        val testImplementation by configurations

        with(Dependencies) {
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

        withType<DokkaTaskPartial>().configureEach {
            dokkaSourceSets.configureEach {
                displayName.set("${Coordinates.name}/${project.name} on ${Coordinates.gitHost}")

                skipDeprecated.set(false)
                includeNonPublic.set(false)
                skipEmptyPackages.set(true)
                reportUndocumented.set(true)
                suppressObviousFunctions.set(true)

                // Link the source to the documentation
                sourceLink {
                    localDirectory.set(file("src"))
                    remoteUrl.set(URL("${Coordinates.gitUrl}/tree/${Coordinates.mainGitBranch}/${project.name}/src"))
                }

                /**
                 * @see config.Dokka.externalDocumentations
                 */
                config.Dokka.externalDocumentations.forEach {
                    externalDocumentationLink { url.set(URL(it)) }
                }
            }
        }

        withType<Jar> {
            archiveBaseName.set("${rootProject.name}.${project.name}")

            val buildTimeAndDate = OffsetDateTime.now()
            val buildDate = DateTimeFormatter.ISO_LOCAL_DATE.format(buildTimeAndDate)
            val buildTime = DateTimeFormatter.ofPattern("HH:mm:ss.SSSZ").format(buildTimeAndDate)

            val javaVersion = System.getProperty("java.version")
            val javaVendor = System.getProperty("java.vendor")
            val javaVmVersion = System.getProperty("java.vm.version")

            with(Coordinates) {
                manifest.attributes(
                    "Name" to group.replace(".", "/") + "/",

                    "Created-By" to "$javaVersion ($javaVendor $javaVmVersion)",
                    "Build-Date" to buildDate,
                    "Build-Time" to buildTime,
                    "Build-Revision" to buildRevision,

                    "Specification-Title" to "$name:${project.name}",
                    "Specification-Version" to prettyProjectVersion,
                    "Specification-Vendor" to vendor,

                    "Implementation-Title" to project.name,
                    "Implementation-Version" to buildRevision,
                    "Implementation-Vendor" to vendor,

                    "Bundle-Name" to name,
                    "Bundle-Description" to description,
                    "Bundle-DocURL" to gitUrl,
                    "Bundle-Vendor" to vendor,
                    "Bundle-SymbolicName" to "$group.$name",
                )
            }

            from("LICENSE")
        }

        // Source artifact, including everything the 'main' does but not compiled.
        create("sourcesJar", Jar::class) {
            group = "build"
            archiveClassifier.set("sources")

            from(sourceSets["main"].allSource)

            from("LICENSE")
        }

        // The Javadoc artifact, containing the Dokka output and the LICENSE file.
        create("javadocJar", Jar::class) {
            group = "build"
            archiveClassifier.set("javadoc")

            val dokkaHtml by getting
            dependsOn(dokkaHtml)

            from(dokkaHtml)
            from("LICENSE")
        }
    }

    publishing.publications {
        create("mavenJava", MavenPublication::class.java) {
            from(components["java"])

            groupId = project.group.toString()
            version = project.version.toString()

            with(Coordinates) {
                pom {
                    name.set(name)
                    description.set(this@with.description)
                    url.set(gitUrl)

                    with(Pom) {
                        licenses {
                            licenses.forEach {
                                license {
                                    name.set(it.name)
                                    url.set(it.url)
                                    distribution.set(it.distribution)
                                }
                            }
                        }

                        developers {
                            developers.forEach {
                                developer {
                                    id.set(it.id)
                                    name.set(it.name)
                                }
                            }
                        }
                    }

                    scm {
                        connection.set("scm:git:git://$gitHost/$repoId.git")
                        developerConnection.set(
                            "scm:git:ssh://$gitHost/$repoId.git"
                        )
                        url.set(gitUrl)
                    }
                }
            }

            signing {
                isRequired = project.properties["signing.keyId"] != null
                sign(this@create)
            }
        }
    }
}

afterEvaluate {
    tasks.dokkaHtmlMultiModule {
        val moduleFile = File(
            projectDir,
            "MODULE.temp.${java.util.UUID.randomUUID()}.md"
        )

        // In order to have a description on the rendered docs, we have to have
        // a file with the # Module thingy in it. That's what we're
        // automagically creating and deleting here.
        run {
            doFirst {
                moduleFile.deleteOnExit()
                moduleFile.writeText(
                    "# ${Coordinates.name}\n${Coordinates.description}"
                )
            }

            doLast { moduleFile.delete() }
        }

        moduleName.set(Coordinates.name)
        includes.from(moduleFile.path)
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
