import javafx.application.Application
import javafx.geometry.HPos
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Tab
import javafx.scene.image.Image
import javafx.scene.layout.Border
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.GridPane
import javafx.scene.layout.RowConstraints
import javafx.stage.Stage

class GUI : Application() {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(GUI::class.java)
        }
    }

    private val cont = Controller()

    override fun start(stage: Stage) {
        stage.title = "Text Encryption"
        stage.isResizable = false
        stage.icons.add(Image("icon.png"))
        stage.centerOnScreen()

        cont.info.minWidth = 310.0
        cont.info.alignment = Pos.CENTER
        cont.info.style = "-fx-border-width: 1; -fx-border-color: black; -fx-font-size: 19px"

        cont.linkGithub.border = Border.EMPTY
        cont.linkGithub.style = "-fx-font-size: 14px; -fx-text-fill: black"
        cont.linkGithub.setOnMouseClicked { cont.openGitHub() }

        // RSA
        cont.paneRSA.children.addAll(cont.buttonGenerateKeyPair, cont.buttonSetDataRSA,
            cont.buttonSetPublicKey, cont.buttonSetPrivateKey, cont.buttonSetSignature,
            cont.buttonEncryptRSA, cont.buttonDecryptRSA, cont.buttonMakeSignature,
            cont.buttonVerifySignature)

        cont.buttonGenerateKeyPair.layoutX = 15.0
        cont.buttonGenerateKeyPair.layoutY = 10.0
        cont.buttonGenerateKeyPair.minWidth = 300.0
        cont.buttonGenerateKeyPair.maxWidth = 300.0
        cont.buttonGenerateKeyPair.style = "-fx-font-size: 16px"
        cont.buttonGenerateKeyPair.setOnMouseClicked { cont.generateKeyPairRSA() }

        cont.buttonSetDataRSA.layoutX = 15.0
        cont.buttonSetDataRSA.layoutY = 55.0
        cont.buttonSetDataRSA.minWidth = 140.0
        cont.buttonSetDataRSA.maxWidth = 140.0
        cont.buttonSetDataRSA.style = "-fx-font-size: 16px"
        cont.buttonSetDataRSA.setOnMouseClicked { cont.setDataRSA() }

        cont.buttonSetPublicKey.layoutX = 15.0
        cont.buttonSetPublicKey.layoutY = 100.0
        cont.buttonSetPublicKey.minWidth = 140.0
        cont.buttonSetPublicKey.maxWidth = 140.0
        cont.buttonSetPublicKey.style = "-fx-font-size: 16px"
        cont.buttonSetPublicKey.setOnMouseClicked { cont.setPublicKeyRSA() }

        cont.buttonSetPrivateKey.layoutX = 15.0
        cont.buttonSetPrivateKey.layoutY = 145.0
        cont.buttonSetPrivateKey.minWidth = 140.0
        cont.buttonSetPrivateKey.maxWidth = 140.0
        cont.buttonSetPrivateKey.style = "-fx-font-size: 16px"
        cont.buttonSetPrivateKey.setOnMouseClicked { cont.setPrivateKeyRSA() }

        cont.buttonSetSignature.layoutX = 15.0
        cont.buttonSetSignature.layoutY = 190.0
        cont.buttonSetSignature.minWidth = 140.0
        cont.buttonSetSignature.maxWidth = 140.0
        cont.buttonSetSignature.style = "-fx-font-size: 16px"
        cont.buttonSetSignature.setOnMouseClicked { cont.setSignatureRSA() }

        cont.buttonEncryptRSA.layoutX = 175.0
        cont.buttonEncryptRSA.layoutY = 55.0
        cont.buttonEncryptRSA.minWidth = 140.0
        cont.buttonEncryptRSA.maxWidth = 140.0
        cont.buttonEncryptRSA.style = "-fx-font-size: 16px"
        cont.buttonEncryptRSA.setOnMouseClicked { cont.encryptRSA() }

        cont.buttonDecryptRSA.layoutX = 175.0
        cont.buttonDecryptRSA.layoutY = 100.0
        cont.buttonDecryptRSA.minWidth = 140.0
        cont.buttonDecryptRSA.maxWidth = 140.0
        cont.buttonDecryptRSA.style = "-fx-font-size: 16px"
        cont.buttonDecryptRSA.setOnMouseClicked { cont.decryptRSA() }

        cont.buttonMakeSignature.layoutX = 175.0
        cont.buttonMakeSignature.layoutY = 145.0
        cont.buttonMakeSignature.minWidth = 140.0
        cont.buttonMakeSignature.maxWidth = 140.0
        cont.buttonMakeSignature.style = "-fx-font-size: 16px"
        cont.buttonMakeSignature.setOnMouseClicked { cont.makeSignatureRSA() }

        cont.buttonVerifySignature.layoutX = 175.0
        cont.buttonVerifySignature.layoutY = 190.0
        cont.buttonVerifySignature.minWidth = 140.0
        cont.buttonVerifySignature.maxWidth = 140.0
        cont.buttonVerifySignature.style = "-fx-font-size: 16px"
        cont.buttonVerifySignature.setOnMouseClicked { cont.verifySignatureRSA() }

        // AES
        cont.paneAES.children.addAll(cont.buttonGenerateSecretKey, cont.buttonSetDataAES,
            cont.buttonSetSecretKey, cont.buttonEncryptAES, cont.buttonDecryptAES)

        cont.buttonGenerateSecretKey.layoutX = 15.0
        cont.buttonGenerateSecretKey.layoutY = 10.0
        cont.buttonGenerateSecretKey.minWidth = 300.0
        cont.buttonGenerateSecretKey.maxWidth = 300.0
        cont.buttonGenerateSecretKey.style = "-fx-font-size: 16px"
        cont.buttonGenerateSecretKey.setOnMouseClicked { cont.generateSecretKeyAES() }

        cont.buttonSetDataAES.layoutX = 15.0
        cont.buttonSetDataAES.layoutY = 55.0
        cont.buttonSetDataAES.minWidth = 140.0
        cont.buttonSetDataAES.maxWidth = 140.0
        cont.buttonSetDataAES.style = "-fx-font-size: 16px"
        cont.buttonSetDataAES.setOnMouseClicked { cont.setDataAES() }

        cont.buttonSetSecretKey.layoutX = 15.0
        cont.buttonSetSecretKey.layoutY = 100.0
        cont.buttonSetSecretKey.minWidth = 140.0
        cont.buttonSetSecretKey.maxWidth = 140.0
        cont.buttonSetSecretKey.style = "-fx-font-size: 16px"
        cont.buttonSetSecretKey.setOnMouseClicked { cont.setSecretKeyAES() }

        cont.buttonEncryptAES.layoutX = 175.0
        cont.buttonEncryptAES.layoutY = 55.0
        cont.buttonEncryptAES.minWidth = 140.0
        cont.buttonEncryptAES.maxWidth = 140.0
        cont.buttonEncryptAES.style = "-fx-font-size: 16px"
        cont.buttonEncryptAES.setOnMouseClicked { cont.encryptAES() }

        cont.buttonDecryptAES.layoutX = 175.0
        cont.buttonDecryptAES.layoutY = 100.0
        cont.buttonDecryptAES.minWidth = 140.0
        cont.buttonDecryptAES.maxWidth = 140.0
        cont.buttonDecryptAES.style = "-fx-font-size: 16px"
        cont.buttonDecryptAES.setOnMouseClicked { cont.decryptAES() }

        // tabPane settings
        cont.tabPane.stylesheets.add("style.css")
        cont.tabPane.minWidth = 330.0
        cont.tabPane.maxWidth = 330.0

        // gridPane settings
        cont.gridPane.layoutX = 0.0
        cont.gridPane.layoutY = 0.0

        // only one column for gridPane
        val column = ColumnConstraints(330.0, 330.0, 330.0)
        cont.gridPane.columnConstraints.add(column)

        // first row (status bar)
        cont.gridPane.rowConstraints.add(RowConstraints(35.0))
        GridPane.setRowIndex(cont.info, 0)
        GridPane.setHalignment(cont.info, HPos.CENTER)

        // second row (tabPane)
        cont.gridPane.rowConstraints.add(RowConstraints(255.0))
        GridPane.setRowIndex(cont.tabPane, 1)

            val rsaTab = Tab("RSA")
            rsaTab.isClosable = false
            cont.tabPane.tabs.add(rsaTab)
            cont.tabPane.selectionModel.select(rsaTab)
            rsaTab.content = cont.paneRSA

            val aesTab = Tab("AES")
            aesTab.isClosable = false
            cont.tabPane.tabs.add(aesTab)
            aesTab.content = cont.paneAES

        // third row (link to github)
        cont.gridPane.rowConstraints.add(RowConstraints(20.0))
        GridPane.setRowIndex(cont.linkGithub, 2)
        GridPane.setHalignment(cont.linkGithub, HPos.CENTER)

        cont.gridPane.children.addAll(cont.info, cont.tabPane, cont.linkGithub)

        stage.scene = Scene(cont.gridPane)
        stage.show()
    }
}