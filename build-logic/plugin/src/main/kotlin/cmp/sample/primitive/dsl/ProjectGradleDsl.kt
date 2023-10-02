package cmp.sample.primitive.dsl

import app.cash.molecule.gradle.MoleculeExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.google.devtools.ksp.gradle.KspExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.plugins.PluginManager
import org.gradle.api.provider.Provider
import org.gradle.api.publish.PublishingExtension
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.accessors.runtime.addConfiguredDependencyTo
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.plugin.use.PluginDependency
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.compose.ComposePlugin
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import java.util.Optional

val Project.libs get() = extensions.getByName("libs") as LibrariesForLibs

fun Project.kotlin(action: KotlinMultiplatformExtension.() -> Unit) {
  extensions.configure(action)
}

fun Project.android(action: LibraryExtension.() -> Unit) {
  extensions.configure(action)
}

fun Project.rootAndroid(action: BaseAppModuleExtension.() -> Unit) {
  extensions.configure(action)
}

fun Project.publishing(action: PublishingExtension.() -> Unit) {
  extensions.configure(action)
}

fun Project.ksp(configure: Action<KspExtension>): Unit =
  extensions.configure("ksp", configure)

fun DependencyHandlerScope.implementation(
  artifact: Optional<Provider<MinimalExternalModuleDependency>>
) {
  add("implementation", artifact.get())
}

fun DependencyHandlerScope.implementation(
  artifact: Provider<MinimalExternalModuleDependency>
) {
  add("implementation", artifact)
}

fun DependencyHandlerScope.implementation(
  dependencyNotation: Any
) {
  add("implementation", dependencyNotation)
}

fun DependencyHandlerScope.debugImplementation(
  artifact: Optional<Provider<MinimalExternalModuleDependency>>
) {
  add("debugImplementation", artifact.get())
}

fun DependencyHandlerScope.debugImplementation(
  dependencyNotation: Any
) {
  add("debugImplementation", dependencyNotation)
}

fun DependencyHandlerScope.androidTestImplementation(
  artifact: Optional<Provider<MinimalExternalModuleDependency>>
) {
  add("androidTestImplementation", artifact.get())
}

fun DependencyHandlerScope.androidTestImplementation(
  dependencyNotation: Any
) {
  add("androidTestImplementation", dependencyNotation)
}

fun DependencyHandlerScope.testImplementation(
  artifact: Optional<Provider<MinimalExternalModuleDependency>>
) {
  add("testImplementation", artifact.get())
}

fun DependencyHandlerScope.testImplementation(
  dependencyNotation: Any
) {
  add("testImplementation", dependencyNotation)
}

fun DependencyHandlerScope.testImplementation(
  artifact: Optional<Provider<MinimalExternalModuleDependency>>,
  dependencyConfiguration: Action<ExternalModuleDependency>,
) {
  addConfiguredDependencyTo(this, "testImplementation", artifact.get(), dependencyConfiguration)
}

fun DependencyHandlerScope.testImplementation(
  artifact: Provider<*>,
  dependencyConfiguration: Action<ExternalModuleDependency>,
) {
  addConfiguredDependencyTo(this, "testImplementation", artifact, dependencyConfiguration)
}

fun DependencyHandlerScope.testImplementation(
  project: Project,
) {
  add("testImplementation", project)
}

private fun DependencyHandlerScope.api(
  artifact: Optional<Provider<MinimalExternalModuleDependency>>
) {
  add("api", artifact.get())
}

fun DependencyHandlerScope.ksp(
  artifact: Optional<Provider<MinimalExternalModuleDependency>>
) {
  add("ksp", artifact.get())
}

fun DependencyHandlerScope.ksp(
  artifact: Provider<*>
) {
  add("ksp", artifact)
}

fun Project.implementationPlatform(dependencyProvider: Provider<MinimalExternalModuleDependency>) {
  project.dependencies {
    this.implementation(platform(dependencyProvider))
  }
}

// project property utilities
fun Project.propertyAsInt(key: String): Int = (this.property(key) as String).toInt()
fun Project.propertyAsFloat(key: String): Float = (this.property(key) as String).toFloat()
fun Project.propertyAsDouble(key: String): Double = (this.property(key) as String).toDouble()
fun Project.propertyAsString(key: String): String = (this.property(key) as String)
fun Project.propertyAsBoolean(key: String): Boolean = (this.property(key) as String).toBoolean()

fun PluginManager.apply(
  pluginProvider: Provider<PluginDependency>
) = apply(pluginProvider.get().pluginId)

val KotlinMultiplatformExtension.`compose`: ComposePlugin.Dependencies
  get() =
    (this as ExtensionAware).extensions.getByName("compose") as ComposePlugin.Dependencies

fun KotlinMultiplatformExtension.`compose`(configure: Action<ComposePlugin.Dependencies>): Unit =
  (this as ExtensionAware).extensions.configure("compose", configure)

val Project.`compose`: ComposeExtension
  get() =
    (this as ExtensionAware).extensions.getByName("compose") as ComposeExtension

fun Project.`compose`(configure: Action<ComposeExtension>): Unit =
  (this as ExtensionAware).extensions.configure("compose", configure)

val Project.`molecule`: MoleculeExtension
  get() =
    (this as ExtensionAware).extensions.getByName("molecule") as MoleculeExtension

fun Project.`molecule`(configure: Action<MoleculeExtension>): Unit =
  (this as ExtensionAware).extensions.configure("molecule", configure)
