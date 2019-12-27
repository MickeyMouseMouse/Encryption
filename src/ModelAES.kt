import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

class ModelAES {
    private val aes = AES()

    private var data: ByteArray? = null
    private var secretKey: SecretKey? = null

    fun generateSecretKey(fileKey: File) {
        val secretKey = aes.generateKey()
        fileKey.writeBytes(secretKey.encoded)
    }

    fun setData(file: File) { data = file.readBytes() }

    fun setSecretKey(file: File): Boolean {
        return try {
            secretKey = SecretKeySpec(Files.readAllBytes(Paths.get(file.absolutePath)), "AES")
            true
        } catch (e: Exception) { false }
    }

    fun canEncrypt() = data != null && secretKey != null

    fun encrypt(file: File): String {
        val result = aes.applyAlgorithm(data!!, secretKey!!, Cipher.ENCRYPT_MODE)
        return writeDownResult(file, result)
    }

    fun decrypt(file: File): String {
        val result = aes.applyAlgorithm(data!!, secretKey!!, Cipher.DECRYPT_MODE)
        return writeDownResult(file, result)
    }

    private fun writeDownResult(file: File, result: ByteArray?): String {
        return if (result == null) {
            "Failed"
        } else {
            file.writeBytes(result)
            "Done"
        }
    }
}