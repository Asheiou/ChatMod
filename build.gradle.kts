plugins {
  kotlin("jvm") version "2.2.0"
  id("com.gradleup.shadow") version "8.3.0"
  id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "cymru.asheiou"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
  maven("https://repo.papermc.io/repository/maven-public/") {
    name = "papermc-repo"
  }
  maven("https://oss.sonatype.org/content/groups/public/") {
    name = "sonatype"
  }
  maven("https://repo.asheiou.cymru/releases/") {
    name = "asheiou"
  }
}

dependencies {
  compileOnly("io.papermc.paper:paper-api:1.21-R0.1-SNAPSHOT")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("cymru.asheiou:configmanager:1.2.1")
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
  testImplementation("org.slf4j:slf4j-simple:2.0.7")
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:2.2.0")
  testImplementation("org.mockito:mockito-core:4.5.1")
  testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
  testImplementation("org.mockbukkit.mockbukkit:mockbukkit-v1.21:4.72.5")
}

tasks {
  runServer {
    // Configure the Minecraft version for our task.
    // This is the only required configuration besides applying the plugin.
    // Your plugin's jar (or shadowJar if present) will be used automatically.
    minecraftVersion("1.21")
  }
}

val targetJavaVersion = 21
kotlin {
  jvmToolchain(targetJavaVersion)
}

tasks.build {
  dependsOn("shadowJar")
}

tasks.processResources {
  val props = mapOf("version" to version)
  inputs.properties(props)
  filteringCharset = "UTF-8"
  filesMatching("plugin.yml") {
    expand(props)
  }
  val projectVer = project.version
  filesMatching(listOf("**/*.yml", "**/*.txt")) {
    filter {
      it.replace("%%VER", projectVer.toString())
    }
  }
}

tasks.test {
  useJUnitPlatform()
  filter {
    includeTestsMatching("*Test")
  }
}

tasks.shadowJar {
  relocate("kotlin", "cymru.asheiou.chatmod.shade.kotlin")
  relocate("org.intellij", "cymru.asheiou.chatmod.shade.intellij")
  relocate("org.jetbrains", "cymru.asheiou.chatmod.shade.jetbrains")
  relocate("cymru.asheiou.configmanager", "cymru.asheiou.shade.chatmod.configmanager")
}