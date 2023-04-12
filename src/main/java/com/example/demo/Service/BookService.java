package com.example.demo.Service;

import com.example.demo.Model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


@Service
public class BookService  {


    private static final Map<Integer, Book> BOOK_REPOSITORY_MAP = new HashMap<>();  // Хранилище книг

    // Переменная для генерации ID клиента
    private static final AtomicInteger BOOK_ID_HOLDER = new AtomicInteger();


    public void create(Book book) {
        final int bookId = BOOK_ID_HOLDER.incrementAndGet();
        book.setID(bookId);
        BOOK_REPOSITORY_MAP.put(bookId, book);
    }


    public List<Book> readAll() {
        return new ArrayList<>(BOOK_REPOSITORY_MAP.values());
    }


    public Book read(int id) {
        return BOOK_REPOSITORY_MAP.get(id);
    }


    public boolean update(Book book, int id) {
        if (BOOK_REPOSITORY_MAP.containsKey(id)) {
            book.setID(id);
            BOOK_REPOSITORY_MAP.put(id, book);
            return true;
        }

        return false;
    }


    public boolean delete(int id) {
        return BOOK_REPOSITORY_MAP.remove(id) != null;
    }
}




