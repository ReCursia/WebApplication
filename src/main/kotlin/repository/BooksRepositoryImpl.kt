package repository

import com.google.gson.Gson
import models.Book
import java.io.FileReader
import java.io.FileWriter

class BooksRepositoryImpl : BooksRepository {
    override fun getAllBooks(): ArrayList<Book> {
        val reader = FileReader(FILE_PATH)
        val books = Gson()
            .fromJson(reader, Array<Book>::class.java)
            .toCollection(ArrayList())
        reader.close()
        return books
    }

    override fun addBook(book: Book) {
        val books = getAllBooks()
        books.add(book)
        val writer = FileWriter(FILE_PATH)
        Gson().toJson(books, writer)
        writer.close()
    }

    companion object {
        private const val FILE_PATH = "D:\\Projects\\WebApplication\\books.json"
    }
}