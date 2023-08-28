package document.docx

import document.IDocument
import document.ITable
import document.IText
import org.docx4j.openpackaging.packages.WordprocessingMLPackage
import java.io.File
import java.io.FileOutputStream

class DocxDocument : IDocument {
    private val doc: WordprocessingMLPackage = WordprocessingMLPackage.createPackage()

    override fun addTable(table: ITable) {
        if (table is DocxTable) doc.mainDocumentPart.addObject(table.data)
    }

    override fun addText(text: IText) {
        if (text is DocxText)
            doc.mainDocumentPart.addObject(text.data)
    }

    override fun save(path: String) {
        val wordFile = File(path)
        val fileOut = FileOutputStream(wordFile)
        doc.save(fileOut)
        fileOut.close()
    }
}