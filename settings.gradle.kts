rootProject.name = "LIfeWithShunt"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(":composeApp")

include(":core:data")
include(":core:domain")
include(":core:di")

include(":core:presentation")
include(":feature:home:api:nav")
include(":feature:home:impl:data")
include(":feature:home:impl:domain")
include(":feature:home:impl:presentation")
include(":feature:home:di")

include(":feature:weather:api")
include(":feature:weather:api:nav")
include(":feature:weather:impl:data")
include(":feature:weather:impl:domain")
include(":feature:weather:impl:presentation")
include(":feature:weather:di")

include(":feature:shuntCard:api:nav")
include(":feature:shuntCard:impl:domain")
include(":feature:shuntCard:impl:data")
include(":feature:shuntCard:impl:presentation")
include(":feature:shuntCard:di")

include(":feature:symptom:api:nav")
include(":feature:symptom:impl:data")
include(":feature:symptom:impl:presentation")
include(":feature:symptom:impl:domain")
include(":feature:symptom:di")
