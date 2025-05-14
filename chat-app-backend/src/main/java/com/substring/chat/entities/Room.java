package com.substring.chat.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "rooms")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room  {
    @Id
    private String id;  //Unique id created by mongodb
    private String roomId; //Created by user
    private List<Message> messages=new ArrayList<>();

    public List<Message> getMessages() {
        return messages;
    }

    public void setRoomId(String roomId) {
        this.roomId=roomId;
    }
}
