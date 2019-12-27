package tests

import RSA
import AES
import java.nio.charset.Charset
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

val rsa = RSA()
val aes = AES()

fun main() {
    testRSA()
    println("-------------------------")
    testAES()
    //println("-------------------------")
    //testAESWithRSA()
}

fun testRSA() {
    val pair = rsa.generateKeyPair()

    val message = "TestMessage"
    val code = rsa.encrypt(message.toByteArray(), pair.public)
    val decode = rsa.decrypt(code!!.toByteArray(), pair.private)
    println("$message = $decode")

    val signature = rsa.makeSignature(message.toByteArray(), pair.private)
    println(rsa.verifySignature(message.toByteArray(), signature!!, pair.public))
}

fun testAES() {
    val secretKey = aes.generateKey()

    val message = "TestMessage"
    val code = aes.applyAlgorithm(message.toByteArray(), secretKey, Cipher.ENCRYPT_MODE)
    val decode = String(aes.applyAlgorithm(code!!, secretKey, Cipher.DECRYPT_MODE)!!, Charset.defaultCharset())
    println("$message = $decode")
}

/*
fun testAESWithRSA() {
    val pair = rsa.generateKeyPair()
    val secretKey = aes.generateKey()

    val encryptedSecretKey = rsa.encrypt(secretKey.encoded, pair.public)
    val decryptedSecretKey = rsa.decrypt(encryptedSecretKey!!.toByteArray(), pair.private)
    val secretKeyRestored = SecretKeySpec(decryptedSecretKey!!.toByteArray(), "AES")

    println(secretKey == secretKeyRestored)
}
*/