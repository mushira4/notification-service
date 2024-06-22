plugins {
    id("java")
    kotlin("jvm")
    id("application")
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"

    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.mybank"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass = "com.mybank.motification.ConsoleApplication"
}

// Shadow task depends on Jar task, so these configs are reflected for Shadow as well
tasks.jar {
    manifest.attributes["Main-Class"] = "com.mybank.motification.ConsoleApplication"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("redis.clients:jedis:5.1.3")

    //Documentation
    implementation("io.springfox:springfox-boot-starter:3.0.0")
    implementation("org.springdoc:springdoc-openapi-ui:1.6.11")
    implementation("org.springdoc:springdoc-openapi-data-rest:1.6.14")
    implementation("javax.servlet:javax.servlet-api:4.0.1")

    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:5.12.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.12.0")
    testImplementation("org.testcontainers:testcontainers:1.19.8")
    testImplementation("org.testcontainers:junit-jupiter:1.19.8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
