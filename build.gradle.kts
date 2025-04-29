plugins {
   id("kotlin-conventions")
}

dependencies {
   testImplementation(libs.kotest.runner.junit5)
   testImplementation(libs.kotest.property)
}
