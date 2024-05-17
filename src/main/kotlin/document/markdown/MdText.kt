package document.markdown

import document.IText

class MdText(private var text: String) : IText, IMdElement {

    /** Значение текста */
    override var value: String
        get() = text
        set(value) {
            text = value
        }

    /** Размер шрифта */
    override var size: Int
        get() = 0
        set(value) {

        }
}