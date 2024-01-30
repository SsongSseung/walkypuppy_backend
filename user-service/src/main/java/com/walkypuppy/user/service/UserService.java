package com.walkypuppy.user.service;

import com.walkypuppy.user.dto.UserDTO;
import com.walkypuppy.user.model.entity.User;
import com.walkypuppy.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }
}
