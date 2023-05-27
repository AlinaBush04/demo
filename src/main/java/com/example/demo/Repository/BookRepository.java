package com.example.demo.Repository;

import com.example.demo.Model.Book;
import com.example.demo.Model.BookMap;
import org.springframework.stereotype.Repository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookRepository implements IBookRepository {

    private static final Map<Integer, Book> BOOK_REPOSITORY_MAP = new HashMap<>();// Хранилище книг

    private void MapToXml() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(BookMap.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        BookMap bookMap = new BookMap();
        bookMap.setBookMap(BOOK_REPOSITORY_MAP);
        jaxbMarshaller.marshal(bookMap, new File("src/main/java/com/example/demo/Repository/BooksBank.xml"));
    }

    private void XmlToMap() throws JAXBException
    {
        JAXBContext jaxbContext = JAXBContext.newInstance(BookMap.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        BookMap bookMap = (BookMap) jaxbUnmarshaller.unmarshal( new File("src/main/java/com/example/demo/Repository/BooksBank.xml") );

        BOOK_REPOSITORY_MAP.clear();
        BOOK_REPOSITORY_MAP.putAll(bookMap.getBookMap());
    }
    @Override
    public void create(Book book) {
        BOOK_REPOSITORY_MAP.put((int) book.getID(), book);
        try {
            this.MapToXml();
        } catch (JAXBException e){
            System.out.println("Error:\n" + e);
        }
    }
    @Override
    public Book read(int id) {
        if (BOOK_REPOSITORY_MAP.isEmpty()){
            try{
                this.XmlToMap();
            } catch (JAXBException e){
                System.out.println("Error:\n" + e);
            }
        }
        return BOOK_REPOSITORY_MAP.get(id);
    }
    @Override
    public List<Book> readAll() {
        if (BOOK_REPOSITORY_MAP.isEmpty()){
            try{
                this.XmlToMap();
            } catch (JAXBException e){
                System.out.println("Error:\n" + e);
            }
        }
        return new ArrayList<>(BOOK_REPOSITORY_MAP.values());
    }
    @Override
    public boolean update(Book book, int id) {
        if (BOOK_REPOSITORY_MAP.containsKey(id)) {
            book.setID(id);
            BOOK_REPOSITORY_MAP.put(id, book);
            try {
                this.MapToXml();
            } catch (JAXBException e){
                System.out.println("Error:\n" + e);
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        boolean result = BOOK_REPOSITORY_MAP.remove(id) != null;
        try {
            this.MapToXml();
        } catch (JAXBException e){
            System.out.println("Error:\n" + e);
            return false;
        }
        return result;
    }
}