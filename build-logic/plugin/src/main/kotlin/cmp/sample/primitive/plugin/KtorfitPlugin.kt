package cmp.sample.primitive.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import cmp.sample.primitive.dsl.apply
import cmp.sample.primitive.dsl.kotlin
import cmp.sample.primitive.dsl.libs

class KtorfitPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("cmp.primitive.ksp")
        apply(libs.plugins.ktorfit)
      }

      kotlin {
        sourceSets.getByName("androidMain") {
          dependencies {
            implementation(libs.ktor.okhttp)
          }
        }
        sourceSets.getByName("commonMain") {
          dependencies {
            implementation(libs.ktorfit.lib)
            implementation(libs.ktor.core)
            implementation(libs.ktor.cio)
            implementation(libs.ktor.logging)
            implementation(libs.ktor.auth)
            implementation(libs.ktor.content.negotiation)
            implementation(libs.ktor.serialization.json)
          }
        }
      }

      dependencies {
        listOf(
          "kspCommonMainMetadata",
          "kspAndroid",
          // kspIosはまだ使用できないみたい
          "kspIosX64",
          "kspIosArm64",
          "kspIosSimulatorArm64",
//          "kspDesktop",
//          "kspJs",
//          "kspWasm",
        ).forEach {
          add(it, libs.ktorfit.ksp)
        }
      }
    }
  }
}

