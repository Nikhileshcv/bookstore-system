package com.bookstore.Bookshop.repository;

import com.bookstore.Bookshop.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface BookRepository extends JpaRepository <Book,Long>{
    @Query(value = "insert into book(author,cost,title,userId) values(:author,:cost,:title,:userId)",nativeQuery = true)
    @Transactional
    @Modifying
    public void saveBook(String author,String cost,String title,Long userId);

    //add sql table for email, update table with datetime and email if user require it
    //in email microservice reterive the details and schedule date time

//    @Query(value = "insert into email(userEmail,dateTime) values(:userEmail,:dateTime)",nativeQuery = true)
//    @Transactional
//    @Modifying
//    public void saveUserInfo(String userEmail,String dateTime);

    @Query(value="select * from book where userId=:userId",nativeQuery = true)
    List<Book> getBooks(Integer userId);

}
