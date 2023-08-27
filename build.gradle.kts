plugins {
    kotlin("jvm") version "1.9.0"
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
    implementation("javax.xml.bind:jaxb-api:2.3.1")
    implementation("com.sun.xml.bind:jaxb-impl:2.3.3")
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