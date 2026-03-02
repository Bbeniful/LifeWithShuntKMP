package com.bbeniful.convention.extensions

import org.gradle.api.Project
import org.gradle.api.artifacts.ProjectDependency

fun Project.dep(path: String): ProjectDependency =
    dependencies.project(mapOf("path" to path)) as ProjectDependency

fun Project.core(module: String): ProjectDependency =
    dep(":core:$module")

fun Project.feature(
    name: String,
    type: String = "impl",
    sub: String? = null
): ProjectDependency {
    val path = if (sub != null) ":feature:$name:$type:$sub" else ":feature:$name:$type"
    return dep(path)
}

fun Project.featureApi(name: String, sub: String = "nav"): ProjectDependency =
    dep(":feature:$name:api:$sub")

fun Project.featureDi(name: String): ProjectDependency =
    dep(":feature:$name:di")