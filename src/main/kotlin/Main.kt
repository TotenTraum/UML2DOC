import diagrams.DiagramFactory
import document.IDocObjectFactory
import document.docx.DocxObjectFactory
import net.sourceforge.plantuml.SourceStringReader
import java.io.File

fun main(args: Array<String>) {
    val file = File("Main.plantuml")

    if (!file.exists()) {
        file.createNewFile()
    }

    val source = file.readText()

    val r = SourceStringReader(source)
//    val cd = r.blocks[0].diagram as DescriptionDiagram

    val factory: IDocObjectFactory = DocxObjectFactory()
    val doc = factory.createDocument()

    val diagramFactory = DiagramFactory()
    val diagram = diagramFactory.create(r.blocks[0].diagram)
    diagram.write(factory, doc)
    doc.save("")
}