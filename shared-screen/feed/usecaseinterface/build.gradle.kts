import cmp.sample.primitive.dsl.android

plugins {
  id("cmp.convention.kmp.shared.screen.usecase")
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
  namespace = "cmp.sample.shared.screen.feed.usecaseinterface"
}
