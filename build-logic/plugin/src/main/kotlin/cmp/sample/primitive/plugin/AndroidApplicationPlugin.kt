package cmp.sample.primitive.plugin

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import cmp.sample.primitive.dsl.apply
import cmp.sample.primitive.dsl.kotlin
import cmp.sample.primitive.dsl.libs
import cmp.sample.primitive.dsl.rootAndroid

class AndroidApplicationPlugin : Plugin<Project> {
  @OptIn(ExperimentalKotlinGradlePluginApi::class)
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply(libs.plugins.android.application)
      }

      kotlin {
        targetHierarchy.default()

        androidTarget()
        sourceSets.getByName("androidMain") {
          dependencies {
            implementation(project(":shared"))
            implementation(project(":shared-screen:splash:uilogic"))
            implementation(libs.androidx.appcompat)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.core.ktx)
            implementation(libs.androidx.splashScreen)
            target.dependencies.add(
              "coreLibraryDesugaring",
              libs.android.desugarJdkLibs
            )
          }
        }
      }

      rootAndroid {
        compileSdk = 34
        namespace = "cmp.sample.android"
        sourceSets {
          named("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDirs("src/androidMain/res")
          }
        }
        defaultConfig {
          applicationId = "cmp.sample.android"
          minSdk = 24
          targetSdk = 34
          versionCode = 1
          versionName = "1.0"
        }
        buildTypes {
          release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
              getDefaultProguardFile("proguard-android-optimize.txt"),
              "proguard-rules.pro"
            )
          }
        }
        packaging {
          resources.excludes.add("META-INF/versions/9/previous-compilation-data.bin")
          resources.excludes.add("META-INF/*.kotlin_module")
        }
        compileOptions {
          sourceCompatibility = JavaVersion.VERSION_17
          targetCompatibility = JavaVersion.VERSION_17
          isCoreLibraryDesugaringEnabled = true
        }
      }

      tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
          jvmTarget = JavaVersion.VERSION_17.toString()
        }
      }
    }
  }
}
