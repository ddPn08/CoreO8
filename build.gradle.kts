plugins {
    kotlin("jvm") version("1.6.0")
    id("com.github.johnrengelman.shadow") version "7.1.1"
}

group = "run.dn5.sasa"
version = "1.0-beta.2"
description = "CoreO8"
val artifactName =  "${rootProject.name}-${rootProject.version}.jar"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://jitpack.io")
}

dependencies{
    compileOnly("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT")
    compileOnly("com.github.LeonMangler:SuperVanish:6.2.7")
    compileOnly("net.luckperms:api:5.4")
}
tasks {
    shadowJar{
        archiveFileName.set(artifactName)
    }

    register("preDebug"){
        dependsOn("clean", "shadowJar")
        doLast {
            copy {
                from("$buildDir/libs/${artifactName}")
                into(".debug/plugins")
            }
        }
    }

    processResources {
        filteringCharset = "UTF-8"
        filesMatching("plugin.yml") {
            expand(mapOf(
                "name" to rootProject.name,
                "id" to rootProject.name.toLowerCase(),
                "group" to project.group,
                "version" to project.version,
                "description" to project.description,
                "author" to "ddPn08"
            ))
        }
    }
    compileKotlin {
        kotlinOptions.jvmTarget = "17"
    }
}