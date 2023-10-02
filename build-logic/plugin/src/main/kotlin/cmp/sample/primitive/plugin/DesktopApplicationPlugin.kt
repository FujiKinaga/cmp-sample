package cmp.sample.primitive.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import cmp.sample.primitive.dsl.kotlin

class DesktopApplicationPlugin : Plugin<Project> {
  @OptIn(ExperimentalKotlinGradlePluginApi::class)
  override fun apply(target: Project) {
    with(target) {
      kotlin {
        targetHierarchy.default()

        jvm {
          withJava()
        }
        sourceSets.getByName("jvmMain") {
          dependencies {
            implementation(project(":shared"))
          }
        }
      }
    }
  }
}
