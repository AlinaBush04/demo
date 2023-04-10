package com.example.demo.Service;

import com.example.demo.Model.Book;

public interface IBookService {

    void saveBook(Book book);

    void deleteBook(long id);

    Object getBookById(long id);

    Object listBooks();


}
