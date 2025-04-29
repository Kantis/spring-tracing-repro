plugins {
   id("kotlin-conventions")
   alias(libs.plugins.springBoot)
   alias(libs.plugins.springDependencyManagement)
   alias(libs.plugins.kotlin.spring)
}

dependencies {
   implementation(libs.springBoot.starter)
   implementation(libs.springBoot.starterWeb)
   implementation(libs.springBoot.starterActuator)
   implementation(libs.springBoot.devtools)
   implementation(libs.tracing.bridge)
   testImplementation(libs.kotest.runnerJunit5)
   testImplementation(libs.kotestExtensions.spring)
   testImplementation(libs.springBoot.starterTest)
}
