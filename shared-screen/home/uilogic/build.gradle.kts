import cmp.sample.primitive.dsl.android

plugins {
  id("cmp.convention.kmp.shared.screen.uilogic")
}

kotlin {
  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation(project(":shared-screen:core:uilogic"))
        implementation(project(":shared-screen:feed:uilogic"))
      }
    }
  }
}

android {
  namespace = "cmp.sample.shared.screen.home.uilogic"
}
