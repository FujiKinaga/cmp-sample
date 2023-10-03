package cmp.sample.convention

import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidApplicationPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("cmp.primitive.kmp")
        apply("cmp.primitive.android.application")
        apply("cmp.primitive.compose")
        apply("cmp.primitive.decompose")
        apply("cmp.primitive.spotless")
        apply("cmp.primitive.settings")
        apply("cmp.primitive.napier")
        apply("cmp.primitive.molecule")
      }
    }
  }
}
