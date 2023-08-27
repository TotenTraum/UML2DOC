package document.docx

import document.ITable
import org.docx4j.jaxb.Context
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

    override fun setText(row: Int, col: Int, text: String) {
        val factory = Context.getWmlObjectFactory()

        // Получаем ячейку таблицы по индексу строки и столбца
        val tr = table.content[row] as Tr
        val tc = tr.content[col] as Tc

        // Создаем параграф и добавляем в него текст
        val p = factory.createP()
        val r = factory.createR()
        val t = factory.createText()
        t.value = text
        r.content.add(t)
        p.content.add(r)

        // Добавляем параграф в ячейку
        tc.content.add(p)
    }


}