package com.technophiles.diaryapp.services;

import com.technophiles.diaryapp.controllers.requests.CreateAccountRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    String createAccount(CreateAccountRequest accountRequestDTO);
}
