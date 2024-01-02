package com.ecom.registrationapi.Service;

import com.ecom.registrationapi.Model.User;
import com.ecom.registrationapi.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public Optional<User> getUser(Integer userId){
        return userDao.findById(userId);
    }

    public ResponseEntity<String> getUser(String email, String password){
        if (userDao.findByEmail(email).isEmpty()){
            return new ResponseEntity<String>("The Email Id cannot be found", HttpStatus.NOT_FOUND);
        }
        else {
            User u = userDao.findByEmail(email).get();
            if (u.getPassword().equals(password)){
                return new ResponseEntity<>("Logged in", HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("Wrong Password", HttpStatus.BAD_REQUEST);
            }
        }

    }
    public ResponseEntity<?> registerUser(User newUser){
        if (userDao.findByEmail(newUser.getEmail()).isEmpty()) {
            userDao.saveAndFlush(newUser);
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("This Email already exists", HttpStatus.BAD_REQUEST);
       }
    }

    public ResponseEntity<User> editUser(String firstName, String lastName, Integer phoneNumber, String email) {
        User u = userDao.findByEmail(email).get();
        u.setFirstName(firstName);
        u.setLastName(lastName);
        u.setPhoneNumber(phoneNumber);
        userDao.save(u);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    public ResponseEntity<String> changePasswordUser(String email, String oldPassword, String newPassword) {
        if (userDao.findByEmail(email).isEmpty()){
            return new ResponseEntity<String>("The Email Id cannot be found", HttpStatus.NOT_FOUND);
        }
        else {
            User u = userDao.findByEmail(email).get();
            if (u.getPassword().equals(oldPassword)) {
                u.setPassword(newPassword);
                userDao.save(u);
                return new ResponseEntity<>("Password updated", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Wrong Password", HttpStatus.BAD_REQUEST);
            }
        }
    }
}
