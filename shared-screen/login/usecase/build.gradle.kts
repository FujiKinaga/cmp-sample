import cmp.sample.primitive.dsl.android

plugins {
  id("cmp.convention.kmp.shared.screen.usecase")
}

kotlin {
  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation(project(":shared-screen:login:usecaseinterface"))

        implementation(project(":shared-feature:core:usecase"))

        implementation(project(":shared-data:core:domain"))
        implementation(project(":shared-data:core:gatewayinterface"))
        implementation(project(":shared-data:core:serviceinterface"))
      }
    }
  }
}

android {
  namespace = "cmp.sample.shared.screen.login.usecase"
}
