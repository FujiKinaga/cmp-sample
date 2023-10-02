package cmp.sample.primitive.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import cmp.sample.primitive.dsl.kotlin
import cmp.sample.primitive.dsl.libs

class PagingLogicPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      kotlin {
        sourceSets.getByName("commonMain") {
          dependencies {
            implementation(libs.paging.common)
            implementation(libs.paging.compose.common)
          }
        }
      }
    }
  }
}
