package document.docx

import document.ITable
import document.IText
import org.docx4j.model.table.TblFactory
import org.docx4j.wml.Tbl
import org.docx4j.wml.Tc
import org.docx4j.wml.Tr

class DocxTable(rows: Int, cols: Int, cellWidth: Int) : ITable {

    private val table: Tbl

    val data: Tbl
        get() = table

    init {
        table = TblFactory.createTable(rows, cols, cellWidth)
    }

    override fun setText(row: Int, col: Int, text: IText) {
        // Получаем ячейку таблицы по индексу строки и столбца
        val tableRow = table.content[row] as Tr
        val tableColumn = tableRow.content[col] as Tc

        // Добавляем параграф в ячейку
        if (text is DocxText)
            tableColumn.content.add(text.data)
    }


}