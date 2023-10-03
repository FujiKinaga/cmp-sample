plugins {
  id("cmp.convention.kmp.shared.preview")
}

kotlin {
  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation(project(":shared-designsystem"))

        implementation(project(":shared-screen:login:ui"))
        implementation(project(":shared-screen:login:uilogic"))

        implementation(project(":shared-screen:feed:ui"))
        implementation(project(":shared-screen:feed:uilogic"))
      }
    }
  }
}

android {
  namespace = "cmp.sample.shared.preview"
}
