package document

interface IDocument {
    fun addTable(table: ITable)

    //TODO change in-arg type
    fun addText(text: IText)

    fun save(path: String)
}