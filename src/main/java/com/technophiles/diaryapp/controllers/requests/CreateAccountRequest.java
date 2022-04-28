package com.technophiles.diaryapp.controllers.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class CreateAccountRequest {
    private String email;
    private String password;
}
