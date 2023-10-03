import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  `kotlin-dsl`
}

group = "cmp.sample.buildlogic"

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.toString()
  }
}

dependencies {
  compileOnly(libs.android.gradlePlugin)
  compileOnly(libs.kotlin.gradlePlugin)
  compileOnly(libs.jetbrains.compose.gradlePlugin)
  compileOnly(libs.molecule.gradlePlugin)
  compileOnly(libs.ksp.gradlePlugin)
  compileOnly(libs.spotless.gradlePlugin)

  // org.gradle.accessors.dm.LibrariesForLibsをbuild-logicでもimportできるようにする
  implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

gradlePlugin {
  plugins {
    // Primitive
    register("primitive-ksp") {
      id = "cmp.primitive.ksp"
      implementationClass = "cmp.sample.primitive.plugin.kotlin.KotlinKspPlugin"
    }
    register("primitive-kmp") {
      id = "cmp.primitive.kmp"
      implementationClass = "cmp.sample.primitive.plugin.kotlin.KotlinMultiplatformPlugin"
    }
    register("primitive-kmp-android") {
      id = "cmp.primitive.kmp.android"
      implementationClass = "cmp.sample.primitive.plugin.kotlin.KotlinMultiplatformAndroidPlugin"
    }
    register("primitive-kmp-ios") {
      id = "cmp.primitive.kmp.ios"
      implementationClass = "cmp.sample.primitive.plugin.kotlin.KotlinMultiplatformIosPlugin"
    }
    register("primitive-kmp-jetpack") {
      id = "cmp.primitive.kmp.jetpack"
      implementationClass = "cmp.sample.primitive.plugin.JetpackMultiplatformPlugin"
    }
    register("primitive-compose") {
      id = "cmp.primitive.compose"
      implementationClass = "cmp.sample.primitive.plugin.ComposePlugin"
    }
    register("primitive-compose-tooling") {
      id = "cmp.primitive.compose.tooling"
      implementationClass = "cmp.sample.primitive.plugin.ComposeToolingPlugin"
    }
    register("primitive-decompose") {
      id = "cmp.primitive.decompose"
      implementationClass = "cmp.sample.primitive.plugin.DecomposePlugin"
    }
    register("primitive-molecule") {
      id = "cmp.primitive.molecule"
      implementationClass = "cmp.sample.primitive.plugin.MoleculePlugin"
    }
    register("primitive-ktorfit") {
      id = "cmp.primitive.ktorfit"
      implementationClass = "cmp.sample.primitive.plugin.KtorfitPlugin"
    }
    register("primitive-coil") {
      id = "cmp.primitive.coil"
      implementationClass = "cmp.sample.primitive.plugin.CoilPlugin"
    }
    register("primitive-kamel") {
      id = "cmp.primitive.kamel"
      implementationClass = "cmp.sample.primitive.plugin.KamelPlugin"
    }
    register("primitive-spotless") {
      id = "cmp.primitive.spotless"
      implementationClass = "cmp.sample.primitive.plugin.SpotlessPlugin"
    }
    register("primitive-napier") {
      id = "cmp.primitive.napier"
      implementationClass = "cmp.sample.primitive.plugin.NapierPlugin"
    }
    register("primitive-settings") {
      id = "cmp.primitive.settings"
      implementationClass = "cmp.sample.primitive.plugin.SettingsPlugin"
    }
    register("primitive-paging-ui") {
      id = "cmp.primitive.paging.ui"
      implementationClass = "cmp.sample.primitive.plugin.PagingUiPlugin"
    }
    register("primitive-paging-logic") {
      id = "cmp.primitive.paging.logic"
      implementationClass = "cmp.sample.primitive.plugin.PagingLogicPlugin"
    }
    register("primitive-android-application") {
      id = "cmp.primitive.android.application"
      implementationClass = "cmp.sample.primitive.plugin.AndroidApplicationPlugin"
    }
    // Convention
    register("convention-kmp-shared") {
      id = "cmp.convention.kmp.shared"
      implementationClass = "cmp.sample.convention.KotlinMultiplatformSharedPlugin"
    }
    register("convention-kmp-shared-screen-ui") {
      id = "cmp.convention.kmp.shared.screen.ui"
      implementationClass = "cmp.sample.convention.KotlinMultiplatformSharedScreenUiPlugin"
    }
    register("convention-kmp-shared-screen-uilogic") {
      id = "cmp.convention.kmp.shared.screen.uilogic"
      implementationClass = "cmp.sample.convention.KotlinMultiplatformSharedScreenUiLogicPlugin"
    }
    register("convention-kmp-shared-screen-usecase") {
      id = "cmp.convention.kmp.shared.screen.usecase"
      implementationClass = "cmp.sample.convention.KotlinMultiplatformSharedScreenUseCasePlugin"
    }
    register("convention-kmp-shared-feature") {
      id = "cmp.convention.kmp.shared.feature"
      implementationClass = "cmp.sample.convention.KotlinMultiplatformSharedFeaturePlugin"
    }
    register("convention-kmp-shared-data") {
      id = "cmp.convention.kmp.shared.data"
      implementationClass = "cmp.sample.convention.KotlinMultiplatformSharedDataPlugin"
    }
    register("convention-kmp-shared-data-apiclient") {
      id = "cmp.convention.kmp.shared.data.apiclient"
      implementationClass = "cmp.sample.convention.KotlinMultiplatformSharedDataApiClientPlugin"
    }
    register("convention-kmp-shared-data-datasource") {
      id = "cmp.convention.kmp.shared.data.datasource"
      implementationClass = "cmp.sample.convention.KotlinMultiplatformSharedDataDataSourcePlugin"
    }
    register("convention-kmp-shared-designsystem") {
      id = "cmp.convention.kmp.shared.designsystem"
      implementationClass = "cmp.sample.convention.KotlinMultiplatformSharedDesignSystemPlugin"
    }
    register("convention-kmp-shared-preview") {
      id = "cmp.convention.kmp.shared.preview"
      implementationClass = "cmp.sample.convention.KotlinMultiplatformSharedPreviewPlugin"
    }
    register("convention-android-application") {
      id = "cmp.convention.android.application"
      implementationClass = "cmp.sample.convention.AndroidApplicationPlugin"
    }
  }
}
