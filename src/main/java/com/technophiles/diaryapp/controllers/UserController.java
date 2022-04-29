package com.technophiles.diaryapp.controllers;

import com.technophiles.diaryapp.controllers.requests.CreateAccountRequest;
import com.technophiles.diaryapp.controllers.response.APIResponse;
import com.technophiles.diaryapp.exceptions.DiaryAppApplicationException;
import com.technophiles.diaryapp.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewUserAccount(@RequestBody CreateAccountRequest accountRequest){
        try{
            APIResponse response = APIResponse.builder()
                    .payLoad(userService.createAccount(accountRequest))
                    .message("account created successful")
                    .isSuccessful(true)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch(DiaryAppApplicationException exception){
            APIResponse response = APIResponse.builder()
                    .message(exception.getMessage())
                    .isSuccessful(false)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
