package tests

import RSA
import AES
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

val rsa = RSA()
val aes = AES()

fun main() {
    testRSA()
    testAES()
    testAESWithRSA()
}

fun testRSA() {
    val pair = rsa.generateKeyPair()

    val message = "TestMessage".toByteArray()
    val code = rsa.encrypt(message, pair.public)
    val decode = rsa.decrypt(code!!, pair.private)
    if (message.contentEquals(decode!!))
        println("RSA test passed")
    else
        println("RSA test failed")

    val signature = rsa.makeSignature(message, pair.private)
    if (rsa.verifySignature(message, signature!!, pair.public))
        println("RSA signature test passed")
    else
        println("RSA signature test failed")
}

fun testAES() {
    val secretKey = aes.generateKey()

    val message = "TestMessage".toByteArray()
    val code = aes.applyAlgorithm(message, secretKey, Cipher.ENCRYPT_MODE)
    val decode = aes.applyAlgorithm(code!!, secretKey, Cipher.DECRYPT_MODE)
    if (message.contentEquals(decode!!))
        println ("AES test passed")
    else
        println("AES test failed")
}

fun testAESWithRSA() {
    val pair = rsa.generateKeyPair()
    val secretKey = aes.generateKey()

    val encryptedSecretKey = rsa.encrypt(secretKey.encoded, pair.public)
    val decryptedSecretKey = rsa.decrypt(encryptedSecretKey!!, pair.private)
    val secretKeyRestored = SecretKeySpec(decryptedSecretKey!!, "AES")

    if (secretKey == secretKeyRestored)
        println("AESWithRSA test passed")
    else
        println("AESWithRSA test failed")
}

/*
Some important info:
    String(file.readBytes(), Charset.defaultCharset())
    String(file.readBytes(), UTF_8)

    https://upread.ru/blog/articles-it/base64-java
 */