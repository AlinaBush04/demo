package com.example.demo.Service;

import com.example.demo.Model.Book;
import org.springframework.stereotype.Service;



@Service
public class BookService implements IBookService {

    public Long createBook(Book book) {
    }

    public Book getBook(Long id) {
    }

    @Override
    public void saveBook(Book book) {

    }

    @Override
    public void deleteBook(long id) {

    }

    @Override
    public Object getBookById(long id) {
        return null;
    }

    @Override
    public Object listBooks() {
        return null;
    }

    public void create(Book book) {
    }
}

