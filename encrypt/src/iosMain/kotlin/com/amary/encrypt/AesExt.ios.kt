package com.amary.encrypt

import korlibs.crypto.AES
import korlibs.crypto.CipherPadding
import korlibs.crypto.encoding.base64
import korlibs.crypto.encoding.fromBase64
import kotlin.random.Random

actual fun String?.encrypt(key: String?): String? {
    return if (this.isNullOrEmpty() || key?.length != 32) null else {
        try {
            // Get block size for AES (typically 16 bytes)
            val ivSize = 16
            // Generate a random IV using Kotlin's random function
            val ivBlocSize = Random.nextBytes(ivSize)
            val secretKey = key.encodeToByteArray()

            // Create AES cipher with CBC mode and no padding
            val encryptedMessage = AES.encryptAesCfb(
                data = this.encodeToByteArray(),
                iv = ivBlocSize,
                key = secretKey,
                padding = CipherPadding.NoPadding
            )

            // Combine the IV and encrypted message
            val ivAndEncryptedMessage = ivBlocSize + encryptedMessage

            // Return the result as a Base64 encoded string
            return ivAndEncryptedMessage.base64
       } catch (e: Exception) {
           e.printStackTrace()
            return null
       }
  }
}

actual fun String?.decrypt(key: String?): String? {
    return if (this.isNullOrEmpty() || key?.length != 32) null else {
        try {
            // Decode the Base64-encoded input string
            val ivAndEncryptedMessage = this.fromBase64()

            // Get the IV size (16 bytes for AES)
            val ivSize = 16
            val ivData = ByteArray(ivSize)

            // Extract IV (first 16 bytes) using copyInto
            ivAndEncryptedMessage.copyInto(ivData, 0, 0, ivSize)

            // Extract the encrypted message (after the first 16 bytes) using copyOfRange
            val encryptedMessage = ivAndEncryptedMessage.copyOfRange(ivSize, ivAndEncryptedMessage.size)

            val secretKey = key.encodeToByteArray()
            // Decrypt the message using CFB mode and no padding
            val decryptedBytes = AES.decryptAesCfb(
                data = encryptedMessage,
                iv = ivData,
                key = secretKey,
                padding = CipherPadding.NoPadding
            )
            return decryptedBytes.decodeToString()
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}