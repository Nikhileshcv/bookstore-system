package com.bookstore.Bookshop.exception;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(Long id){
        super("Could not found the user by id "+id);
    }
}
