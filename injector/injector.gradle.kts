import ProjectVersions.openosrsVersion
import ProjectVersions.rsversion

group = "com.openosrs"
version = 1.0

val vanillaDep: Configuration by configurations.creating

plugins {
    id("org.checkerframework") version "0.5.17"
    java
}

dependencies {
    vanillaDep(group = "net.runelite.rs", name = "vanilla", version = rsversion.toString())

    annotationProcessor(group = "org.projectlombok", name = "lombok", version = "1.18.18")

    compileOnly(group = "org.projectlombok", name = "lombok", version = "1.18.18")
    compileOnly(group ="org.checkerframework", name = "checker-qual", version = "3.11.0")

    implementation(group = "com.google.inject", name = "guice", version = "5.0.1") {
        exclude("aopalliance", "aopalliance")
    }
    implementation(group = "org.slf4j", name = "slf4j-api", version = "1.7.30")
    implementation(project(":deobfuscator"))
    implementation(project(":runescape-api"))
    implementation(project(":runescape-client"))
    implementation(project(":runelite-mixins"))

    implementation(group = "org.ow2.asm", name = "asm", version = "9.0")
    implementation(group = "org.ow2.asm", name = "asm-util", version = "9.0")
    implementation(group = "com.google.guava", name = "guava", version = "23.2-jre")
}

tasks.register<JavaExec>("inject") {
    main = "com.openosrs.injector.Injector"
    classpath = sourceSets["main"].runtimeClasspath
    args(vanillaDep.singleFile, openosrsVersion)
    outputs.upToDateWhen {
        false
    }
}