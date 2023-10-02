import cmp.sample.primitive.dsl.android

plugins {
  id("cmp.convention.kmp.shared.data")
}

kotlin {
  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation(project(":shared-data:core:domain"))
        implementation(project(":shared-feature:core:usecase"))
      }
    }
  }
}

android {
  namespace = "cmp.sample.shared.data.core.serviceinterface"
}
