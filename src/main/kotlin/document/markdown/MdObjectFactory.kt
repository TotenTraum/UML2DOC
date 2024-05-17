package document.markdown

import document.IDocObjectFactory
import document.IDocument
import document.ITable
import document.IText

class MdObjectFactory : IDocObjectFactory {
    /** Создание таблицы
     * @param rows количество строк в таблице
     * @param cols количество столбцов в таблице
     * @param cellWidth ширина ячейки
     */
    override fun createTable(rows: Int, cols: Int, cellWidth: Int): ITable {
        return MdTable(rows, cols)
    }

    /** Создание документа */
    override fun createDocument(): IDocument {
        return MdDocument()
    }

    /** Создание текста
     * @param text текст
     * @param fontSize размер шрифта (по умолчанию: 24 => 12 px)
     */
    override fun createText(text: String, fontSize: Int): IText {
        return MdText(text)
    }
}