import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.diffplug.spotless") version "6.23.3"
    id("jacoco")
    kotlin("jvm") version "1.9.20"
    kotlin("plugin.spring") version "1.9.20"
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
}

group = "com.clp-runner.runner-talk-core"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

sourceSets {
    main {
        java {
            srcDirs(arrayOf("${projectDir}/src/main/java", "${projectDir}/build/generated"))
        }
    }
}

spotless {
    val excludeFiles = arrayOf(".idea/**/*.*", ".vscode/**/*.*")

    java {
        targetExclude("build/**/*.java")
        removeUnusedImports()
        replaceRegex("Remove wildcard imports", "import\\s+[^\\*\\s]+\\*;(\\r\\n|\\r|\\n)", "$1")
        googleJavaFormat()
        importOrder(
            "java",
            "jakarta",
            "javax",
            "lombok",
            "org.springframework",
            "",
            "org.junit",
            "com.runner-talk",
            "\\#",
            "\\#org.junit",
            "\\#com.runner-talk",
        )
        indentWithTabs(2)
        indentWithSpaces(4)
        trimTrailingWhitespace()
        endWithNewline()
    }
    format("yaml") {
        target("**/*.yaml", "**/*.yml")
        targetExclude(*excludeFiles)
        prettier().configFile("../.prettierrc")
    }
    format("xml") {
        target("**/*.xml")
        targetExclude(*excludeFiles)
        prettier().config(mapOf("parser" to "html", "printWidth" to 160)).configFile("../.prettierrc")
    }
    format("md") {
        target("**/*.md")
        targetExclude(*excludeFiles)
        prettier().config(mapOf("printWidth" to 160)).configFile("../.prettierrc")
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.1.5"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.hibernate.javax.persistence:hibernate-jpa-2.1-api:1.0.2.Final")
    implementation("com.querydsl:querydsl-mongodb:4.4.0")
}

tasks.bootJar {
    // Disable bootJar execution for this project
    enabled = false
}

tasks.test {
    useJUnitPlatform()
    systemProperties["spring.profiles.active"] = "test"
}

tasks.jacocoTestReport {
    reports {
        html.required.set(true)
        xml.required.set(false)
        csv.required.set(false)
    }
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            element = "CLASS"
            limit {
                counter = "LINE"
                value = "COVEREDRATIO"
                minimum = "0.0".toBigDecimal()
            }
            excludes = listOf(
            )
        }
    }
}

val testCoverage by tasks.registering {
    group = "verification"
    description = "Runs the unit tests with coverage"
    dependsOn(":test", ":jacocoTestReport", ":jacocoTestCoverageVerification")
    tasks["jacocoTestReport"].mustRunAfter(tasks["test"])
    tasks["jacocoTestCoverageVerification"].mustRunAfter(tasks["jacocoTestReport"])
}

val testAll by tasks.registering {
    group = "verification"
    description = "Runs all tests"
    dependsOn(":spotlessCheck", ":test", ":jacocoTestReport", ":jacocoTestCoverageVerification",":checkPackageDependencies")

    tasks["test"].mustRunAfter(tasks["spotlessCheck"])
    tasks["checkPackageDependencies"].mustRunAfter(tasks["spotlessCheck"])
    tasks["jacocoTestReport"].mustRunAfter(tasks["test"])
    tasks["jacocoTestCoverageVerification"].mustRunAfter(tasks["jacocoTestReport"])
}

val copyDependency by tasks.registering(Copy::class) {
    from(configurations.compileClasspath.get().filter {
        it.name.contains("dd-java-agent")
    })
    into("$buildDir/libs")
}
