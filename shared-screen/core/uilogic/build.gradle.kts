import cmp.sample.primitive.dsl.android

plugins {
  id("cmp.convention.kmp.shared.screen.uilogic")
}

kotlin {
  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation(project(":shared-data:core:domain"))
      }
    }
  }
}

android {
  namespace = "cmp.sample.shared.screen.core.uilogic"
}
