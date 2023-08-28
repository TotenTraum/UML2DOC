package graph

import net.sourceforge.plantuml.abel.Entity

class DescriptionNode(val entity: Entity) : INode {
    val nodes: MutableList<UseCaseNode> = mutableListOf()

    override fun addNode(node: INode) {
        if (node is UseCaseNode && nodes.all { it.entity.uid != node.entity.uid })
            nodes.add(node)
    }

    fun getFlatNodes(): List<UseCaseNode> {
        val list: MutableList<UseCaseNode> = mutableListOf()
        nodes.forEach {
            val iter = it.iterator()
            while (iter.hasNext())
                list.add(iter.next())
        }
        return list
    }
}