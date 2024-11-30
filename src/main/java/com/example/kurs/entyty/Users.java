package com.example.kurs.entyty;

import jakarta.persistence.*;

@Entity
@Table (name = "users")
public class Users {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String userName;
    private String email;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String email) {
        this.email = email;
    }
     public String getUserEmail(){
        return email;
     }
}
