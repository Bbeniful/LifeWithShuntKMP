package com.bbeniful.convention

import com.bbeniful.convention.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KtorPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        print("Applying KtorPlugin to ${target.name}")
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.plugin.serialization")
            }
           // pluginManager.apply("org.jetbrains.kotlin.plugin.serialization")
            extensions.configure<KotlinMultiplatformExtension>("kotlin") {
                sourceSets.apply {
                    commonMain.dependencies {
                        implementation(libs.findLibrary("ktor-client-content-negotiation").get())
                        implementation(libs.findLibrary("ktor-client-core").get())
                        implementation(libs.findLibrary("ktor-serialization-kotlinx-json").get())
                    }

                    androidMain.dependencies {
                        implementation(libs.findLibrary("ktor-client-okhttp").get())
                    }

                    iosMain.dependencies {
                        implementation(libs.findLibrary("ktor-client-darwin").get())
                    }
                }
            }
        }
    }
}