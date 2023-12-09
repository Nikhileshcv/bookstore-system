package com.bookstore.Bookshop.controller;

import com.bookstore.Bookshop.exception.BookNotFoundException;
import com.bookstore.Bookshop.model.Book;
import com.bookstore.Bookshop.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://ccfrontendbucket.s3-website.us-east-2.amazonaws.com/")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    //create book
    @PostMapping("/book")
    public void newBook(@RequestBody Book newBook){
        newBook.setUserId(newBook.getUserId());
        bookRepository.saveBook(newBook.getAuthor(), newBook.getCost(), newBook.getTitle(), newBook.getUserId());
    }

    //read all books
    @GetMapping("/books/{userId}")
    List<Book> getAllUsers(@PathVariable("userId") Integer userId){
        return bookRepository.getBooks(userId);
    }

    //read book by id
    @GetMapping("/book/{id}")
    Book getBookById(@PathVariable Long id){
        return bookRepository.findById(id)
                .orElseThrow(()-> new BookNotFoundException(id));
    }

    @PutMapping("/book/{id}")
    Book updateBook(@RequestBody Book newBook,@PathVariable Long id){
        return bookRepository.findById(id)
                .map(book ->{
                    book.setTitle(newBook.getTitle());
                    book.setAuthor(newBook.getAuthor());
                    book.setCost(newBook.getCost());

                    return bookRepository.save(book);
                }).orElseThrow(()-> new BookNotFoundException(id));
    }

    @DeleteMapping("/book/{id}")
    String deleteBook(@PathVariable Long id){
        if (!bookRepository.existsById(id)){
            throw new BookNotFoundException(id);
        }
        bookRepository.deleteById(id);
        return "Book "+id+" has been deleted.";
    }
}
