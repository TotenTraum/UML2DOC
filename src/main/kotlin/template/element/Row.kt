package template.element

class Row: TemplateElement() {
    data class Item(val header: Text, val item: Text)

    val items: MutableList<Item> = mutableListOf()
}