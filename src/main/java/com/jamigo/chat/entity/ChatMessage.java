package com.jamigo.chat.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
public class ChatMessage {
    private String type;    //給前端判斷是chat、history等，類似大吳老師的action
    private String sender;
    private String receiver;
    private String message;
    private Boolean chatState;
}
