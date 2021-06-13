package com.danchikov.service;

import com.danchikov.entity.User;
import com.danchikov.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public boolean saveUser(User user){
        User userFromDB=userRepository.findByName(user.getName());
        if (userFromDB != null){
            return false;
        }else{
            userRepository.save(user);
            return true;
        }
    }
}
