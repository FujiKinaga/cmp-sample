package cmp.sample.primitive.plugin

import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import cmp.sample.primitive.dsl.apply
import cmp.sample.primitive.dsl.libs

class SpotlessPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      pluginManager.apply(libs.plugins.spotless)

      extensions.configure<SpotlessExtension> {
        kotlin {
          ktlint()
        }
        kotlinGradle {
          target("*.gradle.kts")
          ktlint()
        }
      }
    }
  }
}
