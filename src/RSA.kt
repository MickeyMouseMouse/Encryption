import java.security.*
import javax.crypto.Cipher

class RSA {
    fun generateKeyPair(): KeyPair {
        val generator = KeyPairGenerator.getInstance("RSA")
        generator.initialize(2048, SecureRandom())
        return generator.generateKeyPair()
    }

    fun encrypt(message: ByteArray, publicKey: PublicKey): ByteArray? {
        return try {
            val cipher = Cipher.getInstance("RSA")
            cipher.init(Cipher.ENCRYPT_MODE, publicKey)
            cipher.doFinal(message)
        } catch (e: Exception) { null }
    }

    fun decrypt(code: ByteArray, privateKey: PrivateKey): ByteArray? {
        return try {
            val cipher = Cipher.getInstance("RSA")
            cipher.init(Cipher.DECRYPT_MODE, privateKey)
            return cipher.doFinal(code)
        } catch(e: Exception) { null }
    }

    fun makeSignature(message: ByteArray, privateKey: PrivateKey): ByteArray? {
        return try {
            val privateSignature = Signature.getInstance("SHA256withRSA")
            privateSignature.initSign(privateKey)
            privateSignature.update(message)
            privateSignature.sign()
        } catch(e: Exception) { null }
    }

    fun verifySignature(message: ByteArray, signature: ByteArray, publicKey: PublicKey): Boolean {
        return try {
            val publicSignature = Signature.getInstance("SHA256withRSA")
            publicSignature.initVerify(publicKey)
            publicSignature.update(message)
            publicSignature.verify(signature)
        } catch (e: Exception) { false }
    }
}