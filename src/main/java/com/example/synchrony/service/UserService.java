package com.example.synchrony.service;



import com.example.synchrony.model.User;
import com.example.synchrony.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // Add password encoding logic
        return userRepository.save(user);
    }
    // Add the save method here
    public User save(User user) {
        return userRepository.save(user); // Delegate the save operation to the repository
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

