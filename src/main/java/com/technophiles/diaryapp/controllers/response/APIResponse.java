package com.technophiles.diaryapp.controllers.response;

import com.technophiles.diaryapp.dtos.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class APIResponse {
    private Object payLoad;
    private String message;
    private boolean isSuccessful;
}
