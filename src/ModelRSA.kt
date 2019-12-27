import java.io.File
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec

class ModelRSA {
    private val rsa = RSA()

    private var data: ByteArray? = null
    private var publicKey: PublicKey? = null
    private var privateKey: PrivateKey? = null
    private var signature: String? = null

    fun generateKeyPair(filePublicKey: File, filePrivateKey: File) {
        val keyPair = rsa.generateKeyPair()
        filePublicKey.writeBytes(keyPair.public.encoded)
        filePrivateKey.writeBytes(keyPair.private.encoded)
    }

    fun setData(file: File) { data = file.readBytes() }

    fun setPublicKey(file: File): Boolean {
        return try {
            val keyBytes = Files.readAllBytes(Paths.get(file.absolutePath))
            val kf = KeyFactory.getInstance("RSA")
            publicKey = kf.generatePublic(X509EncodedKeySpec(keyBytes))
            true
        } catch (e: Exception) { false }
    }

    fun setPrivateKey(file: File): Boolean {
        return try {
            val keyBytes = Files.readAllBytes(Paths.get(file.absolutePath))
            val kf = KeyFactory.getInstance("RSA")
            privateKey = kf.generatePrivate(PKCS8EncodedKeySpec(keyBytes))
            true
        } catch (e: Exception) { false }
    }

    fun setSignature(file: File) {
        signature = String(file.readBytes(), Charset.defaultCharset())
    }

    fun canEncrypt(): Boolean {
        if (data == null) return false
        return (data!!.size <= 245)
    }

    fun encrypt(file: File): String {
        if (data == null || publicKey == null)
            return "Set data and public key"
        val result = rsa.encrypt(data!!, publicKey!!)
        return writeDownResult(file, result)
    }

    fun decrypt(file: File): String {
        if (data == null || privateKey == null)
            return "Set data and private key"
        val result = rsa.decrypt(data!!, privateKey!!)
        return writeDownResult(file, result)
    }

    fun makeSignature(file: File): String {
        if (data == null || privateKey == null)
            return "Set data and private key"
        val result = rsa.makeSignature(data!!, privateKey!!)
        return writeDownResult(file, result)
    }

    private fun writeDownResult(file: File, result: String?): String {
        return if (result == null) {
            "Failed"
        } else {
            file.writeText(result)
            "Done"
        }
    }

    fun verifySignature(): String {
        if (data == null || signature == null || publicKey == null)
            return "Set data, signature and public key"

        return if (rsa.verifySignature(data!!, signature!!, publicKey!!))
            "Signatures matched ✔"
        else
            "!!! Signatures didn't match !!!"
    }
}