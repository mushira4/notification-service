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
    if (project.findProperty("chooseMain") != null) {
        if (project.findProperty("chooseMain") == "rest") {
            mainClass = "com.mybank.notification.RestServiceApplication"
        } else if (project.findProperty("chooseMain") == "console") {
            mainClass = "com.mybank.notification.ConsoleApplication"
        }
    } else {
        mainClass = "com.mybank.notification.RestServiceApplication"
    }
}

tasks.bootJar {
    launchScript {
        enabled = true
    }
    enabled = true

    if (project.findProperty("chooseMain") != null) {
        if (project.findProperty("chooseMain") == "rest") {
            archiveFileName = "notification-service-rest-${archiveVersion.get()}.${archiveExtension.get()}"
        } else if (project.findProperty("chooseMain") == "console") {
            archiveFileName = "notification-service-console-${archiveVersion.get()}.${archiveExtension.get()}"
        }
    } else {
        archiveFileName = "notification-service-${archiveVersion.get()}.${archiveExtension.get()}"
    }
}

// Shadow task depends on Jar task, so these configs are reflected for Shadow as well
tasks.shadowJar {
    manifest.attributes["Main-Class"] = project.findProperty("chooseMain").toString()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-loader:3.3.0")
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("redis.clients:jedis:5.1.3")

    //Documentation
    implementation("javax.servlet:javax.servlet-api:4.0.1")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")

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
