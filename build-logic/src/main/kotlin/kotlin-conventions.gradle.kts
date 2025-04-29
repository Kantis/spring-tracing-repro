import io.gitlab.arturbosch.detekt.Detekt
import org.jlleitschuh.gradle.ktlint.KtlintExtension

plugins {
   kotlin("jvm")
   id("com.adarshr.test-logger")
   id("org.jlleitschuh.gradle.ktlint")
   id("io.gitlab.arturbosch.detekt")
}

repositories {
   mavenCentral()
   maven("https://oss.sonatype.org/content/repositories/snapshots") { mavenContent { snapshotsOnly() } }
   maven("https://s01.oss.sonatype.org/content/repositories/snapshots") { mavenContent { snapshotsOnly() } }
}

detekt {
   config.from(rootDir.toPath().toAbsolutePath().toString() + "/.detekt-config.yaml")
}

configure<KtlintExtension> {
   version.set("1.5.0")
   filter {
      exclude {
         it.file.path.contains("generated")
      }
   }
}

kotlin {
   jvmToolchain {
      languageVersion.set(JavaLanguageVersion.of(21))
   }

   compilerOptions {
      freeCompilerArgs.add("-Xcontext-receivers")
   }
}

testlogger {
   showPassed = false
}

tasks.withType<Detekt>().configureEach {
   reports {
      html.required.set(false)
      xml.required.set(false)
      sarif.required.set(false)
   }
}

tasks.withType<Test>().configureEach {
   useJUnitPlatform()
}
