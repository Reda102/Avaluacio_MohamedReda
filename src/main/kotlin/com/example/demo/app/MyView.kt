package com.example.demo.app

import com.example.demo.app.controller.MyController
import tornadofx.View
import javafx.beans.property.SimpleIntegerProperty
import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import javafx.stage.FileChooser
import tornadofx.*
import java.io.File

class MyView : View() {
    // TornadoFX delegates.
    override val root: BorderPane by fxml()
    val controller: MyController by inject()

    // Observable
    val counter = SimpleIntegerProperty()
    val btnAbrirFichero: Button by fxid()
    val btnDesa: Button by fxid()
    val espText: TextArea by fxid()

    val file = File("filename.txt")
    private val ef = arrayOf(FileChooser.ExtensionFilter("Document files (*.txt)", "*.txt"))

    init {

        btnAbrirFichero.action {
            abriFitxer()
        }

        btnDesa.action {
            desaFitxer()
        }
        espText.text {
        }


    }
    private fun desaFitxer() {

        val filechooser = FileChooser()
        filechooser.extensionFilters.addAll(FileChooser.ExtensionFilter("Texto", "*.txt"),
            FileChooser.ExtensionFilter("All Files", "*.*"))

        var file = filechooser.showSaveDialog(null)
        var contingutfitxer:String = espText.text.toString()
        try {
            file.printWriter().use { out ->
                out.println(contingutfitxer)
            }
        }catch(ex:IllegalStateException){
            println("No has guardado bien ....")
        }

    }

    private fun abriFitxer(){
        var s:String=" "
        val fn: List<File> = chooseFile("Seleccion el fichero...",ef, FileChooserMode.Single)

        if (fn.isNotEmpty()) {
            s = "${fn.first()}"
            espText.text= ""
            val file = File(s).inputStream().readBytes().toString(Charsets.UTF_8)
            espText.text = file
        }
    }

}
