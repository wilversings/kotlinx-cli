val mavenUser: String by project
val mavenPassword: String by project

buildscript {
    repositories {
        mavenCentral()
    }
    val kotlinVersion: String by project
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}

plugins {
    id("kotlinx.team.infra")
    id("maven-publish")
}

infra {
    teamcity {
        libraryStagingRepoDescription = project.name
    }
    publishing {
        include(":kotlinx-cli")
        libraryRepoUrl = "https://github.com/Kotlin/kotlinx-cli"
        sonatype {}
    }
}

allprojects {
    repositories {
        mavenCentral()
    }
}

println(mavenUser)

allprojects {
    publishing {
        repositories {
            maven(url = "https://maven.pkg.github.com/wilversings/kotlinx-cli") {
                name = "GitHubPackages"
                credentials {
                    username = mavenUser
                    password = mavenPassword
                }
            }
        }
    }
}