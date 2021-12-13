plugins {
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
dependencies {

    //modules
    implementation(project(":logger"))

    //tests
    testImplementation("io.mockk:mockk:${Versions.mockk}")
    implementation("junit:junit:${Versions.testJUnit}")
}