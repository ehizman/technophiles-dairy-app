package com.technophiles.diaryapp.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class APIResponse {
    private String message;
    private boolean isSuccessful;
}
