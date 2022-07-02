dependencies {
    implementation(project(":common"))
    implementation(project(":configuration"))

    with(Dependencies) {
        arrayOf("api", "kotlin-listener-dsl").forEach {
            api("wtf.mizu.kawa", it, KAWA)
        }
    }
}
