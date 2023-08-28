package diagrams

import document.IDocObjectFactory
import document.IDocument
import graph.DescriptionNode
import graph.INode
import graph.Root
import graph.UseCaseNode
import net.sourceforge.plantuml.abel.Entity
import net.sourceforge.plantuml.abel.EntityFactory
import net.sourceforge.plantuml.abel.LeafType
import net.sourceforge.plantuml.plasma.Quark
import java.util.stream.Stream

class UseCaseDiagram(val factory: EntityFactory) : IDiagram {

    val root = Root()

    override fun write(factory: IDocObjectFactory, doc: IDocument) {
        TODO("Not yet implemented")
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

//        root.descriptionNodes.forEach { descriptionNode ->
//            println(descriptionNode.entity.toString())
//            descriptionNode.getFlatNodes().forEach(::println)
//        }
    }
}