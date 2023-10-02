import cmp.sample.primitive.dsl.android

plugins {
  id("cmp.convention.kmp.shared.data")
}

kotlin {
  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation(project(":shared-data:core:domain"))
        implementation(project(":shared-data:core:domainmapper"))
        implementation(project(":shared-data:core:gatewayinterface"))
        implementation(project(":shared-data:core:apiclient"))
        implementation(project(":shared-data:core:datasource"))
      }
    }
  }
}

android {
  namespace = "cmp.sample.shared.data.core.gateway"
}
