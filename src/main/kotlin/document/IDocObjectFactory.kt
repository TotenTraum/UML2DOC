package document

/** Интерфейс фабрики объектов документа */
interface IDocObjectFactory {
    /** Создание таблицы
     * @param rows количество строк в таблице
     * @param cols количество столбцов в таблице
     * @param cellWidth ширина ячейки
     */
    fun createTable(rows: Int, cols: Int, cellWidth: Int): ITable

    /** Создание документа */
    fun createDocument(): IDocument

    /** Создание текста
     * @param text текст
     * @param fontSize размер шрифта (по умолчанию: 24 => 12 px)
     */
    fun createText(text: String, fontSize: Int = 24): IText
}