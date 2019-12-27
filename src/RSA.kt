import java.nio.charset.StandardCharsets.UTF_8
import java.security.*
import java.util.*
import javax.crypto.Cipher

class RSA {
    fun generateKeyPair(): KeyPair {
        val generator = KeyPairGenerator.getInstance("RSA")
        generator.initialize(2048, SecureRandom())
        return generator.generateKeyPair()
    }

    fun encrypt(message: ByteArray, publicKey: PublicKey): String? {
        return try {
            val cipher = Cipher.getInstance("RSA")
            cipher.init(Cipher.ENCRYPT_MODE, publicKey)
            return Base64.getEncoder().encodeToString(cipher.doFinal(message))
        } catch (e: Exception) { null }
    }

    fun decrypt(code: ByteArray, privateKey: PrivateKey): String? {
        return try {
            val cipher = Cipher.getInstance("RSA")
            cipher.init(Cipher.DECRYPT_MODE, privateKey)
            return String(cipher.doFinal(Base64.getDecoder().decode(code)), UTF_8)
        } catch(e: Exception) { null }
    }

    fun makeSignature(message: ByteArray, privateKey: PrivateKey): String? {
        return try {
            val privateSignature = Signature.getInstance("SHA256withRSA")
            privateSignature.initSign(privateKey)
            privateSignature.update(message)
            Base64.getEncoder().encodeToString(privateSignature.sign())
        } catch(e: Exception) { null }
    }

    fun verifySignature(message: ByteArray, signature: String, publicKey: PublicKey): Boolean {
        return try {
            val publicSignature = Signature.getInstance("SHA256withRSA")
            publicSignature.initVerify(publicKey)
            publicSignature.update(message)
            publicSignature.verify(Base64.getDecoder().decode(signature))
        } catch (e: Exception) { false }
    }
}