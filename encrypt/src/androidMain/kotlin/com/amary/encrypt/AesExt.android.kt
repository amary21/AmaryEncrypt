package com.amary.encrypt

import android.util.Base64
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

actual fun String?.encrypt(key: String?): String? {
    return if (this.isNullOrEmpty() || key?.length != 32) null else {
        try {
            val encodedString = this.toByteArray(Charsets.UTF_8)
            val keyToByteArray = key.toByteArray(Charsets.UTF_8)
            val cipher = Cipher.getInstance("AES/CFB/NoPadding")
            val blockSize = cipher.blockSize
            val secretKey = SecretKeySpec(keyToByteArray, "AES")
            val secureRandom = SecureRandom.getInstance("SHA1PRNG")

            val ivData = ByteArray(blockSize)
            secureRandom.nextBytes(ivData)
            val iv = IvParameterSpec(ivData)

            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv)

            val encryptedMessage = cipher.doFinal(encodedString)
            val ivAndEncryptedMessage = ByteArray(ivData.size + encryptedMessage.size)

            System.arraycopy(ivData, 0, ivAndEncryptedMessage, 0, blockSize)
            System.arraycopy(
                encryptedMessage, 0, ivAndEncryptedMessage,
                blockSize, encryptedMessage.size
            )

            Base64.encodeToString(ivAndEncryptedMessage, Base64.DEFAULT)
        } catch (e: Exception){
            e.printStackTrace()
            null
        }
    }
}

actual fun String?.decrypt(key: String?): String? {
    return if (this.isNullOrEmpty() || key?.length != 32) null else {
        try {
            val keyToByteArray = key.toByteArray(Charsets.UTF_8)
            val ivAndEncryptedMessage = Base64.decode(this.toByteArray(), Base64.DEFAULT)
            val cipher = Cipher.getInstance("AES/CFB/NoPadding")
            val blockSize = cipher.blockSize
            val secretKey = SecretKeySpec(keyToByteArray, "AES")
            val ivData = ByteArray(blockSize)

            System.arraycopy(ivAndEncryptedMessage, 0, ivData, 0, blockSize)

            val iv = IvParameterSpec(ivData)
            val encryptedMessage = ByteArray(ivAndEncryptedMessage.size - blockSize)

            System.arraycopy(
                ivAndEncryptedMessage, blockSize,
                encryptedMessage, 0, encryptedMessage.size
            )

            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv)

            val encodedMessage = cipher.doFinal(encryptedMessage)

            return String(encodedMessage, Charsets.UTF_8)
        } catch (e: Exception){
            e.printStackTrace()
            null
        }
    }
}