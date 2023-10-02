import cmp.sample.primitive.dsl.android

plugins {
  id("cmp.convention.kmp.shared.screen.uilogic")
}

kotlin {
  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation(project(":shared-screen:core:uilogic"))
        implementation(project(":shared-screen:home:uilogic"))

        implementation(project(":shared-data:core:domain"))
        implementation(project(":shared-data:core:repository"))
      }
    }
  }
}

android {
  namespace = "cmp.sample.shared.screen.main.uilogic"
}
