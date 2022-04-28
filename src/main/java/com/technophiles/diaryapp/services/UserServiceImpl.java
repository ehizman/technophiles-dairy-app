package com.technophiles.diaryapp.services;

import com.technophiles.diaryapp.DiaryAppApplication;
import com.technophiles.diaryapp.controllers.requests.CreateAccountRequest;
import com.technophiles.diaryapp.exceptions.DiaryAppApplicationException;
import com.technophiles.diaryapp.models.User;
import com.technophiles.diaryapp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String createAccount(CreateAccountRequest accountRequestDTO) {
        Optional<User> optionalUser = userRepository.findUserByEmail(accountRequestDTO.getEmail());
        if (optionalUser.isPresent()){
            throw new DiaryAppApplicationException("user is already present");
        }
        User user = new User();
        user.setEmail(accountRequestDTO.getEmail());
        user.setPassword(accountRequestDTO.getPassword());
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }
}
