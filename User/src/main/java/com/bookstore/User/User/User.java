package com.bookstore.User.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
        @Id
        @GeneratedValue
        private Long Id;
        private String email;
        private String password;
        private Long userId;
        private String error;

        public User(String email, String password) {
            this.email = email;
            this.password = password;
        }

    public User() {

    }

    // Getters and setters
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getError()
        {
            return error;
        }


    public void setError(String error) {
        this.error=error;
    }
}
