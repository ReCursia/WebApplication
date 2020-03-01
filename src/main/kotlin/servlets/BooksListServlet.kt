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
        fun getTable() = buildString {
            val titles = ResourceBundle.getBundle("Book", Locale(req.getParameter("lang")))
            append("<html>")
            append("<head><title>${titles.getString("title")}</title></head>")
            append("<body>")
            append("<h1>${titles.getString("list")} ${req.getParameter("name") ?: ""}</h1>")
            append("<table border='1'>")
            append("<tr>")
            append("<td><b>${titles.getString("name")}</b></td>")
            append("<td><b>${titles.getString("read")}</b></td>")
            append("<td><b>${titles.getString("author")}</b></td>")
            append("</tr>")
            getBooksFromFile().filter { req.getParameter("name") == it.curReader }.forEach { append(it.toHtml()) }
            append("</table>")
            append("</body>")
            append("</html>")
        }
        req.characterEncoding = "utf-8"
        res.contentType = "text/html;charset=UTF-8"
        res.writer.write(getTable())
    }

    private fun getBooksFromFile() = mutableListOf<Book>().apply {
        //TODO to JSON using GSON
        File(FILE_PATH).forEachLine {
            val splitted = it.split(' ')
            val name = splitted[0]
            val author = splitted[1]
            val isRead = splitted[2].toBoolean()
            val curReader = if (splitted.size > 3) splitted[3] else null
            add(Book(name, author, isRead, curReader))
        }
    }

    companion object {
        private const val FILE_PATH = "D:\\Projects\\WebApplication\\books.txt"
    }
}