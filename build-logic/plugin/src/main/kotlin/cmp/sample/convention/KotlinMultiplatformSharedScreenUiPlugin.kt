package cmp.sample.convention

import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinMultiplatformSharedScreenUiPlugin : Plugin<Project> {

  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("cmp.primitive.kmp")
        apply("cmp.primitive.kmp.android")
        apply("cmp.primitive.kmp.ios")
        apply("cmp.primitive.kmp.jetpack")
        apply("cmp.primitive.compose")
        apply("cmp.primitive.decompose")
        apply("cmp.primitive.coil")
        apply("cmp.primitive.kamel")
        apply("cmp.primitive.spotless")
        apply("cmp.primitive.napier")
        apply("cmp.primitive.paging.ui")
      }
    }
  }
}
