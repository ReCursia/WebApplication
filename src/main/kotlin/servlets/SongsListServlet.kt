package servlets

import models.Song
import repository.SongsRepository
import repository.SongsRepositoryImpl
import java.util.*
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "SongsList", value = ["/songs"])
class SongsListServlet : HttpServlet() {

    private val repository: SongsRepository = SongsRepositoryImpl()

    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        //Uncomment what you want to use
        //jspFlow(req, res)
        defaultFlow(req, res)
    }

    private fun jspFlow(req: HttpServletRequest, res: HttpServletResponse) {
        res.sendRedirect(JSP_FILE_PATH)
    }

    private fun defaultFlow(req: HttpServletRequest, res: HttpServletResponse) {
        req.characterEncoding = "utf-8"
        val lang = req.getParameter("lang") ?: "en" //default lang is en
        val artist = req.getParameter("artist")
        res.contentType = "text/html;charset=UTF-8"
        val table = buildString {
            val resources = ResourceBundle.getBundle("Song", Locale(lang))
            append("<html>")
            append("<head>")
            append("<link rel=\"stylesheet\" type=\"text/css\" href=\"styles/style.css\">")
            append("<link href=\"https://fonts.googleapis.com/css?family=Roboto&display=swap\" rel=\"stylesheet\">")
            append("<link rel=\"stylesheet\" href=\"https://unpkg.com/purecss@1.0.1/build/pure-min.css\" integrity=\"sha384-oAOxQR6DkCoMliIh8yFnu25d7Eq/PHS21PClpwjOTeU2jRSq11vu66rf90/cZr47\" crossorigin=\"anonymous\">")
            append("<title>${resources.getString("header")}</title>")
            append("</head>")
            append("<body>")
            append("<h1>${resources.getString("list")} ${artist ?: ""}</h1>")
            append("<table class=\"pure-table\">")
            append("<tr>")
            append("<th><b>${resources.getString("title")}</b></th>")
            append("<th><b>${resources.getString("album")}</b></th>")
            append("<th><b>${resources.getString("year")}</b></th>")
            append("<th><b>${resources.getString("artist")}</b></th>")
            append("<th><b>${resources.getString("genre")}</b></th>")
            append("<th><b>${resources.getString("play")}</b></th>")
            append("</tr>")
            repository.getAllSongs()
                .filter { req.getParameter("artist").isNullOrBlank() || req.getParameter("artist") == it.artist }
                .forEach { append(it.toHtml()) }
            append("</table>")
            append("</body>")
            append("</html>")
        }
        res.writer.write(table)
    }

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse?) {
        req.characterEncoding = "utf-8"
        val title = req.getParameter("title")
        val album = req.getParameter("album")
        val year = req.getParameter("year").toInt()
        val artist = req.getParameter("artist")
        val genre = req.getParameter("genre")
        repository.addSong(Song(title, album, year, artist, genre))
    }

    companion object {
        private const val JSP_FILE_PATH = "SongList.jsp"
    }
}