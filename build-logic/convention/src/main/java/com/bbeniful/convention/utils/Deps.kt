package com.bbeniful.convention.utils

object Deps {
    const val KOTLIN_SERIALIZATION = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0"

    object Projects {
        const val APP = ":app"
        const val DOMAIN = ":app"
        const val DATA = ":app"
        const val PRESENTATION = ":app"

        object Core {
            const val CORE_DOMAIN = ":core:$DOMAIN"
            const val CORE_DATA = ":core:$DATA"
            const val CORE_PRESENTATION = ":core:$PRESENTATION"
        }

    }
}