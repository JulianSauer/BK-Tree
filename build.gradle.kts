import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.21"
    application
    id("io.ktor.plugin") version "2.2.3"
}

group = "com.julian-sauer"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

application {
    mainClass.set("com.juliansauer.bktree.MainKt")
}

ktor {
    fatJar {
        archiveFileName.set("bk-tree.jar")
    }
}
