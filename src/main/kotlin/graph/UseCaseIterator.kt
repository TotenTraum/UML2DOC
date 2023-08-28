package graph

class UseCaseIterator(private val node: UseCaseNode): Iterator<UseCaseNode> {
    var isNotEnd = true
    var index: Int = 0

    override fun hasNext(): Boolean = isNotEnd

    override fun next(): UseCaseNode {
        return if(index < node.nodes.count()){
            node.nodes[index++]
        } else if(index == node.nodes.count()){
            isNotEnd = false
            node
        } else{
            TODO("специальное исключение")
        }
    }
}