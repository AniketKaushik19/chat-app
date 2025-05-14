package com.substring.chat.playload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.CrossOrigin;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class MessageRequest {
    private String content;
    private  String senderName;
    private  String roomId;

    public String getRoomId() {
        return roomId;
    }


    public String getContent() {
        return content;
    }
    public String getSenderName() {
        return senderName;
    }
}
