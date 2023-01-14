import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import ru.spliterash.shadowkotlinrelocate.shadowjar.kotlinRelocate

plugins {
    kotlin("jvm") version "1.7.21"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("ru.spliterash.shadow-kotlin-relocate") version "1.0.0"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2"

}

group = "ru.spliterash"
version = "1.0.0"

bukkit {
    name = "ItemsAdderHelper"
    main = "ru.spliterash.itemsAdderHelper.plugin.ItemsAdderHelperPlugin"
    apiVersion = "1.13"
    authors = listOf("Spliterash")
    depend = listOf("KotlinMc", "SpringSpigot", "ItemsAdder")
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/") // PaperAPI
    maven("https://repo.aikar.co/content/groups/aikar/") // ACF
    maven("https://repo.dmulloy2.net/repository/public/") // ProtocolLib
    maven {
        url = uri("https://repo.spliterash.ru/all/")
        credentials {
            username = findProperty("SPLITERASH_NEXUS_USR")?.toString()
            password = findProperty("SPLITERASH_NEXUS_PSW")?.toString()
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
repositories {
    maven("https://maven.pkg.github.com/LoneDev6/API-ItemsAdder") {
        name = "githubPackages"
        credentials(PasswordCredentials::class)
    }
}
tasks.shadowJar {
    archiveVersion.set("")
    archiveClassifier.set("")

    val startPath = "ru.spliterash.itemsAdderHelper.shadow."
    kotlinRelocate("co.aikar.commands", startPath + "acf")
    kotlinRelocate("co.aikar.locales", startPath + "locales")

    dependencies {
        allprojects.forEach {
            include(dependency(it))
        }

        include {
            it.moduleGroup in listOf("ru.spliterash")
        }
        include(dependency("co.aikar:acf-paper"))
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.19.2-R0.1-SNAPSHOT")
    compileOnly("ru.spliterash:spring-spigot:1.0.6")
    compileOnly("ru.spliterash:kotlin-mc:1.0.3")


    compileOnly("dev.lone:api-itemsadder:3.0.0")


    implementation("co.aikar:acf-paper:0.5.1-SNAPSHOT")
}
tasks {
    assemble { dependsOn(relocateKotlinMetadata) }
    jar { enabled = false; }
}