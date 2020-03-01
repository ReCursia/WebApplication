package models

import common.Htmlable

data class Book(val name: String, val author: String, val isRead: Boolean = false, val curReader: String? = null) : Htmlable {
    //TODO возможно всю чушь с html в отдельный класс, который будет генерировать данные с Instance
    override fun toHtml() =
        "<tr><td><b>$author</b></td><td><b>$name</b></td><td><b>$isRead</b><td><b>$curReader</b></td></tr>"
}