import cmp.sample.primitive.dsl.android

plugins {
  id("cmp.convention.kmp.shared")
}

kotlin {
  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation(project(":shared-designsystem"))
        implementation(project(":shared-screen:core:ui"))
        implementation(project(":shared-screen:core:uilogic"))

        implementation(project(":shared-screen:splash:ui"))
        implementation(project(":shared-screen:splash:uilogic"))
        implementation(project(":shared-screen:splash:usecaseinterface"))
        implementation(project(":shared-screen:splash:usecase"))

        implementation(project(":shared-screen:login:ui"))
        implementation(project(":shared-screen:login:uilogic"))
        implementation(project(":shared-screen:login:usecaseinterface"))
        implementation(project(":shared-screen:login:usecase"))

        implementation(project(":shared-screen:main:ui"))
        implementation(project(":shared-screen:main:uilogic"))
        implementation(project(":shared-screen:home:ui"))
        implementation(project(":shared-screen:home:uilogic"))

        implementation(project(":shared-screen:feed:ui"))
        implementation(project(":shared-screen:feed:uilogic"))
        implementation(project(":shared-screen:feed:usecaseinterface"))
        implementation(project(":shared-screen:feed:usecase"))

        implementation(project(":shared-feature:core:usecase"))

        implementation(project(":shared-data:core:domain"))
        implementation(project(":shared-data:core:repository"))
        implementation(project(":shared-data:core:gatewayinterface"))
        implementation(project(":shared-data:core:gateway"))
        implementation(project(":shared-data:core:datasource"))
        implementation(project(":shared-data:core:apiclient"))
        implementation(project(":shared-data:core:serviceinterface"))
        implementation(project(":shared-data:core:service"))
      }
    }
  }
}

android {
  namespace = "cmp.sample.shared"
}
