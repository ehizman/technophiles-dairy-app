package com.technophiles.diaryapp.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.technophiles.diaryapp.config.EntryDeserializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@JsonDeserialize(using = EntryDeserializer.class)
public class Entry {
    private String id;
    private String text;
    private LocalDateTime creationTime;

    public Entry(String text) {
        this.id = UUID.randomUUID().toString();
        this.text = text;
        this.creationTime = LocalDateTime.now();
    }
}
