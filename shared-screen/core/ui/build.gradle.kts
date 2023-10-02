import cmp.sample.primitive.dsl.android

plugins {
  id("cmp.convention.kmp.shared.screen.ui")
}

kotlin {
  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation(project(":shared-designsystem"))
        implementation(project(":shared-screen:core:uilogic"))
      }
    }
  }
}

android {
  namespace = "cmp.sample.shared.screen.core.ui"
}
