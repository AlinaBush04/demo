package com.example.demo.Service;

import com.example.demo.Model.Book;
import com.example.demo.Repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Service
public class BookService  {


    private static final BookRepository BOOK_REPOSITORY = new BookRepository();  // Хранилище книг


    private static final AtomicInteger BOOK_ID_HOLDER = new AtomicInteger(); // Переменная для генерацииID книжки


    public void create(Book book) {
        final int bookId = BOOK_ID_HOLDER.incrementAndGet();
        book.setID(bookId);
        BOOK_REPOSITORY.create(book);
    }


    public List<Book> readAll() {
        return BOOK_REPOSITORY.readAll();
    }


    public Book read(int id) {
        return BOOK_REPOSITORY.read(id);
    }


    public boolean update(Book book, int id) {
        return BOOK_REPOSITORY.update(book, id);
    }


    public boolean delete(int id) {
        return BOOK_REPOSITORY.delete(id);
    }
}




