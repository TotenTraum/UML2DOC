package diagrams

import net.sourceforge.plantuml.classdiagram.ClassDiagram
import net.sourceforge.plantuml.core.Diagram

class DiagramFactory {
    fun create(diagram: Diagram): IDiagram{
        return when (diagram){
            is ClassDiagram -> ClassDiagram(diagram.entityFactory.root().children.stream())
            else -> {
                TODO("Not now")
            }
        }
    }
}