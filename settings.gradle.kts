pluginManagement {
  includeBuild("build-logic")
  repositories {
    gradlePluginPortal()
    google {
      content {
        includeGroupByRegex("androidx.*")
        includeGroupByRegex("com\\.android.*")
        includeGroupByRegex("com\\.google.*")
      }
    }
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
  }
}
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
  repositories {
    google {
      content {
        includeGroupByRegex("androidx.*")
        includeGroupByRegex("com\\.android.*")
        includeGroupByRegex("com\\.google.*")
      }
    }
    mavenCentral()
    gradlePluginPortal()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven { url = uri("https://jitpack.io") }
  }
}
rootProject.name = "cmp-sample"
include(":androidApp")
include(":shared")
include(":shared-designsystem")
include(":shared-screen:core:ui")
include(":shared-screen:core:uilogic")
include(":shared-screen:splash:ui")
include(":shared-screen:splash:uilogic")
include(":shared-screen:splash:usecaseinterface")
include(":shared-screen:splash:usecase")
include(":shared-screen:login:ui")
include(":shared-screen:login:uilogic")
include(":shared-screen:login:usecaseinterface")
include(":shared-screen:login:usecase")
include(":shared-screen:main:ui")
include(":shared-screen:main:uilogic")
include(":shared-screen:home:ui")
include(":shared-screen:home:uilogic")
include(":shared-screen:feed:ui")
include(":shared-screen:feed:uilogic")
include(":shared-screen:feed:usecaseinterface")
include(":shared-screen:feed:usecase")

include(":shared-feature:core:usecase")

include(":shared-data:core:domain")
include(":shared-data:core:domainmapper")
include(":shared-data:core:repository")
include(":shared-data:core:gateway")
include(":shared-data:core:gatewayinterface")
include(":shared-data:core:datasource")
include(":shared-data:core:apiclient")
include(":shared-data:core:serviceinterface")
include(":shared-data:core:service")

include(":shared-preview")
