package graph

import net.sourceforge.plantuml.abel.Entity

class UseCaseNode(val entity: Entity): INode {
    val nodes: MutableList<UseCaseNode> = mutableListOf()

    override fun addNode(node: INode) {
        if (node is UseCaseNode && nodes.all { it.entity.uid != node.entity.uid })
            nodes.add(node)
    }

    fun iterator(): UseCaseIterator = UseCaseIterator(this)

    override fun toString(): String {
        return entity.toString()
    }
}