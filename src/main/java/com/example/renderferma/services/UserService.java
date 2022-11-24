package com.example.renderferma.services;

import com.example.renderferma.models.User;
import com.example.renderferma.repositories.UserRepository;
import com.example.renderferma.util.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void save(User user){
        userRepository.save(user);
    }

    public User findByNameAndPassword(String name, String password){
        return userRepository.findByNameAndPassword(name, password).orElseThrow(UserNotFoundException::new);
    }

    public User findById(int id){
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
}
