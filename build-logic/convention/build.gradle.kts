plugins {
   `kotlin-dsl`
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies {
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.compose.gradle.plugin)
    compileOnly("org.jetbrains.compose:compose-gradle-plugin:${libs.versions.composeMultiplatform.get()}")

    // 2. Compose Compiler Gradle Plugin
    // Note: In 2026 (Kotlin 2.0+), the compiler plugin version matches Kotlin
    compileOnly("org.jetbrains.kotlin:compose-compiler-gradle-plugin:${libs.versions.kotlin.get()}")
}

gradlePlugin {
    plugins {
        register("ktorPlugin") {
            id = "com.bbeniful.kmp.ktor"
            implementationClass = "com.bbeniful.convention.KtorPlugin"
        }

        register("nav3WithKoinPlugin") {
            id = "com.bbeniful.kmp.nav3withkoin"
            implementationClass = "com.bbeniful.convention.Nav3WithKoinPlugin"
        }
        register("nav3Plugin") {
            id = "com.bbeniful.kmp.nav3"
            implementationClass = "com.bbeniful.convention.Nav3Plugin"
        }
        register("composePlugin") {
            id = "com.bbeniful.kmp.compose"
            implementationClass = "com.bbeniful.convention.ComposePlugin"
        }

        register("addingFeaturesPlugin") {
            id = "com.bbeniful.kmp.addingfeatures"
            implementationClass = "com.bbeniful.convention.AddingFeatureToAppPlugin"
        }
    }
}
