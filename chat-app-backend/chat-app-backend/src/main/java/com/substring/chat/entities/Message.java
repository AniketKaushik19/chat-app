package com.substring.chat.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String  senderName;
    private  String content;
    private LocalDateTime timestamp;

    public Message(String senderName, String content) {
        this.senderName = senderName;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

       public void setContent(String content) {
        this.content = content;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setSender(String senderName) {
        this.senderName = senderName;
    }

}
