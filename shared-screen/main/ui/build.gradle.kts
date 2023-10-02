import cmp.sample.primitive.dsl.android

plugins {
  id("cmp.convention.kmp.shared.screen.ui")
}

kotlin {
  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation(project(":shared-designsystem"))
        implementation(project(":shared-screen:core:ui"))
        implementation(project(":shared-screen:core:uilogic"))
        implementation(project(":shared-screen:main:uilogic"))
        implementation(project(":shared-screen:home:ui"))
        implementation(project(":shared-screen:home:uilogic"))
      }
    }
  }
}

android {
  namespace = "cmp.sample.shared.screen.main.ui"
}
