import javafx.scene.control.*
import javafx.scene.layout.GridPane
import javafx.scene.layout.Pane
import javafx.stage.FileChooser
import javafx.stage.Stage
import java.awt.Desktop
import java.io.File
import java.net.URI
import java.text.SimpleDateFormat
import java.util.*

class Controller {
    val gridPane = GridPane()
    val tabPane = TabPane()
    val paneRSA = Pane()
    val paneAES = Pane()

    var info = Label("Hello")
    val linkGithub = Hyperlink("View code in GitHub")

    val buttonGenerateKeyPair = Button("Generate New Key Pair")

    val buttonSetDataRSA = Button("Set Data")
    val buttonSetPublicKey = Button("Set Public Key")
    val buttonSetPrivateKey = Button("Set Private Key")
    val buttonSetSignature = Button("Set Signature")

    val buttonEncryptRSA = Button("Encrypt")
    val buttonDecryptRSA = Button("Decrypt")
    val buttonMakeSignature = Button("Make Signature")
    val buttonVerifySignature = Button("Verify Signature")

    val buttonGenerateSecretKey = Button("Generate New Secret Key")

    val buttonSetDataAES = Button("Set Data")
    val buttonSetSecretKey = Button("Set Secret Key")

    val buttonEncryptAES = Button("Encrypt")
    val buttonDecryptAES = Button("Decrypt")

    private val rsa = ModelRSA()
    private val aes = ModelAES()

    // RSA ALGORITHM
    private fun showHint() {
        val alert = Alert(Alert.AlertType.INFORMATION,
            "The first file will be the public key, the second file will be the private key.")
        val dialogPane = alert.dialogPane
        dialogPane.style = "-fx-font-size: 16px"
        alert.showAndWait()
    }

    fun generateKeyPairRSA() {
        showHint()

        val filePublicKey = saveFile("Save Public Key",
            "PublicKey_" + getDateAndTime()) ?: return

        val filePrivateKey = saveFile("Save Private Key",
            "PrivateKey_" + getDateAndTime())

        if (filePrivateKey == null) {
            filePublicKey.delete()
            return
        }

        rsa.generateKeyPair(filePublicKey, filePrivateKey)

        info.text = "Done"
    }

    fun setDataRSA() {
        val file = openFile() ?: return
        rsa.setData(file)
        info.text = "Data accepted"
    }

    fun setPublicKeyRSA() {
        val file = openFile() ?: return

        if (rsa.setPublicKey(file))
            info.text = "Public key accepted"
        else
            info.text = "Failed"
    }

    fun setPrivateKeyRSA() {
        val file = openFile() ?: return

        if (rsa.setPrivateKey(file))
            info.text = "Private key accepted"
        else
            info.text = "Failed"
    }

    fun setSignatureRSA() {
        val file = openFile() ?: return
        rsa.setSignature(file)
        info.text = "Signature accepted"
    }

    fun encryptRSA() {
        if (!rsa.canEncrypt()) {
            info.text = "File size should be in 1...245 bytes"
            return
        }

        val file = saveFile("Save Output File",
            "EncryptedRSA_" + getDateAndTime()) ?: return
        info.text = rsa.encrypt(file)
    }

    fun decryptRSA() {
        val file = saveFile("Save Output File",
            "DecryptedRSA_" + getDateAndTime()) ?: return
        info.text = rsa.decrypt(file)
    }

    fun makeSignatureRSA() {
        val file = saveFile("Save Signature",
            "Signature_" + getDateAndTime()) ?: return
        info.text = rsa.makeSignature(file)
    }

    fun verifySignatureRSA() { info.text = rsa.verifySignature() }

    // AES ALGORITHM
    fun generateSecretKeyAES() {
        val fileSecretKey = saveFile("Save Secret Key",
            "SecretKey_" + getDateAndTime()) ?: return

        aes.generateSecretKey(fileSecretKey)

        info.text = "Done"
    }

    fun setDataAES() {
        val file = openFile() ?: return
        aes.setData(file)
        info.text = "Data accepted"
    }

    fun setSecretKeyAES() {
        val file = openFile() ?: return

        if (aes.setSecretKey(file))
            info.text = "Secret key accepted"
        else
            info.text = "Failed"
    }

    fun encryptAES() {
        if (!aes.canEncrypt()) {
            info.text = "Set data and secret key"
            return
        }

        val file = saveFile("Save Output File",
            "EncryptedAES_" + getDateAndTime()) ?: return
        info.text = aes.encrypt(file)
    }

    fun decryptAES() {
        if (!aes.canEncrypt()) {
            info.text = "Set data and secret key"
            return
        }

        val file = saveFile("Save Output File",
            "DecryptedAES_" + getDateAndTime()) ?: return
        info.text = aes.decrypt(file)
    }

    // OTHER
    private fun openFile(): File? {
        val fileChooser = FileChooser()
        fileChooser.title = "Open Text File"
        fileChooser.extensionFilters.add(FileChooser.ExtensionFilter("Text File", "*.txt"))
        return fileChooser.showOpenDialog(Stage())
    }

    private fun saveFile(title: String, fileName: String): File? {
        val fileChooser = FileChooser()
        fileChooser.title = title
        fileChooser.initialFileName = fileName
        fileChooser.extensionFilters.add(FileChooser.ExtensionFilter("Text File", "*.txt"))
        return fileChooser.showSaveDialog(Stage())
    }

    private fun getDateAndTime(): String {
        val formatter = SimpleDateFormat("dd_MM_yyyy-HH_mm_ss")
        return formatter.format(Calendar.getInstance().time)
    }

    fun openGitHub() {
        Desktop.getDesktop().browse(URI("https://github.com/MickeyMouseMouse/Encryption"))
    }
}