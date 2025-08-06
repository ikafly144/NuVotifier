plugins {
    `kotlin-dsl`
    kotlin("jvm") version "2.2.0"
}

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven {
        name = "sponge"
        url = uri("https://repo.spongepowered.org/repository/maven-public/")
    }
    maven {
        name = "fabric"
        url = uri("https://maven.fabricmc.net/")
    }
}

dependencies {
    implementation(gradleApi())
    implementation("org.ajoberstar.grgit:grgit-gradle:5.3.2")
    implementation("com.gradleup.shadow:com.gradleup.shadow.gradle.plugin:9.0.0-SNAPSHOT")
    implementation("org.jfrog.buildinfo:build-info-extractor-gradle:4.34.2")
    implementation("org.spongepowered.gradle.plugin:org.spongepowered.gradle.plugin.gradle.plugin:2.3.0")
    implementation("net.fabricmc:fabric-loom:1.11.4")
    implementation("com.jfrog.artifactory:com.jfrog.artifactory.gradle.plugin:6.0.0")
}
