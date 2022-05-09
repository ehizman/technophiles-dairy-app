package com.technophiles.diaryapp.services;

import com.technophiles.diaryapp.controllers.requests.CreateAccountRequest;
import com.technophiles.diaryapp.dtos.UpdateUserDTO;
import com.technophiles.diaryapp.dtos.UserDTO;
import com.technophiles.diaryapp.exceptions.DiaryAppApplicationException;
import com.technophiles.diaryapp.models.Diary;
import com.technophiles.diaryapp.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@SpringBootTest
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
class UserServiceTest {
    @Autowired
    private UserService userService;
    private  CreateAccountRequest accountRequest;

    @BeforeEach
    void setUp() {
        accountRequest = CreateAccountRequest.builder()
                .email("testemail@gmail.com")
                .password("password")
                .build();
    }

    @Test
    void testCreateAccount(){
        UserDTO userDTO = userService.createAccount(accountRequest);
        assertThat(userDTO.getId()).isNotNull();
        assertThat(userDTO.getEmail()).isEqualTo("testemail@gmail.com");
    }

    @Test
    @DisplayName("When you try to create a user account with an email that already exist in DB," +
            "The create account service should throw a DiaryException with the message: user account already exists")
    void thatTestThrowsDiaryExceptionExceptionWhenEmailAlreadyExists(){
        userService.createAccount(accountRequest);
        CreateAccountRequest newAccountRequest = CreateAccountRequest.builder()
                .email("testemail@gmail.com")
                .password("password")
                .build();
        assertThatThrownBy(()->userService.createAccount(newAccountRequest)).isInstanceOf(DiaryAppApplicationException.class).hasMessage("user is already present");
    }

    @Test
    void testThatCanGetUserInformation(){
        UserDTO userDTO = userService.createAccount(accountRequest);
        UserDTO userFromDatabase = userService.findUser(userDTO.getId());
        assertThat(userDTO.getId()).isEqualTo(userFromDatabase.getId());
    }

    @Test
    void testThatCanUpdateUserInformation(){
        UserDTO userDTO = userService.createAccount(accountRequest);
        UpdateUserDTO updateUserDTO = new UpdateUserDTO("", "newest password");
        String result = userService.updateUser(userDTO.getId(), updateUserDTO);
        assertThat(result).isEqualTo("User details updated successfully");
        UserDTO userFromDatabase = userService.findUser(userDTO.getId());
        assertThat(userFromDatabase.getEmail()).isEqualTo("testemail@gmail.com");
    }

    @Test
    void testThatThrowsExceptionWhenUserIdIsNotFound(){
        userService.createAccount(accountRequest);
        String id = "null id";
        UpdateUserDTO updateUserDTO = UpdateUserDTO.builder()
                .password("new password")
                .email("testemail@gmail.com")
                .build();
        assertThatThrownBy(()-> userService.updateUser(id, updateUserDTO)).isInstanceOf(DiaryAppApplicationException.class).hasMessage("user account does not exist");
    }

    @Test
    void testThatCanAddDiaryToUser(){
        UserDTO userDTO = userService.createAccount(accountRequest);
        User user = userService.findUserByIdInternal(userDTO.getId());
        String diaryTitle = "diary title";
        Diary diary = new Diary(diaryTitle);
        Diary savedDiary = userService.addNewDiary(userDTO.getId(),diary);
        assertThat(savedDiary.getId()).isNotNull();
        assertThat(savedDiary.getTitle()).isEqualTo("diary title");
    }

    @AfterEach
    void tearDown() {
        userService.deleteByEmail("testemail@gmail.com");
    }
}