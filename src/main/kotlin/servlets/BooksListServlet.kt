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
        val content = getTable()
        res.contentType = "text/html;charset=UTF-8"
        res.writer.write(content)
    }

    private fun getTable(): String {
        return buildString {
            append("<html>\n")
            append("<head><title>Список книг</title></head>\n")
            append("<body>\n")
            append("<h1>Список книг читателя</h1>\n")
            append("<table border='1'>\n")
            append("<tr><td><b>Автор книги</b></td><td><b>Название книги </b></td><td><b>Прочитал</b></td></tr>\n")
            getBooksFromFile().forEach { append(it.toHtml()) }
            append("</table>")
            append("</body>")
            append("</html>")
        }
    }

    private fun getBooksFromFile(): List<Book> {
        val books = mutableListOf<Book>()
        File(FILE_PATH).forEachLine {
            val splitted = it.split(' ')
            val name = splitted[0]
            val author = splitted[1]
            val isRead = splitted[2].toBoolean()
            books.add(Book(name, author, isRead))
        }
        return books
    }

    companion object {
        private const val FILE_PATH = "D:\\Projects\\WebApplication\\books.txt"
    }
}