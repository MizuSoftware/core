repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("com.google.auto.service:auto-service:1.0.1")
    annotationProcessor("com.google.auto.service:auto-service:1.0.1")
    api("wtf.mizu", "oshanraina", "0.1.0")
    api(project(":intermediates"))

    testAnnotationProcessor(project(":annotation-processor"))
}