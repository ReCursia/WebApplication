package servlets

import models.Book
import java.io.File
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "BooksList", value = ["/books"])
class BooksListServlet : HttpServlet() {
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        fun getTable() = buildString {
            append("<html>")
            append("<head><title>Список книг</title></head>")
            append("<body>")
            append("<h1>Список книг ${req.getParameter("name") ?: ""}</h1>")
            append("<table border='1'>\n")
            append("<tr><td><b>Автор книги</b></td><td><b>Название книги </b></td><td><b>Прочитал</b></td><td><b>Текущий читатель</b></td></tr>")
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