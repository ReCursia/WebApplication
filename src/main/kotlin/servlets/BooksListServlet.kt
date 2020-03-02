package servlets

import models.Book
import java.io.File
import java.util.*
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "BooksList", value = ["/books"])
class BooksListServlet : HttpServlet() {
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        req.characterEncoding = "utf-8"
        val lang = req.getParameter("lang") ?: "en" //default lang is en
        val name = req.getParameter("name")
        fun getTable() = buildString {
            val resources = ResourceBundle.getBundle("Book", Locale(lang))
            append("<html>")
            append("<head><title>${resources.getString("title")}</title></head>")
            append("<body>")
            append("<h1>${resources.getString("list")} ${name ?: ""}</h1>")
            append("<table border='1'>")
            append("<tr>")
            append("<td><b>${resources.getString("name")}</b></td>")
            append("<td><b>${resources.getString("read")}</b></td>")
            append("<td><b>${resources.getString("author")}</b></td>")
            append("</tr>")
            getBooksFromFile().filter { name == it.curReader }.forEach { append(it.toHtml()) }
            append("</table>")
            append("</body>")
            append("</html>")
        }
        res.contentType = "text/html;charset=UTF-8"
        res.writer.write(getTable())
    }

    private fun getBooksFromFile() = mutableListOf<Book>().apply {
        //TODO to JSON using GSON
        File(FILE_PATH).forEachLine {
            val split = it.split(' ')
            val name = split[0]
            val author = split[1]
            val isRead = split[2].toBoolean()
            val curReader = if (split.size > 3) split[3] else null
            add(Book(name, author, isRead, curReader))
        }
    }

    companion object {
        private const val FILE_PATH = "D:\\Projects\\WebApplication\\books.txt"
    }
}