package com.technophiles.diaryapp.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDiaryForm {
    private String title;
    public UpdateDiaryForm(String newDiaryTitle) {
        this.title = newDiaryTitle;
    }
}
