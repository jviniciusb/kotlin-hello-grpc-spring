import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlinVersion: String by project
val grpcVersion: String by project
val grpcKotlinVersion: String by project
val coroutinesVersion: String by project
val grpcSpringBootStarterVersion: String by project
val authToken: String by project

plugins {
    id("maven-publish")
    id("org.springframework.boot") version "2.5.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.5.30"
    kotlin("plugin.spring") version "1.5.30"
}

group = "com.jviniciusb"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
    maven {
        setUrl("https://jitpack.io")
    }
}

dependencies {
    // spring
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // grpc
    implementation("io.grpc:grpc-kotlin-stub:$grpcKotlinVersion")
    implementation("io.github.lognet:grpc-spring-boot-starter:$grpcSpringBootStarterVersion")

    // kotlin coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

    // proto stubs lib
    implementation("com.github.jviniciusb:proto-hello-grpc:1.0.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}