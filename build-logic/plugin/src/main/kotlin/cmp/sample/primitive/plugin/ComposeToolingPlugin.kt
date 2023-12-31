package cmp.sample.primitive.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import cmp.sample.primitive.dsl.compose
import cmp.sample.primitive.dsl.kotlin

class ComposeToolingPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      kotlin {
        sourceSets.getByName("androidMain") {
          dependencies {
            implementation(compose.uiTooling)
            implementation(compose.preview)
          }
        }
//        sourceSets.getByName("commonMain") {
//          dependencies {
//            api(compose.uiTooling)
//            api(compose.preview)
//          }
//        }
      }
    }
  }
}
