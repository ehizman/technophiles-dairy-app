package com.technophiles.diaryapp.services;

import com.technophiles.diaryapp.controllers.requests.CreateAccountRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
class UserServiceTest {
    @Autowired
    private UserService userService;


    @Test
    void testCreateAccount(){
        CreateAccountRequest accountRequest = CreateAccountRequest.builder()
                .email("test@gmail.com")
                .password("password")
                .build();
        String id = userService.createAccount(accountRequest);
        assertThat(id).isNotNull();
    }

}