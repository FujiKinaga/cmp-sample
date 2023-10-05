package cmp.sample.primitive.plugin

import cmp.sample.primitive.dsl.kotlin
import cmp.sample.primitive.dsl.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class PagingLogicPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      kotlin {
        sourceSets.getByName("commonMain") {
          dependencies {
            implementation(libs.paging.compose.common)
            implementation(libs.androidx.paging.common)
          }
        }
        sourceSets.getByName("androidMain") {
          dependencies {
            implementation(libs.androidx.paging.runtime)
            implementation(libs.androidx.paging.compose)
          }
        }
      }
    }
  }
}
