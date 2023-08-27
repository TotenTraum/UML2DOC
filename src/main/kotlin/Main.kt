import document.IDocObjectFactory
import document.IDocument
import document.ITable
import document.docx.DocxObjectFactory
import net.sourceforge.plantuml.SourceStringReader
import net.sourceforge.plantuml.abel.Entity
import net.sourceforge.plantuml.classdiagram.ClassDiagram
import net.sourceforge.plantuml.plasma.Quark
import java.io.File
import java.util.stream.Stream

fun toDataModelTable(factory: IDocObjectFactory, doc: IDocument, stream: Stream<Quark<Entity>>) {
    stream.forEach { entity ->
        if (!entity.data.bodier.fieldsToDisplay.any() && !entity.data.bodier.methodsToDisplay.any())
            return@forEach

        doc.addText("Справочник \"${entity.name}\" предназначен для хранения информации о статусах задач.  Атрибуты справочника ")
        if (entity.data.bodier.fieldsToDisplay.any())
            doc.addTable(fieldTable(factory, entity))
        if (entity.data.bodier.methodsToDisplay.any())
            doc.addTable(methodTable(factory, entity))
    }
}

fun fieldTable(factory: IDocObjectFactory, entity: Quark<Entity>): ITable {
    val table = factory.createTable(entity.data.bodier.fieldsToDisplay.size() + 1, 3, 3000)

    table.setText(0, 0, "Параметр")
    table.setText(0, 1, "Тип")
    table.setText(0, 2, "Примечание")
    entity.data.bodier.fieldsToDisplay.forEachIndexed { index, charSequence ->
        val list = charSequence.toString().split(":")
        if (list.count() != 2)
            println("incorrect $list")
        table.setText(index + 1, 0, list[0])
        table.setText(index + 1, 1, list[1])
        table.setText(index + 1, 2, "")
    }
    return table
}

fun methodTable(factory: IDocObjectFactory, entity: Quark<Entity>): ITable {
    val table = factory.createTable(entity.data.bodier.methodsToDisplay.size() + 1, 2, 4500)

    table.setText(0, 0, "Функция")
    table.setText(0, 1, "Примечание")
    entity.data.bodier.methodsToDisplay.forEachIndexed { index, charSequence ->
        table.setText(index + 1, 0, charSequence.toString())
        table.setText(index + 1, 1, "")
    }
    return table
}

fun main(args: Array<String>) {
    val factory: IDocObjectFactory = DocxObjectFactory()

    val doc = factory.createDocument()

    val file = File("IDocObjectFactory.plantuml")

    if (!file.exists()) {
        file.createNewFile()
    }

    val source = file.readText()

    val r = SourceStringReader(source)
    println(r.blocks.size)
    println(r.blocks[0].diagram is ClassDiagram)
    val cd = r.blocks[0].diagram as ClassDiagram
//    val cd = r.blocks[0].diagram as DescriptionDiagram

    val classes = cd.entityFactory.root().children

    toDataModelTable(factory, doc, classes.stream())

    doc.save("")
}