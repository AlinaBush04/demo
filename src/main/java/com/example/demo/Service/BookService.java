package com.example.demo.Service;

import com.example.demo.Model.Book;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Service
public class BookService implements IBookService {

    private static IBookRepository BOOK_REPOSITORY;  // Хранилище книг

    private static AtomicInteger BOOK_ID_HOLDER; // Переменная для генерацииID книжки


    @Autowired
    public BookService(BookRepository bookRepository) {
        BOOK_REPOSITORY = bookRepository;
        List<Book> bookList = BOOK_REPOSITORY.readAll();
        long maxId = 0;
        for (Book book : bookList) {
            if (book.getID()>maxId) {
                maxId = book.getID();
            }
        }
        BOOK_ID_HOLDER = new AtomicInteger((int) maxId);
    }

    @Override
    public void create(Book book) {
        final int bookId = BOOK_ID_HOLDER.incrementAndGet();
        book.setID(bookId);
        BOOK_REPOSITORY.create(book);
    }

    @Override
    public List<Book> readAll() {
        return BOOK_REPOSITORY.readAll();
    }

    @Override
    public Book read(int id) {
        return BOOK_REPOSITORY.read(id);
    }

    @Override
    public boolean update(Book book, int id) {
        return BOOK_REPOSITORY.update(book, id);
    }

    @Override
    public boolean delete(int id) {
        return BOOK_REPOSITORY.delete(id);
    }
}




