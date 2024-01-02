package com.ecom.registrationapi.UserController;

import com.ecom.registrationapi.Model.User;
import com.ecom.registrationapi.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> userRegistration(@RequestBody User newUser){
        return userService.registerUser(newUser);
    }

    @GetMapping("/getUser/{userId}")
    public User getUser(@PathVariable Integer userId){
        return userService.getUser(userId).get();
    }

    @GetMapping("/login")
    public ResponseEntity<String> getUser(@RequestHeader String email, @RequestHeader String password){
        return userService.getUser(email, password);
    }

    @PostMapping("/editProfile")
    public ResponseEntity<User> editUser(@RequestHeader String firstName, @RequestHeader String lastName, @RequestHeader Integer phoneNumber, @RequestHeader String email){
        return userService.editUser(firstName, lastName, phoneNumber, email);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestHeader String email, @RequestHeader String oldPassword,@RequestHeader String newPassword){
        return userService.changePasswordUser(email, oldPassword, newPassword);
    }
}
