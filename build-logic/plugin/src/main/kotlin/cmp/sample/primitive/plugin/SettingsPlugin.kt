package cmp.sample.primitive.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import cmp.sample.primitive.dsl.kotlin
import cmp.sample.primitive.dsl.libs

class SettingsPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      kotlin {
        sourceSets.getByName("commonMain") {
          dependencies {
            implementation(libs.settings.core)
            implementation(libs.settings.serialization)
            implementation(libs.settings.coroutines)
            implementation(libs.androidx.dataStore.core)
          }
        }
        sourceSets.getByName("androidMain") {
          dependencies {
            implementation(libs.settings.datastore)
          }
        }
      }
    }
  }
}
