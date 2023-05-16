package com.example.demo.Service;

import com.example.demo.Model.Book;

import java.util.List;

public interface IBookService {

    void create(Book book);
    List<Book> readAll();
    Book read(int id);
    boolean update(Book book, int id);
    boolean delete(int id);

}
