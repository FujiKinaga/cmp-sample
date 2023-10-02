package cmp.sample.primitive.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import cmp.sample.primitive.dsl.kotlin
import cmp.sample.primitive.dsl.libs

class CoilPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      kotlin {
        sourceSets.getByName("androidMain") {
          dependencies {
            implementation(libs.coil.core)
            implementation(libs.coil.compose)
          }
        }
      }
    }
  }
}
