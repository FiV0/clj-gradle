import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    // Apply the java-library plugin for API and implementation separation.
    `java-library`
    id("dev.clojurephant.clojure") version "0.8.0-beta.7"
    kotlin("jvm") version "1.9.0"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
    maven { url = uri("https://repo.clojars.org/") }
}

dependencies {
    // Use JUnit test framework.
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")

    // kotlin
    implementation(kotlin("stdlib-jdk8"))

    // Clojure
    implementation( "org.clojure", "clojure", "1.12.0-rc1")
    testRuntimeOnly("dev.clojurephant", "jovial", "0.4.1")
    nrepl("cider", "cider-nrepl", "0.49.1")
}

tasks.test {
    useJUnitPlatform {
        excludeTags("integration")
    }
}

tasks.clojureRepl {
    classpath = files(classpath, sourceSets["main"].output)
    middleware.add("cider.nrepl/cider-middleware")
}

tasks.checkClojure {
    enabled = false
}


// Apply a specific Java toolchain to ease working on different environments.
java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)

        java {
            freeCompilerArgs.add("-Xjvm-default=all")
        }
    }
}
