# AES Encryption/Decryption in Kotlin Multiplatform (KMP)

This project provides an implementation of AES encryption and decryption using Kotlin Multiplatform (KMP), allowing you to securely encrypt and decrypt data across platforms like Android, iOS, and more. The encryption is done using AES in CFB mode with no padding, and the data is Base64 encoded to facilitate transport.

## Features

- AES encryption and decryption using `AES/CFB/NoPadding` mode.
- Cross-platform support for Android and iOS (via Kotlin/Native).
- Base64 encoding/decoding for easy data exchange.
- Uses the [Krypto](https://github.com/korlibs/krypto) library for cryptographic functions.

## Requirements

- Kotlin 1.6+ (for Kotlin Multiplatform support)
- Android Studio or Xcode (for Android and iOS development, respectively)

## Example

Here’s a complete example showing encryption and decryption in Kotlin Multiplatform:

```kotlin
import kotlin.random.Random

val key = "Amary-KMPEncryptDecryptAESReqRes"  // Key must be 32 bytes
val message = "Hello, Kotlin Multiplatform!"

// Encrypt the message
val encryptedMessage = message.encrypt(key)
println("Encrypted: $encryptedMessage")

// Decrypt the message
val decryptedMessage = encryptedMessage?.decrypt(key)
println("Decrypted: $decryptedMessage")
```

## Platform Support

- **Android**: The encryption and decryption functions work seamlessly on Android.
- **iOS**: The same encryption and decryption functions work on iOS using Kotlin/Native.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

