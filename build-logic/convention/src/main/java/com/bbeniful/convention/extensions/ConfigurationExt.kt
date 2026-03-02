@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

package com.bbeniful.convention.extensions

import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet


fun Project.kmpDependencies(configure: KotlinMultiplatformExtension.() -> Unit) {
    extensions.configure<KotlinMultiplatformExtension>("kotlin") {
        configure()
    }
}

private fun KotlinMultiplatformExtension.sourceSets(
    configure: NamedDomainObjectContainer<KotlinSourceSet>.() -> Unit
) {
    sourceSets.configure()
}