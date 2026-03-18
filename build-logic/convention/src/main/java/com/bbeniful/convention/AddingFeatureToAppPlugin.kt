package com.bbeniful.convention

import com.bbeniful.convention.extensions.core
import com.bbeniful.convention.extensions.feature
import com.bbeniful.convention.extensions.featureApi
import com.bbeniful.convention.extensions.featureDi
import com.bbeniful.convention.extensions.kmpDependencies
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.invoke

class AddingFeatureToAppPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            kmpDependencies {
                sourceSets {
                    commonMain.dependencies {
                        // Core modules
                        implementation(core("data"))
                        implementation(core("domain"))
                        implementation(core("di"))
                        implementation(core("presentation"))

                        // Home Feature
                        implementation(featureApi("home", "nav"))
                        implementation(featureDi("home"))
                        //implementation(feature("home", "impl", "data"))
                        //implementation(feature("home", "impl", "domain"))
                        implementation(feature("home", "impl", "presentation"))

                        // Weather
                        implementation(featureApi("weather", "nav"))
                        //implementation(feature("weather", "impl", "data"))
                        /*TMP*/
                        implementation(feature("weather", "impl", "domain"))
                        implementation(feature("weather", "impl", "presentation"))
                        implementation(featureDi("weather"))

                        // ShuntCard
                        implementation(featureApi("shuntCard", "nav"))
                        implementation(featureDi("shuntCard"))
                        //implementation(feature("shuntCard", "impl", "data"))
                        //implementation(feature("shuntCard", "impl", "domain"))
                        implementation(feature("shuntCard", "impl", "presentation"))

                        // Symptom
                        implementation(featureApi("symptom", "nav"))
                        //implementation(feature("symptom", "impl", "data"))
                        //implementation(feature("symptom", "impl", "domain"))
                        implementation(feature("symptom", "impl", "presentation"))
                        implementation(featureDi("symptom"))

                    }
                }
            }

        }
    }
}