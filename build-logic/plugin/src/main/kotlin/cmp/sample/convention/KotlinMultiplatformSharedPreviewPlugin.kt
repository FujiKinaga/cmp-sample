package cmp.sample.convention

import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinMultiplatformSharedPreviewPlugin : Plugin<Project> {

  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("cmp.primitive.kmp")
        apply("cmp.primitive.kmp.android")
        apply("cmp.primitive.kmp.ios")
        apply("cmp.primitive.compose")
        apply("cmp.primitive.compose.tooling")
        apply("cmp.primitive.decompose")
        apply("cmp.primitive.paging.ui")
        apply("cmp.primitive.paging.logic")
      }
    }
  }
}
