plugins {
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
dependencies {
    implementation("junit:junit:${Versions.junit}")
    implementation(project(mapOf("path" to ":core")))
}