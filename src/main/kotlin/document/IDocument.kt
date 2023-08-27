package document

interface IDocument {
    fun addTable(table: ITable)

    //TODO change in-arg type
    fun addText(text: String)

    fun save(path: String)
}