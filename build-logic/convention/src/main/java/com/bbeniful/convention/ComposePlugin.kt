package com.bbeniful.convention

import com.bbeniful.convention.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class ComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        print("Hello from ComposePlugin")
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.compose")
                apply("org.jetbrains.kotlin.plugin.compose")
            }
           extensions.configure<KotlinMultiplatformExtension>("kotlin") {
                sourceSets.apply {
                    commonMain.dependencies {
                        implementation(libs.findLibrary("compose-runtime").get())
                        implementation(libs.findLibrary("compose-foundation").get())
                        implementation(libs.findLibrary("compose-material3").get())
                        implementation(libs.findLibrary("compose-components-resources").get())
                        implementation(libs.findLibrary("compose-uiToolingPreview").get())
                        implementation(libs.findLibrary("androidx-lifecycle-viewmodelCompose").get())
                        implementation(libs.findLibrary("androidx-lifecycle-runtimeCompose").get())
                    }
                    androidMain.dependencies {
                        implementation(libs.findLibrary("compose-uiToolingPreview").get())
                        implementation(libs.findLibrary("androidx-activity-compose").get())
                    }
                }
            }
        }
    }
}