package cmp.sample.primitive.plugin.kotlin

import org.gradle.api.Plugin
import org.gradle.api.Project
import cmp.sample.primitive.dsl.apply
import cmp.sample.primitive.dsl.libs

class KotlinKspPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      pluginManager.apply(libs.plugins.ksp)
    }
  }
}
