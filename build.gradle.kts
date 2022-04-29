import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
}

group = "me.pedro.h"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1-native-mt")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.6.10")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1-native-mt")
    testImplementation("io.mockk:mockk:1.12.3")
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}