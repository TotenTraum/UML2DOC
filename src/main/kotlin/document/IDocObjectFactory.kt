package document

interface IDocObjectFactory {
    fun createTable(rows: Int, cols: Int, cellWidth: Int): ITable

    fun createDocument(): IDocument

    fun createText()
}