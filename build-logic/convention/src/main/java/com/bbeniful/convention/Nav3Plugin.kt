package com.bbeniful.convention

import com.bbeniful.convention.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class Nav3Plugin : Plugin<Project> {

    override fun apply(target: Project) {
        print("Hello from Nav3WithKoinPlugin")
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.compose")
                apply("org.jetbrains.kotlin.plugin.compose")
            }
            extensions.configure<KotlinMultiplatformExtension>("kotlin") {
                sourceSets.apply {
                    commonMain.dependencies {
                        implementation(libs.findLibrary("androidx-nav3-ui").get())
                        implementation(libs.findLibrary("androidx-material3-adaptive").get())
                        implementation(libs.findLibrary("androidx-material3-adaptive-nav3").get())
                    }
                }
            }
        }
    }
}