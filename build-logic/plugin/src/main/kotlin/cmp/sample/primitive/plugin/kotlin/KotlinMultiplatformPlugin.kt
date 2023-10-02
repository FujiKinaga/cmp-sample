package cmp.sample.primitive.plugin.kotlin

import org.gradle.api.Plugin
import org.gradle.api.Project
import cmp.sample.primitive.dsl.apply
import cmp.sample.primitive.dsl.kotlin
import cmp.sample.primitive.dsl.libs

class KotlinMultiplatformPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply(libs.plugins.kotlin.multiplatform)
        apply(libs.plugins.kotlin.serialization)
      }

      kotlin {
        sourceSets.getByName("commonMain") {
          dependencies {
            implementation(libs.kotlinx.datetime)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.io.core)
            implementation(libs.kotlinx.io.bytestring)
          }
        }
        sourceSets.getByName("commonTest") {
          dependencies {
            implementation(kotlin("test"))
          }
        }
      }
    }
  }
}
