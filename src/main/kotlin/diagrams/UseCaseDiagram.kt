package diagrams

import document.IDocObjectFactory
import document.IDocument
import document.ITable
import graph.DescriptionNode
import graph.INode
import graph.Root
import graph.UseCaseNode
import net.sourceforge.plantuml.abel.Entity
import net.sourceforge.plantuml.abel.EntityFactory
import net.sourceforge.plantuml.abel.LeafType
import net.sourceforge.plantuml.plasma.Quark
import java.util.stream.Stream

class UseCaseDiagram(private val factory: EntityFactory) : IDiagram {

    private val root = Root()

    override fun write(factory: IDocObjectFactory, doc: IDocument) {
        useCaseDiagram()
        val text = factory.createText("Таблица *.* – Реестр вариантов использования ")
        doc.addText(text)
        doc.addTable(headerTable(factory))
        root.descriptionNodes.forEach { descriptionNode ->
            val nodes = descriptionNode.getFlatNodes()
            doc.addTable(itemTable(factory, descriptionNode.entity, nodes))
        }
    }

    private fun itemTable(factory: IDocObjectFactory, entity: Entity, nodes: List<UseCaseNode>): ITable {
        val table = factory.createTable(nodes.count(), 4, 2500)

        nodes.forEachIndexed { index, useCaseNode ->
            table.setText(factory, index, 0, "${entity.quark}${index + 1}")
            table.setText(factory, index, 1, entity.display.toString().trim('[', ']'))
            table.setText(factory, index, 2, useCaseNode.entity.display.toString().trim('[', ']'))
            table.setText(factory, index, 3, "")
        }
        return table
    }

    private fun headerTable(factory: IDocObjectFactory): ITable {
        val table = factory.createTable(1, 4, 2500)

        table.setText(factory, 0, 0, "Код")
        table.setText(factory, 0, 1, "Основной актор")
        table.setText(factory, 0, 2, "Наименование")
        table.setText(factory, 0, 3, "Формулировка")
        return table
    }

    private fun useCaseDiagram() {
        val map = mutableMapOf<String, INode>()
        factory.root().data.leafs().stream().filter { it.leafType == LeafType.DESCRIPTION }.forEach {
            val node = DescriptionNode(it)
            root.descriptionNodes.add(node)
            map[it.uid] = node
        }

        factory.root().data.leafs().stream().filter { it.leafType == LeafType.USECASE }.forEach {
            val node = UseCaseNode(it)
            map[it.uid] = node
        }

        factory.links.stream().forEach { link -> map[link.entity1.uid]?.addNode(map[link.entity2.uid]!!) }
    }

    private fun ITable.setText(factory: IDocObjectFactory, row: Int, col: Int, text: String, fontSize: Int = 24) {
        val textObj = factory.createText(text, fontSize)
        setText(row, col, textObj)
    }
}