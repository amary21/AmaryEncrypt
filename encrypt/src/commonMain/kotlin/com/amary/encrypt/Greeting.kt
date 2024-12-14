package com.amary.encrypt

class Greeting {
    private val platform: Platform = getPlatform()

    private val platformEncrypt: String = "Hello, ${platform.name}!".encrypt().toString()

    private val platformDecrypt: String = platformEncrypt.decrypt().toString()

    fun encrypt(): String {
        return platformEncrypt
    }

    fun decrypt(): String {
        return platformDecrypt
    }
}