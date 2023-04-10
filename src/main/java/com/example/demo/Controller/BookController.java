package com.example.demo.Controller;

import com.example.demo.Model.Book;
import com.example.demo.Service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping( "/createBook")             /// создание книжки
    @Async
    public ResponseEntity<?> create(@RequestBody Book book) {
        bookService.create(book);
        return new ResponseEntity<>(HttpStatus.CREATED); //// статус, что все окей

    }

    @GetMapping( "/books/{id}")           /// получение книжки по ее айди
    public ResponseEntity<Book> read(@PathVariable(name = "id") int id) {
        final Book book = bookService.read(id);

        return book != null
                ? new ResponseEntity<>(book, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping( "/getBooks")  /// получение всех книг
    @Async
    public ResponseEntity<List<Book>> read() {
        final List<Book> books = bookService.readAll();

        return books != null && !books.isEmpty()
                ? new ResponseEntity<>(books, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/books/{id}")        /// изменение
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Book book) {
        final boolean updated = bookService.update(book, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/clients/{id}")          // удаление
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = bookService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }



}
