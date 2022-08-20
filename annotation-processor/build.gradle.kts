repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("com.google.auto.service:auto-service:1.0.1")
    annotationProcessor("com.google.auto.service:auto-service:1.0.1")
    implementation("wtf.mizu", "oshanraina", "0.1.0")
    implementation(project(":common"))
    implementation(project(":mod"))
    implementation(project(":intermediates"))

    testAnnotationProcessor(project(":annotation-processor"))
}