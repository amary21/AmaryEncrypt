package com.amary.encrypt

expect fun String?.encrypt(key: String? = AesKey.DEFAULT): String?

expect fun String?.decrypt(key: String? = AesKey.DEFAULT): String?