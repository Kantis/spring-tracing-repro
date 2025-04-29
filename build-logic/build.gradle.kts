plugins {
   `kotlin-dsl`
}

repositories {
   gradlePluginPortal()
   mavenCentral()
}

dependencies {
   implementation(libs.kotlin.gradle.plugin)
   implementation(libs.kotlin.serialization.plugin)
   implementation(libs.ktlint.gradle)
   implementation(libs.testlogger.gradle)
   implementation(libs.detekt.gradle)
}
