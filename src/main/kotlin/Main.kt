import net.sourceforge.plantuml.SourceStringReader
import net.sourceforge.plantuml.abel.Entity
import net.sourceforge.plantuml.classdiagram.ClassDiagram
import net.sourceforge.plantuml.plasma.Quark
import org.docx4j.jaxb.Context
import org.docx4j.model.table.TblFactory
import org.docx4j.openpackaging.packages.WordprocessingMLPackage
import org.docx4j.wml.*
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception


import java.util.stream.Stream

fun toDataModelTable(doc: WordprocessingMLPackage, stream: Stream<Quark<Entity>>) {
    stream.forEach { entity ->
        val mainDocumentPart = doc.mainDocumentPart
        mainDocumentPart.addParagraphOfText("Справочник \"${entity.name}\" предназначен для хранения информации о статусах задач.  Атрибуты справочника ")

        val writableWidthTwips: Int = doc.documentModel
            .sections[0].pageDimensions.writableWidthTwips

        val table = TblFactory.createTable(entity.data.bodier.fieldsToDisplay.size() + 1, 3, writableWidthTwips)


        setTableCellText(table, 0, 0, "Параметр")
        setTableCellText(table, 0, 1, "Тип")
        setTableCellText(table, 0, 2, "Примечание")
        entity.data.bodier.fieldsToDisplay.forEachIndexed { index, charSequence ->
            val list = charSequence.toString().split(":")
            if (list.count() != 2)
                println("incorrect $list")
            setTableCellText(table, index + 1, 0, list[0])
            setTableCellText(table, index + 1, 1, list[1])
            setTableCellText(table, index + 1, 2, "")
        }

        doc.mainDocumentPart.addObject(table)
    }
}

fun setTableCellText(table: Tbl, rowIndex: Int, columnIndex: Int, text: String) {
    val factory = Context.getWmlObjectFactory()

    // Получаем ячейку таблицы по индексу строки и столбца
    val tr = table.content[rowIndex] as Tr
    val tc = tr.content[columnIndex] as Tc

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

fun main(args: Array<String>) {
    val docx = WordprocessingMLPackage.createPackage()

    val file = File("source.puml")

    if (!file.exists()) {
        file.createNewFile()
    }

    val source = file.readText()

    val r = SourceStringReader(source)
    println(r.blocks.size)
    val cd = r.blocks[0].diagram as ClassDiagram
    val classes = cd.entityFactory.root().children

    toDataModelTable(docx, classes.stream())

    val wordFile = File("myDoc.docx")
    try {
        val fileOut = FileOutputStream(wordFile)
        docx.save(fileOut)
        fileOut.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}