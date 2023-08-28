package document

/** Интерфейс документа
 * @see IDocObjectFactory
 */
interface IDocument {
    /** Добавляет таблицу в документ
     * @param table вставляемая таблица
     * @see ITable
     */
    fun addTable(table: ITable)

    /** Добавляет текст в документ
     * @param text вставляемый текст
     * @see IText
     */
    fun addText(text: IText)

    /** Сохраняет документ в файл
     * @param path путь к файлу
     */
    fun save(path: String)
}