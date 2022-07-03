dependencies {
    implementation(project(":common"))
    implementation(project(":configuration"))

    with(Dependencies) {
        arrayOf("api", "kotlin-listener-dsl").forEach {
            implementation("wtf.mizu.kawa", it, KAWA)
        }
    }
}
