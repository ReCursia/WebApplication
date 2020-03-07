package repository

import models.Book

interface BooksRepository {
    fun getAllBooks() : List<Book>
    fun addBook(book: Book)
}