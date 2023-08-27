package document.docx

import document.IText
import org.docx4j.jaxb.Context
import org.docx4j.wml.*
import java.math.BigInteger

class DocxText(text: String, fontSize: Int = 12) : IText {
    private val _paragraph: P = Context.getWmlObjectFactory().createP()
    private val _run: R = Context.getWmlObjectFactory().createR()
    private val _text: Text = Context.getWmlObjectFactory().createText()
    private val _size: HpsMeasure = Context.getWmlObjectFactory().createHpsMeasure()
    private val _rPr: RPr = Context.getWmlObjectFactory().createRPr()

    init {
        _paragraph.content.add(_run)
        _run.content.add(_text)
        _rPr.sz = _size
        _run.rPr = _rPr
        _size.`val` = BigInteger.valueOf(fontSize.toLong())
        _text.value = text
    }

    val data: P
        get() = _paragraph

    override var value: String
        get() = _text.value
        set(value) {
            _text.value = value
        }

    override var size: Int
        get() = _size.`val`.toInt()
        set(value) {
            _size.`val` = BigInteger.valueOf(value.toLong())
        }
}