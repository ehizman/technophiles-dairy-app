package com.technophiles.diaryapp.services;

import com.technophiles.diaryapp.controllers.requests.CreateAccountRequest;
import com.technophiles.diaryapp.dtos.UpdateUserDTO;
import com.technophiles.diaryapp.dtos.UserDTO;
import com.technophiles.diaryapp.models.Diary;
import com.technophiles.diaryapp.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDTO createAccount(CreateAccountRequest accountRequestDTO);

    UserDTO findUser(String id);

    UserDTO findUserByEmail(String email);

    String updateUser(String id, UpdateUserDTO updateUserDTO);

    Diary addNewDiary(String userId, Diary diary);

    User findUserByIdInternal(String userId);

    void deleteByEmail(String s);
}
