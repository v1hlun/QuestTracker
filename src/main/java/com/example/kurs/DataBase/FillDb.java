package com.example.kurs.DataBase;

import com.example.kurs.Dao.UserDao;
import com.example.kurs.entyty.Users;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class FillDb {


    private final UserDao userDao;

    public FillDb(UserDao userDao){this.userDao = userDao;}

    @PostConstruct
    public void seedData(){
        userDao.deleteAll();

        Users user1 = new Users();
        user1.setUserName("John Doe");
        userDao.save(user1);

        Users user2 = new Users();
        user2.setUserName("Jane Smith");
        userDao.save(user2);

        Users user3 = new Users();
        user3.setUserName("Phillip Black");
        userDao.save(user3);

        System.out.println("database has been seeded");

    }
}
