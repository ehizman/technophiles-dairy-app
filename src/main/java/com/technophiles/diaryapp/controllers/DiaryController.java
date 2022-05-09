package com.technophiles.diaryapp.controllers;

import com.technophiles.diaryapp.controllers.response.APIResponse;
import com.technophiles.diaryapp.exceptions.DiaryAppApplicationException;
import com.technophiles.diaryapp.models.Diary;
import com.technophiles.diaryapp.models.Entry;
import com.technophiles.diaryapp.services.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/diaries")
public class DiaryController {
    @Autowired
    DiaryService diaryService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<?> createNewDiary(@RequestParam String diaryTitle, @PathVariable("userId") String userId){
        try {
            Diary diary = diaryService.createDiary(diaryTitle, userId);
            APIResponse apiResponse = new APIResponse(diary, "diary successfully", true);
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        } catch (DiaryAppApplicationException exception){
            APIResponse apiResponse = APIResponse.builder()
                    .message(exception.getMessage())
                    .isSuccessful(false)
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    /*
    * in other for Jackson to automatically deserialize a JSON object we have to
    * implement a custom Deserializer- EntryDeserializer
    * */
    @PostMapping("/addEntries/{diaryId}")
    public ResponseEntity<?> addNewEntries(@PathVariable("diaryId") String diaryId, @RequestBody List<Entry> entryList){
        try{
            Diary diary = diaryService.addEntries(entryList, diaryId);
            APIResponse apiResponse = new APIResponse(diary, "diary successfully", true);
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        } catch (DiaryAppApplicationException exception){
            APIResponse apiResponse = APIResponse.builder()
                    .message(exception.getMessage())
                    .isSuccessful(false)
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
