/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java application project to get you started.
 * For more details on building Java & JVM projects, please refer to https://docs.gradle.org/8.3/userguide/building_java_projects.html in the Gradle documentation.
 */

plugins {
  // Apply the application plugin to add support for building a CLI application in Java.
  application
  `java-library`
  // org.openjfx.javafxplugin
  // org.beryx.jlink
  id("org.openjfx.javafxplugin") version "0.0.10"
}

repositories {
  // Use Maven Central for resolving dependencies.
  mavenCentral()
}

dependencies {
  // Use JUnit Jupiter for testing.
  testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")

  testRuntimeOnly("org.junit.platform:junit-platform-launcher")

  // This dependency is used by the application.
  implementation("com.google.guava:guava:32.1.1-jre")
  implementation("com.fasterxml.jackson.core:jackson-databind:2.13.0")
  implementation("com.github.javafaker:javafaker:1.0.2")
}

// Apply a specific Java toolchain to ease working on different environments.
java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(21))
  }
}

javafx {
  version = "21.0.1"
  modules("javafx.controls", "javafx.fxml")
}

application {
  // Define the main class for the application.
  mainClass.set("medialib.App")
}

tasks.named<Test>("test") {
  // Use JUnit Platform for unit tests.
  useJUnitPlatform()
}
tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}
