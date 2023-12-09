package com.bookstore.User.Repository;

import com.bookstore.User.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    @Query(value="select userId from user where email=:email",nativeQuery = true)
    Long findUserId(String email);

}
