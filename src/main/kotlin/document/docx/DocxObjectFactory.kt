package document.docx

import document.IDocObjectFactory
import document.IDocument
import document.ITable
import document.IText

class DocxObjectFactory : IDocObjectFactory {
    override fun createTable(rows: Int, cols: Int, cellWidth: Int): ITable = DocxTable(rows, cols, cellWidth)

    override fun createDocument(): IDocument = DocxDocument()

    override fun createText(text: String, fontSize: Int):IText = DocxText(text, fontSize)
}