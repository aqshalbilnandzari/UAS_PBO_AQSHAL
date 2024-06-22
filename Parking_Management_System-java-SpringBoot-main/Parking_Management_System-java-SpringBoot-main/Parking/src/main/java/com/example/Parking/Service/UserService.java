package com.example.Parking.Service;

import com.example.Parking.Model.User;
import com.example.Parking.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void register(String name, String phoneNumber, String password) {
        User user = new User();
        user.setName(name);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);

        userRepository.save(user);
    }

    public User updatePassword(Integer userId, String password) {
        User user = userRepository.findById(userId).get();
        user.setPassword(password);
        userRepository.save(user);

        return user;
    }

    public String deleteUser(Integer userId) {
        userRepository.deleteById(userId);
        return "User Deleted Successfully";
    }
}
