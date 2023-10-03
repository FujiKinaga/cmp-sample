package cmp.sample.primitive.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.compose.ExperimentalComposeLibrary
import cmp.sample.primitive.dsl.apply
import cmp.sample.primitive.dsl.compose
import cmp.sample.primitive.dsl.kotlin
import cmp.sample.primitive.dsl.libs

class ComposePlugin : Plugin<Project> {
  @OptIn(ExperimentalComposeLibrary::class)
  override fun apply(target: Project) {
    with(target) {
      pluginManager.apply(libs.plugins.compose)

      kotlin {
        sourceSets.getByName("commonMain") {
          dependencies {
            implementation(compose.compiler.forKotlin(libs.versions.kotlin.get()))
            implementation(compose.animation)
            implementation(compose.animationGraphics)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.runtime)
            implementation(compose.runtimeSaveable)
            implementation(compose.ui)
            implementation(compose.materialIconsExtended)
            implementation(compose.components.resources)
          }
        }
        sourceSets.getByName("commonTest") {
          dependencies {

          }
        }
      }

      compose {
        kotlinCompilerPlugin.set(dependencies.compiler.forKotlin(libs.versions.kotlin.get()))
      }
    }
  }
}
