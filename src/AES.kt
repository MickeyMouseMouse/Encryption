import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey


class AES {
    fun generateKey(): SecretKey {
        val generator = KeyGenerator.getInstance("AES")
        generator.init(256)
        return generator.generateKey()
    }

    // mode = Cipher.ENCRYPT_MODE / Cipher.DECRYPT_MODE
    fun applyAlgorithm(message: ByteArray, key: SecretKey, mode: Int): ByteArray? {
        return try {
            val cipher = Cipher.getInstance("AES")
            cipher.init(mode, key)
            return cipher.doFinal(message)
        } catch (e: Exception) { null }
    }
}