package servlets

import models.Book
import repository.BooksRepository
import repository.BooksRepositoryImpl
import java.util.*
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "BooksList", value = ["/books"])
class BooksListServlet : HttpServlet() {

    private val repository: BooksRepository = BooksRepositoryImpl()

    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        res.sendRedirect(JSP_FILE_PATH)
        /*
        req.characterEncoding = "utf-8"
        val lang = req.getParameter("lang") ?: "en" //default lang is en
        val name = req.getParameter("name")
        res.contentType = "text/html;charset=UTF-8"
        val table = buildString {
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
            repository.getAllBooks().forEach { append(it.toHtml()) }
            append("</table>")
            append("</body>")
            append("</html>")
        }
        res.writer.write(table)

         */
    }

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse?) {
        req.characterEncoding = "utf-8"
        val name = req.getParameter("name")
        val author = req.getParameter("author")
        val isRead = req.getParameter("read")?.toBoolean() ?: false
        val curReader = req.getParameter("reader")
        repository.addBook(Book(name, author, isRead, curReader))
    }

    companion object {
        private const val JSP_FILE_PATH = "BookList.jsp"
    }
}