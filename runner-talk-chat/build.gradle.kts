import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.diffplug.spotless") version "6.23.3"
    id("jacoco")
    kotlin("jvm") version "1.9.20"
    kotlin("plugin.spring") version "1.9.20"
}

springBoot {
    buildInfo()
}

group = "com.clp-runner.runner-talk-chat"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

sourceSets {
    main {
        java {
            srcDirs(arrayOf("${projectDir}/src/main/kotlin", "${projectDir}/build/generated"))
        }
    }
}

spotless {
    val excludeFiles = arrayOf(".idea/**/*.*", ".vscode/**/*.*")

    java {
        targetExclude("build/**/*.kt")
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
            "\\#",
            "org.junit",
            "\\#org.junit",
            "com.wesang",
            "\\#com.clprunner.runnertalkchat",
        )
        indentWithTabs(2)
        indentWithSpaces(2)
        trimTrailingWhitespace()
        endWithNewline()
    }
    format("yaml") {
        target("**/*.yaml", "**/*.yml")
        targetExclude(*excludeFiles)
        prettier().configFile("${rootDir}/.prettierrc")
    }
    format("xml") {
        target("**/*.xml")
        targetExclude(*excludeFiles)
        prettier().config(mapOf("parser" to "html", "printWidth" to 160)).configFile("${rootDir}/.prettierrc")
    }
    format("md") {
        target("**/*.md")
        targetExclude(*excludeFiles)
        prettier().config(mapOf("printWidth" to 160)).configFile("${rootDir}/.prettierrc")
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo.spring30x:4.11.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

}

tasks.test {
    useJUnitPlatform()
    if (JavaVersion.current().isCompatibleWith(JavaVersion.VERSION_13)) {
        jvmArgs("-XX:+AllowRedefinitionToAddDeleteMethods")
    }
    systemProperties["spring.profiles.active"] = "test"
}

tasks.jacocoTestReport {
    reports {
        html.required.set(true)
        xml.required.set(true)
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
                minimum = "0.85".toBigDecimal()
            }
            excludes = listOf(
                "com.clprunner.runnertalkchat.RunnerTalkChatApplication",
            )
        }
    }
}

val testCoverage by tasks.registering {
    group = "verification"
    description = "Runs the unit tests with coverage"
    dependsOn("test",
        "jacocoTestReport",
        "jacocoTestCoverageVerification")
    tasks["jacocoTestReport"].mustRunAfter(tasks["test"])
    tasks["jacocoTestCoverageVerification"].mustRunAfter(tasks["jacocoTestReport"])
}

val testAll by tasks.registering {
    group = "verification"
    description = "Runs all tests"
    dependsOn("spotlessCheck",
        "test",
        "jacocoTestReport",
        "jacocoTestCoverageVerification")
    tasks["test"].mustRunAfter(tasks["spotlessCheck"])
    tasks["jacocoTestReport"].mustRunAfter(tasks["test"])
    tasks["jacocoTestCoverageVerification"].mustRunAfter(tasks["jacocoTestReport"])
}

val jar: Jar by tasks
jar.enabled = false

tasks.bootJar {
    dependsOn(copyHermesMessageProcessorDependency)
    exclude("dd-java-agent-*.jar")
}

val copyHermesMessageProcessorDependency by tasks.registering(Copy::class) {
    from(configurations.compileClasspath)
    into("$buildDir/libs")
    include("dd-java-agent-*.jar")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
