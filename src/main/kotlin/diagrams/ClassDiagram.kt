package diagrams

import document.IDocObjectFactory
import document.IDocument
import document.ITable
import net.sourceforge.plantuml.abel.Entity
import net.sourceforge.plantuml.plasma.Quark
import java.util.stream.Stream

class ClassDiagram(private val stream: Stream<Quark<Entity>>) : IDiagram {
    override fun write(factory: IDocObjectFactory, doc: IDocument) {
        stream.forEach { entity ->
            if (!entity.data.bodier.fieldsToDisplay.any() && !entity.data.bodier.methodsToDisplay.any())
                return@forEach
            val text =
                factory.createText("Справочник \"${entity.name}\" предназначен для хранения информации о статусах задач.")
            doc.addText(text)
            if (entity.data.bodier.fieldsToDisplay.any())
                doc.addTable(fieldTable(factory, entity))
            if (entity.data.bodier.methodsToDisplay.any())
                doc.addTable(methodTable(factory, entity))
        }
    }

    private fun fieldTable(factory: IDocObjectFactory, entity: Quark<Entity>): ITable {
        val table = factory.createTable(entity.data.bodier.fieldsToDisplay.size() + 1, 3, 3000)

        table.setText(factory, 0, 0, "Параметр")
        table.setText(factory, 0, 1, "Тип")
        table.setText(factory, 0, 2, "Примечание")
        entity.data.bodier.fieldsToDisplay.forEachIndexed { index, charSequence ->
            val list = charSequence.toString().trim().replace("\\s+".toRegex(), " ").replace(" :".toRegex(), ":")
                .split(": ", ":", " ")
            if (list.count() != 2)
                println("incorrect $list")
            table.setText(factory, index + 1, 0, list[0])
            table.setText(factory, index + 1, 1, list[1])
            table.setText(factory, index + 1, 2, "")
        }
        return table
    }

    private fun ITable.setText(factory: IDocObjectFactory, row: Int, col: Int, text: String, fontSize: Int = 24) {
        val textObj = factory.createText(text, fontSize)
        setText(row, col, textObj)
    }

    private fun methodTable(factory: IDocObjectFactory, entity: Quark<Entity>): ITable {
        val table = factory.createTable(entity.data.bodier.methodsToDisplay.size() + 1, 2, 4500)

        table.setText(factory, 0, 0, "Функция")
        table.setText(factory, 0, 1, "Примечание")

        entity.data.bodier.methodsToDisplay.forEachIndexed { index, charSequence ->
            val list = charSequence.toString().trim().replace("\\s+".toRegex(), " ").split(" ")
            val str = list.joinToString(separator = " ") { it.visibilityStatus() }
            table.setText(factory, index + 1, 0, str)
            table.setText(factory, index + 1, 1, "")
        }
        return table
    }

    private fun String.visibilityStatus(): String = when (trim()) {
        "+" -> "public"
        "#" -> "protected"
        "~" -> "package private"
        "-" -> "private"
        else -> this
    }
}