package document.markdown

import document.IDocument
import document.ITable
import document.IText
import java.io.File

class MdDocument : IDocument {

    var list = mutableListOf<IMdElement>()

    /** Добавляет таблицу в документ
     * @param table вставляемая таблица
     * @see ITable
     */
    override fun addTable(table: ITable) {
        if (table is IMdElement)
            list.add(table)
    }

    /** Добавляет текст в документ
     * @param text вставляемый текст
     * @see IText
     */
    override fun addText(text: IText) {
        if (text is IMdElement)
            list.add(text)
    }

    /** Сохраняет документ в файл
     * @param path путь к файлу
     */
    override fun save(path: String) {
        val wordFile = File(path)
        if (!wordFile.exists())
            wordFile.createNewFile()

        var prevTable: Boolean = false
        var countCols: Int = 0

        wordFile.printWriter().use { out ->
            for (elem in list) {
                if (elem is MdTable) {
                    if (elem.table.isEmpty())
                        continue

                    if (!prevTable || countCols != elem.cols)
                        out.println()

                    elem.table.first().forEach {
                        if (it != "")
                            out.print("|$it")
                        else
                            out.print("| ")
                    }
                    out.println("|")

                    if (!prevTable || countCols != elem.cols) {
                        repeat(elem.cols) {
                            out.print("|-------")
                        }
                        out.println("|")
                    }
                    elem.table.forEachIndexed { index, item ->
                        if (index == 0)
                            return@forEachIndexed
                        item.forEach {
                            if (it != "")
                                out.print("|$it")
                            else
                                out.print("| ")

                        }
                        out.println("|")
                    }

                    prevTable = true
                    countCols = elem.cols
                } else if (elem is MdText) {
                    prevTable = false
                    out.println(elem.value)
                }
            }
        }
    }
}