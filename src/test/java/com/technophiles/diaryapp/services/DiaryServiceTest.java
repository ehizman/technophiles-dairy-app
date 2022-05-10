package com.technophiles.diaryapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
public class DiaryServiceTest {
    @Autowired
    private DiaryService diaryService;


//    @Test
//    void testThatCanCreateANewDiary(){
//        String title = "new diary title";
//        String userId = "626bddced1cf260d4686368a";
//        Diary diary = diaryService.createDiary(title, userId);
//        assertThat(diary.getText()).isEqualTo("new diary title");
//    }
}
