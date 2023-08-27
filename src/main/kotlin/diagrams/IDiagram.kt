package diagrams

import document.IDocObjectFactory
import document.IDocument

interface IDiagram {
    fun write(factory: IDocObjectFactory, doc: IDocument)
}