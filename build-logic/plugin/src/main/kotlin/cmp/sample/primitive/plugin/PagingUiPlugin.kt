package cmp.sample.primitive.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import cmp.sample.primitive.dsl.kotlin
import cmp.sample.primitive.dsl.libs

class PagingUiPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      kotlin {
        sourceSets.getByName("commonMain") {
          dependencies {
            implementation(libs.paging.compose.common)
          }
        }
        sourceSets.getByName("androidMain") {
          dependencies {
            implementation(libs.androidx.paging.runtime)
            implementation(libs.androidx.paging.compose)
          }
        }
        sourceSets.getByName("iosMain") {
          dependencies {
            implementation(libs.paging.runtime.uikit)
          }
        }
      }
    }
  }
}
