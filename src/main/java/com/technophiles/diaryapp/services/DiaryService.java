package com.technophiles.diaryapp.services;

import com.technophiles.diaryapp.dtos.UpdateDiaryForm;
import com.technophiles.diaryapp.models.Diary;
import com.technophiles.diaryapp.models.Entry;
import org.apache.logging.log4j.message.EntryMessage;

import java.util.List;

public interface DiaryService {
    Diary createDiary(String title, String userId);

    String updateDiary(String diaryId, UpdateDiaryForm updateDiaryForm);

    Diary addEntries(List<Entry> entries, String diaryId);
}
