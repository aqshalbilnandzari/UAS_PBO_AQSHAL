package com.example.Parking.Controller;

import com.example.Parking.Model.User;
import com.example.Parking.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestParam String name, @RequestParam String phoneNumber, @RequestParam String password){
        userService.register(name, phoneNumber, password);
        return new ResponseEntity<>("User Added Successfully", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updatePassword(@RequestParam Integer userId, @RequestParam String password){
        User updatedUser = userService.updatePassword(userId, password);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam Integer userId){
        return userService.deleteUser(userId);
    }

}
