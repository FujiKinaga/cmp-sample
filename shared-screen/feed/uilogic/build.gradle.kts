import cmp.sample.primitive.dsl.android

plugins {
  id("cmp.convention.kmp.shared.screen.uilogic")
}

kotlin {
  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation(project(":shared-screen:core:uilogic"))

        implementation(project(":shared-feature:core:usecase"))
        implementation(project(":shared-screen:feed:usecaseinterface"))

        implementation(project(":shared-data:core:domain"))
      }
    }
  }
}

android {
  namespace = "cmp.sample.shared.screen.feed.uilogic"
}
