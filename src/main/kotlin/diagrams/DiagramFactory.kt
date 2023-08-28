package diagrams

import graph.DescriptionNode
import net.sourceforge.plantuml.classdiagram.ClassDiagram
import net.sourceforge.plantuml.core.Diagram
import net.sourceforge.plantuml.descdiagram.DescriptionDiagram

class DiagramFactory {
    fun create(diagram: Diagram): IDiagram = when (diagram) {
        is ClassDiagram -> ClassDiagram(diagram.entityFactory.root().children.stream())
        is DescriptionDiagram -> UseCaseDiagram(diagram.entityFactory)
        else -> {
            TODO("Not now")
        }
    }
}