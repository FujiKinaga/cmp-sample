package cmp.sample.primitive.plugin.kotlin

import cmp.sample.primitive.dsl.android
import cmp.sample.primitive.dsl.apply
import cmp.sample.primitive.dsl.kotlin
import cmp.sample.primitive.dsl.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class KotlinMultiplatformAndroidPlugin : Plugin<Project> {

  @OptIn(ExperimentalKotlinGradlePluginApi::class)
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply(libs.plugins.android.library)
        apply(libs.plugins.kotlin.parcelize)
      }

      kotlin {
        targetHierarchy.default()

        androidTarget {
          compilations.all {
            kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
          }
          publishAllLibraryVariants()
        }

        sourceSets.getByName("androidMain") {
          dependencies {

          }
        }

        sourceSets.getByName("androidUnitTest") {
          dependencies {
            implementation(kotlin("test-junit"))
          }
        }
      }

      android {
        compileSdk = 34
        defaultConfig {
          minSdk = 24
        }
        buildFeatures {
          compose = true
        }
        composeOptions {
          kotlinCompilerExtensionVersion = libs.versions.jetpackCompose.get()
        }
        sourceSets {
          named("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDirs("src/androidMain/res", "src/commonMain/resources")
            resources.srcDirs("src/commonMain/resources")
          }
        }
        compileOptions {
          sourceCompatibility = JavaVersion.VERSION_17
          targetCompatibility = JavaVersion.VERSION_17
        }
      }

      tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
          jvmTarget = JavaVersion.VERSION_17.toString()
        }
      }
    }
  }
}
