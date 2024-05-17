package document.markdown

import document.ITable
import document.IText

class MdTable(val rows: Int, val cols: Int) : ITable, IMdElement {

    val table: List<MutableList<String>> = List(rows) {
        MutableList(cols) { " " }
    }

    override fun setText(row: Int, col: Int, text: IText) {
        table[row][col] = text.value
    }
}