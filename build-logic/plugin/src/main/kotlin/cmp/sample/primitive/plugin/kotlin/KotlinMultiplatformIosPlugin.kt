package cmp.sample.primitive.plugin.kotlin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import cmp.sample.primitive.dsl.kotlin

class KotlinMultiplatformIosPlugin : Plugin<Project> {
  @OptIn(ExperimentalKotlinGradlePluginApi::class)
  override fun apply(target: Project) {
    with(target) {
      kotlin {
        targetHierarchy.default()

        listOf(
          iosX64(),
          iosArm64(),
          iosSimulatorArm64()
        ).forEach { iosTarget ->
          iosTarget.binaries.framework {
            baseName = "shared"
            isStatic = true
          }
        }
      }
    }
  }
}
