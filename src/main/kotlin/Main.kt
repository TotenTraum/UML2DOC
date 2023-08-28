import document.IDocObjectFactory
import document.docx.DocxObjectFactory
import net.sourceforge.plantuml.SourceStringReader
import java.io.File
import java.io.FileNotFoundException


fun main(args: Array<String>) {
    val file = File("subsystem_chat.puml")

    if (!file.exists()) throw FileNotFoundException()

    val source = file.readText()
    val reader = SourceStringReader(source)

    val factory: IDocObjectFactory = DocxObjectFactory()
    val doc = factory.createDocument()

    reader.blocks.forEach {
//        val diagramFactory = DiagramFactory()
//        val diagram = diagramFactory.create(it.diagram)
//        diagram.write(factory, doc)
    }
    doc.save("document.docx")
}