plugins {
    id("java")
    kotlin("jvm")
    id("application")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.mybank"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass = "com.mybank.Main"
}

// Shadow task depends on Jar task, so these configs are reflected for Shadow as well
tasks.jar {
    manifest.attributes["Main-Class"] = "com.mybank.Main"
}

dependencies {
    implementation("redis.clients:jedis:5.0.2")

    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:5.12.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.12.0")
    testImplementation("org.testcontainers:testcontainers:1.16.0")
    testImplementation("org.testcontainers:junit-jupiter:1.16.0")

    implementation(kotlin("stdlib-jdk8"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
