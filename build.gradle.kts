import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.9.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("java")
    application
}

group = "org.traum"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.sourceforge.plantuml:plantuml-mit:1.2023.10")
    implementation("org.docx4j:docx4j:6.1.2")
    implementation("jakarta.xml.bind:jakarta.xml.bind-api:2.3.3")
    implementation("org.glassfish.jaxb:jaxb-runtime:2.3.3")
//    implementation("com.sun.xml.bind:jaxb-impl:2.3.3")
    implementation("com.mohamedrejeb.ksoup:ksoup-html:0.2.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Class-Path"] = "libs/lib.jar"
    }
}

tasks.withType(ShadowJar::class).configureEach{
    archiveBaseName = "lib"
    archiveClassifier = ""
    archiveVersion = ""
}