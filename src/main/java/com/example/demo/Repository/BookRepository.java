package com.example.demo.Repository;

import com.example.demo.Model.Book;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookRepository {

    private static final Map<Integer, Book> BOOK_REPOSITORY_MAP = new HashMap<>();  // Хранилище книг

    public void create(Book book) {
        BOOK_REPOSITORY_MAP.put((int) book.getID(), book);
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