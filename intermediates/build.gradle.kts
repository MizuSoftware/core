repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    api("wtf.mizu", "oshanraina", "0.1.0")
    api(project(":mod"))
    api("com.squareup", "javapoet", "1.13.0")
    api("com.google.code.gson", "gson", "2.9.1")
}