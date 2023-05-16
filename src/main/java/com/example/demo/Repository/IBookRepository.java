package com.example.demo.Repository;


import com.example.demo.Model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IBookRepository {
    void create(Book book);
    Book read(int id);
    List<Book> readAll();
    boolean update(Book book, int id);
    boolean delete(int id);




}
