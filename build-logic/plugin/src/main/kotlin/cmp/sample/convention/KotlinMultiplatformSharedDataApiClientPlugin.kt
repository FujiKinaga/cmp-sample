package cmp.sample.convention

import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinMultiplatformSharedDataApiClientPlugin : Plugin<Project> {

  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("cmp.primitive.kmp")
        apply("cmp.primitive.kmp.android")
        apply("cmp.primitive.kmp.ios")
        apply("cmp.primitive.ktorfit")
        apply("cmp.primitive.spotless")
        apply("cmp.primitive.napier")
      }
    }
  }
}
