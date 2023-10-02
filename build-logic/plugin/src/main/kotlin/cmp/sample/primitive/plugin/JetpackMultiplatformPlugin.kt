package cmp.sample.primitive.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import cmp.sample.primitive.dsl.kotlin
import cmp.sample.primitive.dsl.libs

class JetpackMultiplatformPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      kotlin {
        sourceSets.getByName("commonMain") {
          dependencies {
            implementation(libs.androidx.annotation)
            implementation(libs.androidx.collection)
          }
        }
        sourceSets.getByName("commonTest") {
          dependencies {

          }
        }
      }
    }
  }
}
