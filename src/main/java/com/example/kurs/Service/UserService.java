package com.example.kurs.Service;

import com.example.kurs.Dao.UserDao;
import com.example.kurs.entyty.Users;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Users createUser(Users user) {
        return userDao.save(user); // Create
    }

    public Optional<Users> getUserById(Long id) {
        return userDao.findById(id); // Read
    }

    public List<Users> getAllUsers() {
        return userDao.findAll(); // Read all
    }

    public Users updateUser(Long id, Users updatedUser) {
        Optional<Users> optionalUser = userDao.findById(id);
        if (optionalUser.isPresent()) {
            Users existingUser = optionalUser.get();
            existingUser.setUserName(updatedUser.getUserName());
            existingUser.setUserEmail(updatedUser.getUserEmail());
            return userDao.save(existingUser); // Update
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public void deleteUser(Long id) {
        userDao.deleteById(id); // Delete
    }
}
