package cmp.sample.primitive.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import cmp.sample.primitive.dsl.kotlin
import cmp.sample.primitive.dsl.libs

class MoleculePlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      // この方法で行うと、内部のComposeCompilerのapi表記によってフルバージョンが伝播してしまう
      // pluginManager.apply("app.cash.molecule")

      kotlin {
        sourceSets.getByName("commonMain") {
          dependencies {
            implementation(libs.molecule.runtime)
          }
        }
      }

//      molecule {
//        kotlinCompilerPlugin.set(compose.dependencies.compiler.forKotlin(libs.versions.kotlin.get()))
//      }
    }
  }
}

