package document.docx

import document.IDocument
import document.ITable
import org.docx4j.openpackaging.packages.WordprocessingMLPackage
import java.io.File
import java.io.FileOutputStream

class DocxDocument : IDocument {
    private val doc: WordprocessingMLPackage = WordprocessingMLPackage.createPackage()

    override fun addTable(table: ITable) {
        if (table is DocxTable) doc.mainDocumentPart.addObject(table.data)
    }

    override fun addText(text: String) {
        doc.mainDocumentPart.addParagraphOfText(text)
    }

    override fun save(path: String) {
        val wordFile = File("myDoc.docx")
        println(wordFile.absolutePath)
        val fileOut = FileOutputStream(wordFile)
        doc.save(fileOut)
        fileOut.close()
    }
}