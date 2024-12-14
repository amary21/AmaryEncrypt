package com.amary.encrypt

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform