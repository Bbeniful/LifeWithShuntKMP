plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.androidLint)
    alias(libs.plugins.nav3ConventionPlugin)
    alias(libs.plugins.composeConventionPlugin)
    alias(libs.plugins.nav3WithKoinConventionPlugin)
}

kotlin {

    androidLibrary {
        namespace = "com.bbeniful.feature.home.presentation"
        compileSdk = 36
        minSdk = 29

        withHostTestBuilder {
        }

        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    val xcfName = "feature:home:presentationKit"


    iosArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosSimulatorArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                api(projects.core.domain)
                api(projects.core.presentation)
                api(projects.feature.weather.api.domain)
                implementation(libs.koin.core)
                implementation(project.dependencies.platform(libs.koin.bom))
                implementation(libs.koin.androidx.compose)
                implementation(libs.koin.compose.viewmodel)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.koin.android)
            }
        }

        getByName("androidDeviceTest") {
            dependencies {
                implementation(libs.androidx.runner)
                implementation(libs.androidx.core)
                implementation(libs.androidx.testExt.junit)
            }
        }

        iosMain {
            dependencies {
            }
        }
    }

    androidLibrary {
        experimentalProperties["android.experimental.kmp.enableAndroidResources"] = true
    }

}