package com.example.kurs.Controller;

import com.example.kurs.Dao.UserDao;
import com.example.kurs.entyty.Users;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/users")
    public List<Users> getAllUsers(){
        return userDao.findAll();
    }

}

