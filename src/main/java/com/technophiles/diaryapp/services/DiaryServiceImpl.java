package com.technophiles.diaryapp.services;

import com.technophiles.diaryapp.dtos.UpdateDiaryForm;
import com.technophiles.diaryapp.exceptions.DiaryAppApplicationException;
import com.technophiles.diaryapp.models.Diary;
import com.technophiles.diaryapp.models.Entry;
import com.technophiles.diaryapp.models.User;
import com.technophiles.diaryapp.repositories.DiaryRepository;
import com.technophiles.diaryapp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class DiaryServiceImpl implements DiaryService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DiaryRepository diaryRepository;
    @Override
    public Diary createDiary(String diaryTitle, String userId) {
        User user =  userRepository.findById(userId)
                .orElseThrow(()->new DiaryAppApplicationException("user not found"));
        Diary diary = new Diary(diaryTitle);
        Diary savedDiary = diaryRepository.save(diary);
        user.getDiaries().add(savedDiary);
        log.info("User --> {}", user);
        userRepository.save(user);
        return savedDiary;
    }

    @Override
    public String updateDiary(String diaryId, UpdateDiaryForm updateDiaryForm) {
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(()-> new DiaryAppApplicationException("diary does not exist"));
        if (!(updateDiaryForm.getTitle().trim().equals("") || updateDiaryForm.getTitle() == null)){
            diary.setText(updateDiaryForm.getTitle());
            diaryRepository.save(diary);
        }
        return "Diary has been updated";
    }

    @Override
    public Diary addEntries(List<Entry> entries, String diaryId) {
       Diary diary = diaryRepository.findById(diaryId).orElseThrow(()-> new DiaryAppApplicationException("diary does not exist"));
       diary.getEntries().addAll(entries);
       return diaryRepository.save(diary);
    }

    @Override
    public void deleteDiary(String id) {
        Diary diary = diaryRepository.findById(id).orElseThrow(()-> new DiaryAppApplicationException("Diary does not exist"));

        diaryRepository.delete(diary);
    }

    @Override
    public Diary findDiary(String id) {
        return diaryRepository.findById(id).orElse(null);
    }
}
