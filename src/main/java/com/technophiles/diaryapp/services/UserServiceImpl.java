package com.technophiles.diaryapp.services;

import com.technophiles.diaryapp.controllers.requests.CreateAccountRequest;
import com.technophiles.diaryapp.dtos.UpdateUserDTO;
import com.technophiles.diaryapp.dtos.UserDTO;
import com.technophiles.diaryapp.exceptions.DiaryAppApplicationException;
import com.technophiles.diaryapp.mappers.UserMapper;
import com.technophiles.diaryapp.mappers.UserMapperImpl;
import com.technophiles.diaryapp.models.Diary;
import com.technophiles.diaryapp.models.User;
import com.technophiles.diaryapp.repositories.DiaryRepository;
import com.technophiles.diaryapp.repositories.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
@NoArgsConstructor
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    private UserMapper userMapper = new UserMapperImpl();
    private DiaryRepository diaryRepository;

    public UserServiceImpl(UserRepository userRepository, DiaryRepository diaryRepository) {
        this.userRepository = userRepository;
        this.diaryRepository = diaryRepository;
    }

    @Override
    public UserDTO createAccount(CreateAccountRequest accountRequestDTO) {
        Optional<User> optionalUser = userRepository.findUserByEmail(accountRequestDTO.getEmail());
        if (optionalUser.isPresent()){
            throw new DiaryAppApplicationException("user is already present");
        }
        User user = new User();
        user.setEmail(accountRequestDTO.getEmail());
        user.setPassword(accountRequestDTO.getPassword());
        user.setDiaries(new HashSet<>());
        User savedUser = userRepository.save(user);
        return userMapper.userToUserDTO(savedUser);
    }

    @Override
    public UserDTO findUser(String id) {
        User user = userRepository.findById(id).orElseThrow(()-> new DiaryAppApplicationException("user with id does not exist"));
        return userMapper.userToUserDTO(user);
    }



    @Override
    public String updateUser(String id, UpdateUserDTO updateUserDTO) {
        User user = userRepository.findById(id).orElseThrow(()-> new DiaryAppApplicationException("user account does not exist"));

        boolean isUpdated = false;

        if (!(updateUserDTO.getEmail()== null || updateUserDTO.getEmail().trim().equals(""))){
            user.setEmail(updateUserDTO.getEmail());
            isUpdated = true;
        }

        if (!(updateUserDTO.getPassword()==null || updateUserDTO.getPassword().trim().equals(""))){
            user.setPassword(updateUserDTO.getPassword());
            isUpdated = true;
        }

        if (isUpdated){
            userRepository.save(user);
        }
        return "User details updated successfully";
    }

    @Override
    public Diary addNewDiary(String userId, Diary diary) {
        User user = userRepository.findById(userId).orElseThrow(()-> new DiaryAppApplicationException("user does not exist"));
        Diary savedDiary = diaryRepository.save(diary);
        user.getDiaries().add(savedDiary);
        userRepository.save(user);
        return savedDiary;
    }

    @Override
    public User findUserByIdInternal(String userId) {
        return userRepository.findById(userId).orElseThrow(()-> new DiaryAppApplicationException("user does not exist"));
    }

    @Override
    public void deleteByEmail(String email) {
        User user = userRepository.findUserByEmail(email).orElseThrow(()-> new DiaryAppApplicationException("user with not found"));
        userRepository.delete(user);
    }
}
