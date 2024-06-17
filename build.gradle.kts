plugins {
  `java-library`
  id("io.papermc.paperweight.userdev") version "1.7.1"
  id("xyz.jpenilla.run-paper") version "2.2.3" // Adds runServer and runMojangMappedServer tasks for testing
}

java {
  // Configure the java toolchain. This allows gradle to auto-provision JDK 17 on systems that only have JDK 8 installed for example.
  toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

repositories {
  mavenCentral()
  maven(url = "https://maven.playpro.com")
  maven(url = "https://maven.enginehub.org/repo/")
}

dependencies {
  paperweight.paperDevBundle("1.20.6-R0.1-SNAPSHOT")
  compileOnly("net.coreprotect:coreprotect:22.0")
  compileOnly("com.sk89q.worldguard:worldguard-bukkit:7.0.10") {
    exclude(group = "org.bukkit", module = "bukkit")
  }
}

tasks {
  // Configure reobfJar to run when invoking the build task
  assemble {
    dependsOn(reobfJar)
  }

  compileJava {
    options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything

    // Set the release flag. This configures what version bytecode the compiler will emit, as well as what JDK APIs are usable.
    // See https://openjdk.java.net/jeps/247 for more information.
    options.release.set(21)
  }
  javadoc {
    options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything
  }
//  processResources {
//    filteringCharset = Charsets.UTF_8.name() // We want UTF-8 for everything
//    val props = mapOf(
//      "name" to project.name,
//      "version" to project.version,
//      "description" to project.description,
//      "apiVersion" to "1.20"
//    )
//    inputs.properties(props)
//    filesMatching("plugin.yml") {
//      expand(props)
//    }
//  }
//
//  /*
//  reobfJar {
//    // This is an example of how you might change the output location for reobfJar. It's recommended not to do this
//    // for a variety of reasons, however it's asked frequently enough that an example of how to do it is included here.
//    outputJar.set(layout.buildDirectory.file("libs/PaperweightTestPlugin-${project.version}.jar"))
//  }
}
