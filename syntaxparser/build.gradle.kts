plugins {
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
dependencies {
    implementation(project(mapOf("path" to ":logger")))

    testImplementation("io.mockk:mockk:1.11.0")
    implementation("junit:junit:${Versions.junit}")
    implementation(project(mapOf("path" to ":core")))
}