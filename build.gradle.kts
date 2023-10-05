plugins {
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.android.test) apply false
  alias(libs.plugins.kotlin.jvm) apply false
  alias(libs.plugins.kotlin.multiplatform) apply false
  alias(libs.plugins.kotlin.android) apply false
  alias(libs.plugins.kotlin.js) apply false
  alias(libs.plugins.kotlin.serialization) apply false
  alias(libs.plugins.kotlin.parcelize) apply false
  alias(libs.plugins.molecule) apply false
  alias(libs.plugins.compose) apply false
  alias(libs.plugins.ksp) apply true
  alias(libs.plugins.ktorfit) apply true
  alias(libs.plugins.spotless) apply false
}

configure<de.jensklingenberg.ktorfit.gradle.KtorfitGradleConfiguration> {
  version = libs.versions.ktorfit.get()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.toString()
  }
}
