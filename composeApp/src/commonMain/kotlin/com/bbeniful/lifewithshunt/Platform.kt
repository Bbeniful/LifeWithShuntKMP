package com.bbeniful.lifewithshunt

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform