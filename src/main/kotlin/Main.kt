import diagrams.DiagramFactory
import document.IDocObjectFactory
import document.docx.DocxObjectFactory
import document.markdown.MdObjectFactory
import net.sourceforge.plantuml.SourceStringReader
import java.io.File
import java.io.FileNotFoundException
import java.nio.file.Files
import kotlin.io.path.Path

fun main() {
    val dir = File("input")
    if (!Files.exists(Path("output"))) {
        Files.createDirectory(Path("output"))
    }
    dir.listFiles()?.forEach { file ->

        if (!file.exists()) throw FileNotFoundException()

        val source = file.readText()
        val reader = SourceStringReader(source)

        val factory: IDocObjectFactory = MdObjectFactory()
        val doc = factory.createDocument()

        reader.blocks.forEach {
            val diagramFactory = DiagramFactory()
            val diagram = diagramFactory.create(it.diagram)
            diagram.write(factory, doc)
        }
        doc.save("output/${file.absoluteFile.nameWithoutExtension}.md")
    }
}